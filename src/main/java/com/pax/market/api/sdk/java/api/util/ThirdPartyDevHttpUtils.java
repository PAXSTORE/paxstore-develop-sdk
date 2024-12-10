/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.util;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.constant.ResultCode;
import com.pax.market.api.sdk.java.api.io.UploadedFileContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * 网络工具类。
 */
public abstract class ThirdPartyDevHttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(ThirdPartyDevHttpUtils.class);

	private static final int BUFFER_SIZE = 4096;
	private static final String DEFAULT_CHARSET = Constants.CHARSET_UTF8;
	private static Locale locale = Locale.CHINA;
	private static JsonParser jsonParser = new JsonParser();

	private static final String BOUNDARY_PREFIX = "--";
	/**
	 * 回车换行,用于一行的结尾
	 */
	private static final String LINE_END = "\r\n";

	/**
     * Sets local.
     *
     * @param locale the locale
     */
    public static void setLocal(Locale locale) {
		ThirdPartyDevHttpUtils.locale = locale;
	}

    /**
     * The type Trust all trust manager.
     */
    public static class TrustAllTrustManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}

	private ThirdPartyDevHttpUtils() {
	}

    /**
     * Request string.
     *
     * @param requestUrl     the request url
     * @param requestMethod  the request method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param userData       the user data, will be ignored when is multipart-form
     * @param headerMap      the header map
     * @param fileContentMap      the header map, only support for multipart-form
     * @param formValueMap      the header map, only support for multipart-form
     * @return the string
     */
    public static String request(String requestUrl, String requestMethod, int connectTimeout, int readTimeout, String userData, Map<String, String> headerMap, Map<String, UploadedFileContent> fileContentMap, Map<String, String> formValueMap, int retryTimes){
		try {
			return RetryUtils.retry(() -> {
				return request(requestUrl, requestMethod, connectTimeout, readTimeout, userData, false, headerMap, fileContentMap, formValueMap);
			}, e -> isExceptionShouldRetry(e), retryTimes);
		} catch (Exception e) {
			logger.error("Exception Occurred. Details: {}", e.toString());
			if(e instanceof IOException) {
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_UN_CONNECT);
			}else{
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
			}
		}
	}

	private static boolean isExceptionShouldRetry(Throwable e) {
    	if(e instanceof ConnectException){
    		return true;
		} else if(e instanceof SocketTimeoutException){
    		return true;
		}else{
    		return false;
		}
	}

	private static String request(String requestUrl, String requestMethod, int connectTimeout, int readTimeout, String userData, boolean compressData,
								  Map<String, String> headerMap, Map<String, UploadedFileContent> fileContentMap, Map<String, String> formValueMap) throws ConnectException, SocketTimeoutException{
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = getConnection(requestUrl, connectTimeout, readTimeout);
			if (fileContentMap!=null && !fileContentMap.isEmpty()) {
				//走multipart-form形式
				return finalMultipartFormRequest(urlConnection,  headerMap, fileContentMap, formValueMap);
			} else {
				//走restful形式
				return finalRestfulRequest(urlConnection, requestMethod, userData, compressData, headerMap);
			}

		} catch (IOException e) {
			if(e instanceof ConnectException){
				throw (ConnectException)e;
			}else if(e instanceof SocketTimeoutException){
				throw (SocketTimeoutException)e;
			}
			logger.error("IOException Occurred. Details: {}", e.toString());
		} finally {
			if(urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
	}

	private static String finalRestfulRequest(HttpURLConnection urlConnection, String requestMethod, String userData, boolean compressData,
													Map<String, String> headerMap) throws ConnectException,SocketTimeoutException{
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		FileOutputStream fileOutputStream = null;
		String filePath = null;
		try {
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod(requestMethod);
			urlConnection.setRequestProperty(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
			if(locale != null) {
				urlConnection.setRequestProperty(Constants.ACCESS_LANGUAGE, getLanguageTag(locale));
			}
			if (headerMap != null) {
				for (Entry<String, String> entry : headerMap.entrySet()) {
					urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			if ("GET".equalsIgnoreCase(requestMethod) || "DELETE".equalsIgnoreCase(requestMethod)) {
				urlConnection.connect();
			} else {
				urlConnection.setDoOutput(true);
			}

			String rateLimit = "";
			String rateLimitRemain = "";
			String rateLimitReset = "";

			if ((null != userData) && (userData.length() > 0)) {
				OutputStream outputStream = null;
				try {
					outputStream = urlConnection.getOutputStream();
					if (!compressData) {
						outputStream.write(userData.getBytes("UTF-8"));
					} else {
						String hexString = AlgHelper.bytes2HexString(compressData(userData.getBytes("UTF-8")));
						outputStream.write(hexString.getBytes());
					}
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
			}

			Map<String, List<String>> map = urlConnection.getHeaderFields();
			rateLimit = map.get("X-RateLimit-Limit")==null?"":map.get("X-RateLimit-Limit").get(0);
			rateLimitRemain = map.get("X-RateLimit-Remaining")==null?"":map.get("X-RateLimit-Remaining").get(0);
			rateLimitReset = map.get("X-RateLimit-Reset")==null?"":map.get("X-RateLimit-Reset").get(0);

			if (urlConnection.getResponseCode() == 200 || urlConnection.getResponseCode() == 201
					|| urlConnection.getResponseCode() == 204) {
				bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
			} else {
				if(urlConnection.getErrorStream() != null) {
					bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream(), "utf-8"));
				} else {
					bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
				}
			}

			String str;
			while ((str = bufferedReader.readLine()) != null) {
				stringBuilder.append(str);
			}
			String resultStr = stringBuilder.toString();
			if(StringUtils.isBlank(resultStr)) {
				resultStr= "{}";
			}else if(!StringUtils.startsWith(resultStr, "{")){
				resultStr=String.format("{%s}", resultStr);
			}
			JsonObject json = jsonParser.parse(resultStr).getAsJsonObject();
			json.addProperty("rateLimit", rateLimit);
			json.addProperty("rateLimitRemain", rateLimitRemain);
			json.addProperty("rateLimitReset", rateLimitReset);
			return json.toString();
		} catch (SocketTimeoutException localSocketTimeoutException) {
			if(StringUtils.containsIgnoreCase(localSocketTimeoutException.toString(),"Read timed out")){
				FileUtils.deleteFile(filePath);
				logger.error("SocketTimeoutException Occurred. Details: {}", localSocketTimeoutException.toString());
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_CONNECT_TIMEOUT);
			}else{
				throw localSocketTimeoutException;
			}

		} catch (ConnectException localConnectException) {
			throw localConnectException;

		} catch (FileNotFoundException fileNotFoundException) {
			FileUtils.deleteFile(filePath);
			logger.error("FileNotFoundException Occurred. Details: {}", fileNotFoundException.toString());
			return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_FILE_NOT_FOUND);
		} catch (Exception ignored) {
			FileUtils.deleteFile(filePath);
			logger.error("Exception Occurred. Details: {}", ignored.toString());
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error("IOException Occurred. Details: {}", e.toString());
				}
			}
			if(fileOutputStream != null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					logger.error("IOException Occurred. Details: {}", e.toString());
				}
			}
			if(urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
	}

	private static String finalMultipartFormRequest(HttpURLConnection urlConnection, Map<String, String> headerMap, Map<String, UploadedFileContent> fileContentMap, Map<String, String> formValueMap) throws ConnectException,SocketTimeoutException{
		String boundary = "PAXBoundary" + System.currentTimeMillis();
		BufferedReader bufferedReader = null;

		try {
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod(SdkRequest.RequestMethod.POST.getValue());
			urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			if(locale != null) {
				urlConnection.setRequestProperty(Constants.ACCESS_LANGUAGE, getLanguageTag(locale));
			}
			if (headerMap != null) {
				for (Entry<String, String> entry : headerMap.entrySet()) {
					urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			OutputStream outputStream = null;
			try {
				outputStream = new DataOutputStream(urlConnection.getOutputStream());
				if (fileContentMap != null && !fileContentMap.isEmpty()) {
					for (Entry<String, UploadedFileContent> uploadFile : fileContentMap.entrySet()) {
						writeFile(uploadFile.getKey(), uploadFile.getValue(), boundary, outputStream);
					}
				}

				if (formValueMap != null && !formValueMap.isEmpty()) {
					for (Entry<String, String> entry : formValueMap.entrySet()) {
						writeSimpleFormField(boundary, outputStream, entry);
					}
				}

				String endStr = BOUNDARY_PREFIX + boundary + BOUNDARY_PREFIX + LINE_END;
				outputStream.write(endStr.getBytes());

			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			Map<String, List<String>> map = urlConnection.getHeaderFields();
			String rateLimit = map.get("X-RateLimit-Limit")==null?"":map.get("X-RateLimit-Limit").get(0);
			String rateLimitRemain = map.get("X-RateLimit-Remaining")==null?"":map.get("X-RateLimit-Remaining").get(0);
			String rateLimitReset = map.get("X-RateLimit-Reset")==null?"":map.get("X-RateLimit-Reset").get(0);

			if (urlConnection.getResponseCode() == 200 || urlConnection.getResponseCode() == 201
					|| urlConnection.getResponseCode() == 204) {
				bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
			} else {
				if(urlConnection.getErrorStream() != null) {
					bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream(), "utf-8"));
				} else {
					bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
				}
			}
			String str;
			StringBuilder stringBuilder  = new StringBuilder();
			while ((str = bufferedReader.readLine()) != null) {
				stringBuilder.append(str);
			}
			String resultStr = stringBuilder.toString();
			if(StringUtils.isBlank(resultStr)) {
				resultStr= "{}";
			}else if(!StringUtils.startsWith(resultStr, "{")){
				resultStr=String.format("{%s}", resultStr);
			}
			JsonObject json = jsonParser.parse(resultStr).getAsJsonObject();
			json.addProperty("rateLimit", rateLimit);
			json.addProperty("rateLimitRemain", rateLimitRemain);
			json.addProperty("rateLimitReset", rateLimitReset);
			return json.toString();

		} catch (SocketTimeoutException localSocketTimeoutException) {
			if(StringUtils.containsIgnoreCase(localSocketTimeoutException.toString(),"Read timed out")){
				logger.error("SocketTimeoutException Occurred. Details: {}", localSocketTimeoutException.toString());
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_CONNECT_TIMEOUT);
			}else{
				throw localSocketTimeoutException;
			}
		} catch (ConnectException localConnectException) {
			throw localConnectException;

		} catch (FileNotFoundException fileNotFoundException) {
			logger.error("FileNotFoundException Occurred. Details: {}", fileNotFoundException.toString());
			return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_FILE_NOT_FOUND);
		} catch (Exception ignored) {
			logger.error("Exception Occurred. Details: {}", ignored.toString());
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error("IOException Occurred. Details: {}", e.toString());
				}
			}
			if(urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
	}

	private static void writeSimpleFormField(String boundary, OutputStream out, Entry<String, String> entry) throws IOException {
		String boundaryStr = BOUNDARY_PREFIX + boundary + LINE_END;
		out.write(boundaryStr.getBytes());
		String contentDispositionStr = String.format("Content-Disposition: form-data; name=\"%s\"", entry.getKey()) + LINE_END + LINE_END;
		out.write(contentDispositionStr.getBytes());
		String valueStr = entry.getValue() + LINE_END;
		out.write(valueStr.getBytes());
	}


	private static void writeFile(String paramName, UploadedFileContent fileContent, String boundary,
								  OutputStream out) {

		try (DataInputStream  in = new DataInputStream(new ByteArrayInputStream(fileContent.getBytesContent()))) {
			String boundaryStr = BOUNDARY_PREFIX + boundary + LINE_END;
			out.write(boundaryStr.getBytes());
			String fileName = fileContent.getName();
			String contentDispositionStr = String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", paramName, fileName) + LINE_END;
			out.write(contentDispositionStr.getBytes());
			String contentType = "Content-Type: application/octet-stream" + LINE_END + LINE_END;
			if (StringUtils.equals("apkFile", paramName)) {
				contentType = "Content-Type: application/vnd.android.package-archive" + LINE_END + LINE_END;
			} else if (paramName.contains("screenshot#") || paramName.contains("iconFile") || paramName.contains("featuredImg")) {
				contentType = "Content-Type: image/jpeg" + LINE_END + LINE_END;
			} else if (StringUtils.equals("attachment", paramName)) {
				contentType = "Content-Type: text/plain" + LINE_END + LINE_END;
			}
			out.write(contentType.getBytes());

		/*	String line;
			while ((line = fileReader.readLine()) != null) {
				out.write(line.getBytes());
			}*/

			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			out.write(LINE_END.getBytes());
		} catch (Exception e) {
			logger.error("write file error : " + e.toString());
		}
	}

	private static HttpURLConnection getConnection(String requestUrl, int connectTimeout, int readTimeout) throws IOException {
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (conn instanceof HttpsURLConnection) {
			HttpsURLConnection connHttps = (HttpsURLConnection) conn;
			try {
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(null, new TrustManager[] { new TrustAllTrustManager() }, new SecureRandom());
				connHttps.setSSLSocketFactory(ctx.getSocketFactory());
				connHttps.setHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
			} catch (GeneralSecurityException e){
				logger.error("GeneralSecurityException Occurred. Details: {}", e.toString());
			}
			connHttps.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			conn = connHttps;
		}

		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		return conn;
	}

	private static URL buildGetUrl(String url, String query) throws IOException {
		if (StringUtils.isEmpty(query)) {
			return new URL(url);
		}

		return new URL(buildRequestUrl(url, query));
	}

    /**
     * Build request url string.
     *
     * @param url     the url
     * @param queries the queries
     * @return the string
     */
    public static String buildRequestUrl(String url, String... queries) {
		if (queries == null || queries.length == 0) {
			return url;
		}

		StringBuilder newUrl = new StringBuilder(url);
		boolean hasQuery = url.contains("?");
		boolean hasPrepend = url.endsWith("?") || url.endsWith("&");

		for (String query : queries) {
			if (!StringUtils.isEmpty(query)) {
				if (!hasPrepend) {
					if (hasQuery) {
						newUrl.append("&");
					} else {
						newUrl.append("?");
						hasQuery = true;
					}
				}
				newUrl.append(query);
				hasPrepend = false;
			}
		}
		return newUrl.toString();
	}

    /**
     * Build query string.
     *
     * @param params  the params
     * @param charset the charset
     * @return the string
     * @throws IOException the io exception
     */
    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (StringUtils.areNotEmpty(name, value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}

    /**
     * Gets response as string.
     *
     * @param conn the conn
     * @return the response as string
     * @throws IOException the io exception
     */
    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		if (conn.getResponseCode() < 400) {
			String contentEncoding = conn.getContentEncoding();
			if (Constants.CONTENT_ENCODING_GZIP.equalsIgnoreCase(contentEncoding)) {
				return getStreamAsString(new GZIPInputStream(conn.getInputStream()), charset);
			} else {
				return getStreamAsString(conn.getInputStream(), charset);
			}
		} else {// Client Error 4xx and Server Error 5xx
			throw new IOException(conn.getResponseCode() + " " + conn.getResponseMessage());
		}
	}

    /**
     * Gets stream as string.
     *
     * @param stream  the stream
     * @param charset the charset
     * @return the stream as string
     * @throws IOException the io exception
     */
    public static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			Reader reader = new InputStreamReader(stream, charset);
			StringBuilder response = new StringBuilder();

			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

    /**
     * Gets response charset.
     *
     * @param ctype the ctype
     * @return the response charset
     */
    public static String getResponseCharset(String ctype) {
		String charset = DEFAULT_CHARSET;

		if (!StringUtils.isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!StringUtils.isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

    /**
     * 使用默认的UTF-8字符集反编码请求参数值。
     *
     * @param value 参数值
     * @return 反编码后的参数值 string
     */
    public static String decode(String value) {
		return decode(value, DEFAULT_CHARSET);
	}

    /**
     * 使用默认的UTF-8字符集编码请求参数值。
     *
     * @param value 参数值
     * @return 编码后的参数值 string
     */
    public static String encode(String value) {
		return encode(value, DEFAULT_CHARSET);
	}

    /**
     * 使用指定的字符集反编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 反编码后的参数值 string
     */
    public static String decode(String value, String charset) {
		String result = null;
		if (!StringUtils.isEmpty(value)) {
			try {
				result = URLDecoder.decode(value, charset);
			} catch (IOException e) {
				logger.error("IOException Occurred. Details: {}", e.toString());
			}
		}
		return result;
	}

    /**
     * 使用指定的字符集编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 编码后的参数值 string
     */
    public static String encode(String value, String charset) {
		String result = null;
		if (!StringUtils.isEmpty(value)) {
			try {
				result = URLEncoder.encode(value, charset);
			} catch (IOException e) {
				logger.error("IOException Occurred. Details: {}", e.toString());
			}
		}
		return result;
	}

    /**
     * 从URL中提取所有的参数。
     *
     * @param query URL地址
     * @return 参数映射 map
     */
    public static Map<String, String> splitUrlQuery(String query) {
		Map<String, String> result = new HashMap<String, String>();

		String[] pairs = query.split("&");
		if (pairs != null && pairs.length > 0) {
			for (String pair : pairs) {
				String[] param = pair.split("=", 2);
				if (param != null && param.length == 2) {
					result.put(param[0], param[1]);
				}
			}
		}

		return result;
	}

    /**
     * Compress data byte [ ].
     *
     * @param bytes the bytes
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    public static byte[] compressData(byte[] bytes)
			throws IOException {
		if (null == bytes) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		gzipOutputStream.write(bytes);
		gzipOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	private static String getLanguageTag(Locale locale) {
		if (locale != null) {
			String localeStr = locale.toString();
			return localeStr.replace("_", "-");
		}
		return null;
	}



}
