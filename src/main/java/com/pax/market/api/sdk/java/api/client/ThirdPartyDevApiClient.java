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
package com.pax.market.api.sdk.java.api.client;


import com.pax.market.api.sdk.java.api.BaseThirdPartyDevApi;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.constant.ResultCode;
import com.pax.market.api.sdk.java.api.util.CryptoUtils;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.ThirdPartyDevHttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * 客户端
 */
public class ThirdPartyDevApiClient {

	private static final Logger logger = LoggerFactory.getLogger(ThirdPartyDevApiClient.class.getSimpleName());

    /**
     * The Base url.
     */
    protected String baseUrl;
    /**
     * The Api key.
     */
    protected String apiKey;
    /**
     * The Api secret.
     */
    protected String apiSecret;
    /**
     * The Sign method.
     */
    protected String signMethod = Constants.SIGN_METHOD_HMAC;
    /**
     * The Connect timeout.
     */
    protected int connectTimeout = 60000; 			// 默认连接超时时间为30秒
    /**
     * The Read timeout.
     */
    protected int readTimeout = 60000; 				// 默认响应超时时间为30秒

    protected int retryTimes = 5;

    /**
     * Instantiates a new Default client.
     *
     * @param baseUrl   the base url
     */
    public ThirdPartyDevApiClient(String baseUrl, String apiKey, String apiSecret) {
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.baseUrl = baseUrl;
		if(BaseThirdPartyDevApi.connectTimeout>0) {
			this.connectTimeout = BaseThirdPartyDevApi.connectTimeout;
		}
		if(BaseThirdPartyDevApi.readTimeout>0) {
			this.readTimeout = BaseThirdPartyDevApi.readTimeout;
		}
		retryTimes = BaseThirdPartyDevApi.retryTimes;
	}


    /**
     * Instantiates a new Default client.
     *
     * @param baseUrl        the base url
     * @param apiKey         the app key
     * @param apiSecret      the app secret
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     */
    public ThirdPartyDevApiClient(String baseUrl, String apiKey, String apiSecret, int connectTimeout, int readTimeout) {
		this(baseUrl, apiKey, apiSecret);
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}

    /**
     * Instantiates a new Default client.
     *
     * @param baseUrl        the base url
     * @param appKey         the app key
     * @param appSecret      the app secret
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param signMethod     the sign method
     */
    public ThirdPartyDevApiClient(String baseUrl, String appKey, String appSecret, int connectTimeout, int readTimeout, String signMethod) {
		this(baseUrl, appKey, appSecret, connectTimeout, readTimeout);
		this.signMethod = signMethod;
	}

    /**
     * Execute string.
     *
     * @param request the request
     * @return the string
     */
    public String execute(SdkRequest request) {
    	request.addHeader(Constants.REQ_HEADER_SDK_LANG, Constants.THIRD_PARTY_API_SDK_LANGUAGE);
    	request.addHeader(Constants.REQ_HEADER_SDK_VERSION, Constants.THIRD_PARTY_API_SDK_VERSION);
		try {
			return _execute(request);
		} catch (IOException e) {
			logger.error("IOException occurred when execute request. Details: {}", e.toString());
		} catch (GeneralSecurityException e) {
			logger.error("GeneralSecurityException occurred when execute request. Details: {}", e.toString());
		}
		return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
	}

	protected String _execute(SdkRequest request) throws IOException, GeneralSecurityException {
		String response;

		if(apiKey != null) {
			request.addRequestParam(Constants.DEVELOPER_KEY, apiKey);
		}
		Long timestamp = request.getTimestamp();
		if(timestamp == null){
			timestamp = System.currentTimeMillis();
		}
		request.addRequestParam(Constants.TIMESTAMP, Long.toString(timestamp));

		String query = ThirdPartyDevHttpUtils.buildQuery(request.getRequestParams(), Constants.CHARSET_UTF8);
		if(apiSecret != null) {
			String signature = CryptoUtils.signRequest(query, null, apiSecret, signMethod);
			request.addHeader(Constants.SIGNATURE, signature);
		}
		String requestUrl = ThirdPartyDevHttpUtils.buildRequestUrl(baseUrl + request.getRequestMappingUrl(), query);
		logger.info(" --> {} {}", request.getRequestMethod().getValue(), requestUrl);

		response = ThirdPartyDevHttpUtils.request(requestUrl, request.getRequestMethod().getValue(),connectTimeout, readTimeout, request.getRequestBody(), request.getHeaderMap(), request.getUploadFileMap(), request.getFormValueMap(), retryTimes);

		return response;
	}

    /**
     * 设置API请求的连接超时时间
     *
     * @param connectTimeout the connect timeout
     */
    public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

    /**
     * 设置API请求的读超时时间
     *
     * @param readTimeout the read timeout
     */
    public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

}
