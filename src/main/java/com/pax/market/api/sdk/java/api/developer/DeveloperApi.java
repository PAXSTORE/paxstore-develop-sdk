package com.pax.market.api.sdk.java.api.developer;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartyDevApi;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartyDevApiClient;
import com.pax.market.api.sdk.java.api.developer.dto.CreateApkRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 2 * @Author: Zhou Dong
 * 3 * @Date: 2020/7/28 17:53
 * 4
 */
public class DeveloperApi extends BaseThirdPartyDevApi {
    private static final Logger logger = LoggerFactory.getLogger(DeveloperApi.class.getSimpleName());

    protected static final String UPLOAD_APK_URL = "/v1/3rd/developer/apk/upload";

    public DeveloperApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public DeveloperApi(String baseUrl, String apiKey, String apiSecret, TimeZone timeZone) {
        super(baseUrl, apiKey, apiSecret, timeZone);
    }

    public Result<String> uploadApk(CreateApkRequest createApkRequest) {
        List<String> validationErrs = validateCreate(createApkRequest, "parameter.terminalCreateRequest.null");
        if (validationErrs.size() > 0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPLOAD_APK_URL);
        handleFormData(createApkRequest,request);
        return emptyResult(client,request);
    }

    private void handleFormData(CreateApkRequest createApkRequest, SdkRequest request) {
        if (StringUtils.isNotBlank(createApkRequest.getIconFilePath())) {
            request.addFilePath("iconFile", createApkRequest.getIconFilePath());
        }

        if (StringUtils.isNotBlank(createApkRequest.getFeaturedImgPath())) {
            request.addFilePath("featuredImg", createApkRequest.getFeaturedImgPath());
        }

        if (StringUtils.isNotBlank(createApkRequest.getAppFilePath())) {
            request.addFilePath("apkFile", createApkRequest.getAppFilePath());
        }

        if (createApkRequest.getScreenshotFiles() != null && !createApkRequest.getScreenshotFiles().isEmpty()) {
            for (int i = 0; i < createApkRequest.getScreenshotFiles().size(); i++) {
                request.addFilePath("screenshot#" + i, createApkRequest.getScreenshotFiles().get(i));
            }
        }

        if (createApkRequest.getParamTemplateFiles() != null && !createApkRequest.getParamTemplateFiles().isEmpty()) {
            List<String> paramTemplateNameList = new ArrayList<>();
            for (int i = 0; i < createApkRequest.getParamTemplateFiles().size(); i++) {
                String filePath = createApkRequest.getParamTemplateFiles().get(i);
                String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
                request.addFilePath("paramTemplate#" + fileName, filePath);
                paramTemplateNameList.add(fileName);
            }
            createApkRequest.setParamTemplateList(paramTemplateNameList);
        }

        request.addFormValue("apkDetail", new Gson().toJson(createApkRequest, CreateApkRequest.class));
    }

    private Result<String>  emptyResult(ThirdPartyDevApiClient client, SdkRequest request) {
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return  new Result<String>(emptyResponse);
    }
}
