package com.pax.market.api.sdk.java.api.developer;

import com.google.gson.Gson;
import com.pax.market.api.sdk.java.api.BaseThirdPartyDevApi;
import com.pax.market.api.sdk.java.api.base.dto.*;
import com.pax.market.api.sdk.java.api.base.request.SdkRequest;
import com.pax.market.api.sdk.java.api.client.ThirdPartyDevApiClient;
import com.pax.market.api.sdk.java.api.developer.dto.step.CreateSingleAppRequest;
import com.pax.market.api.sdk.java.api.developer.dto.step.CreateSingleApkRequest;
import com.pax.market.api.sdk.java.api.developer.dto.CreateApkRequest;
import com.pax.market.api.sdk.java.api.developer.dto.step.EditSingleApkRequest;
import com.pax.market.api.sdk.java.api.io.UploadedFileContent;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import com.pax.market.api.sdk.java.api.util.GsonUtils;
import com.pax.market.api.sdk.java.api.util.StringUtils;
import com.pax.market.api.sdk.java.api.validate.Validators;
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
    protected static final String CREATE_APP_URL = "/v1/3rd/developer/apps";
    protected static final String CREATE_APK_URL = "/v1/3rd/developer/apps/{appId}/apks";
    protected static final String EDIT_APK_URL = "/v1/3rd/developer/apks/{apkId}";
    protected static final String SUBMIT_APK_URL = "/v1/3rd/developer/apks/{apkId}/submit";
    protected static final String DELETE_APP_URL = "/v1/3rd/developer/apps/{appId}";
    protected static final String DELETE_APK_URL = "/v1/3rd/developer/apks/{apkId}";
    protected static final String GET_APP_BY_PACKAGE_URL = "/v1/3rd/developer/apps";
    protected static final String GET_CODE_BY_TYPE_URL = "/v1/3rd/developer/codes";
    protected static final String GET_APK_BY_TYPE_URL = "/v1/3rd/developer/apks/{apkId}";

    public DeveloperApi(String baseUrl, String apiKey, String apiSecret) {
        super(baseUrl, apiKey, apiSecret);
    }

    public DeveloperApi(String baseUrl, String apiKey, String apiSecret, TimeZone timeZone) {
        super(baseUrl, apiKey, apiSecret, timeZone);
    }

    public Result<String> uploadApk(CreateApkRequest createApkRequest) {
        List<String> validationErrs = Validators.validateCreate(createApkRequest, "parameter.terminalCreateRequest.null");
        if (validationErrs.size() > 0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(UPLOAD_APK_URL);
        handleFormData(createApkRequest,request);
        return emptyResult(client,request);
    }

    public Result<Long> createApp(CreateSingleAppRequest createAppRequest) {
        List<String> validationErrs = Validators.validateCreate(createAppRequest, "parameter.appCreateRequest.null");
        if (validationErrs.size() > 0) {
            return new Result<Long>(validationErrs);
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_APP_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        request.setRequestBody(new Gson().toJson(createAppRequest, CreateSingleAppRequest.class));
        return idResult(client,request);
    }

    public Result<Long> createApk(CreateSingleApkRequest createApkRequest) {
        List<String> validationErrs = Validators.validateCreate(createApkRequest, "parameter.apkCreateRequest.null");
        if (validationErrs.size() > 0) {
            return new Result<Long>(validationErrs);
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(CREATE_APK_URL.replace("{appId}", String.valueOf(createApkRequest.getAppId())));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        handleCreateApkFormData(createApkRequest, request);

        return idResult(client,request);
    }

    public Result<String> editApk(EditSingleApkRequest editApkRequest) {
        List<String> validationErrs = Validators.validateCreate(editApkRequest, "parameter.apkEditRequest.null");
        if (validationErrs.size() > 0) {
            return new Result<String>(validationErrs);
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(EDIT_APK_URL.replace("{apkId}", String.valueOf(editApkRequest.getApkId())));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        handleEditApkFormData(editApkRequest, request);

        return emptyResult(client,request);
    }

    public Result<String> deleteApk(Long apkId) {
        if(apkId==null || apkId<=0){
            return new Result<String>(Collections.singletonList("parameter.apkDeleteRequest.null"));
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_APK_URL.replace("{apkId}", String.valueOf(apkId)));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        return emptyResult(client,request);
    }

    public Result<String> deleteApp(Long appId) {
        if(appId==null || appId<=0){
            return new Result<String>(Collections.singletonList("parameter.appDeleteRequest.null"));
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(DELETE_APP_URL.replace("{appId}", String.valueOf(appId)));
        request.setRequestMethod(SdkRequest.RequestMethod.DELETE);
        return emptyResult(client,request);
    }

    public Result<AppDetailDTO> getAppInfoByName(String packageName, String appName) {
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_APP_BY_PACKAGE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        if(StringUtils.isNotBlank(packageName)){
            request.getRequestParams().put("packageName", packageName);
        }
        if(StringUtils.isNotBlank(appName)){
            request.getRequestParams().put("appName", appName);
        }
        return dataResult(client,request);
    }

    public Result<String> submitApk(Long apkId) {
        if(apkId==null || apkId<=0){
            return new Result<String>(Collections.singletonList("parameter.apkSubmitRequest.null"));
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(SUBMIT_APK_URL.replace("{apkId}", String.valueOf(apkId)));
        request.setRequestMethod(SdkRequest.RequestMethod.POST);
        return emptyResult(client,request);
    }

    public Result<ApkInfoDTO> getApkById(Long apkId) {
        if(apkId==null || apkId<=0){
            return new Result<ApkInfoDTO>(Collections.singletonList("parameter.apkId.null"));
        }
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_APK_BY_TYPE_URL.replace("{apkId}", String.valueOf(apkId)));
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        return apkDataResult(client,request);
    }

    private void handleCreateApkFormData(CreateSingleApkRequest createApkRequest, SdkRequest request) {
        if (createApkRequest.getIconFile()!=null) {
            request.addUploadFile("iconFile", createApkRequest.getIconFile());
        }

        if (createApkRequest.getFeaturedImgFile()!=null) {
            request.addUploadFile("featuredImg", createApkRequest.getFeaturedImgFile());
        }

        if (createApkRequest.getAppFile()!=null) {
            request.addUploadFile("apkFile", createApkRequest.getAppFile());
        }

        if (createApkRequest.getScreenshotFileList() != null && !createApkRequest.getScreenshotFileList().isEmpty()) {
            for (int i = 0; i < createApkRequest.getScreenshotFileList().size(); i++) {
                request.addUploadFile("screenshot#" + i, createApkRequest.getScreenshotFileList().get(i));
            }
        }

        if (createApkRequest.getParamTemplateFileList() != null && !createApkRequest.getParamTemplateFileList().isEmpty()) {
            List<String> paramTemplateNameList = new ArrayList<>();
            for(UploadedFileContent fileContent : createApkRequest.getParamTemplateFileList()){
                request.addUploadFile("paramTemplate#" + fileContent.getName(), fileContent);
                paramTemplateNameList.add(fileContent.getName());
            }
            createApkRequest.setParamTemplateList(paramTemplateNameList);
        }
        Gson gson = GsonUtils.getGsonBuilder();
        request.addFormValue("apkDetail", gson.toJson(createApkRequest, CreateSingleApkRequest.class));
    }

    private void handleEditApkFormData(EditSingleApkRequest editApkRequest, SdkRequest request) {
        if (editApkRequest.getIconFile()!=null) {
            request.addUploadFile("iconFile", editApkRequest.getIconFile());
        }

        if (editApkRequest.getFeaturedImgFile()!=null) {
            request.addUploadFile("featuredImg", editApkRequest.getFeaturedImgFile());
        }

        if (editApkRequest.getAppFile()!=null) {
            request.addUploadFile("apkFile", editApkRequest.getAppFile());
        }

        if (editApkRequest.getScreenshotFileList() != null && !editApkRequest.getScreenshotFileList().isEmpty()) {
            for (int i = 0; i < editApkRequest.getScreenshotFileList().size(); i++) {
                request.addUploadFile("screenshot#" + i, editApkRequest.getScreenshotFileList().get(i));
            }
        }

        if (editApkRequest.getParamTemplateFileList() != null && !editApkRequest.getParamTemplateFileList().isEmpty()) {
            List<String> paramTemplateNameList = new ArrayList<>();
            for(UploadedFileContent fileContent: editApkRequest.getParamTemplateFileList()){
                request.addUploadFile("paramTemplate#" + fileContent.getName(), fileContent);
                paramTemplateNameList.add(fileContent.getName());
            }
            editApkRequest.setParamTemplateList(paramTemplateNameList);
        }

        request.addFormValue("apkDetail", GsonUtils.getGsonBuilder().toJson(editApkRequest, EditSingleApkRequest.class));
    }

    private void handleFormData(CreateApkRequest createApkRequest, SdkRequest request) {
        if (createApkRequest.getIconFile()!=null) {
            request.addUploadFile("iconFile", createApkRequest.getIconFile());
        }

        if (createApkRequest.getFeaturedImgFile()!=null) {
            request.addUploadFile("featuredImg", createApkRequest.getFeaturedImgFile());
        }

        if (createApkRequest.getAppFile()!=null) {
            request.addUploadFile("apkFile", createApkRequest.getAppFile());
        }

        if (createApkRequest.getAttachment()!=null) {
            request.addUploadFile("attachment", createApkRequest.getAttachment());
        }

        if (createApkRequest.getScreenshotFileList() != null && !createApkRequest.getScreenshotFileList().isEmpty()) {
            for (int i = 0; i < createApkRequest.getScreenshotFileList().size(); i++) {
                request.addUploadFile("screenshot#" + i, createApkRequest.getScreenshotFileList().get(i));
            }
        }

        if (createApkRequest.getParamTemplateFileList() != null && !createApkRequest.getParamTemplateFileList().isEmpty()) {
            List<String> paramTemplateNameList = new ArrayList<>();
            for(UploadedFileContent fileContent : createApkRequest.getParamTemplateFileList()){
                request.addUploadFile("paramTemplate#" + fileContent.getName(), fileContent);
                paramTemplateNameList.add(fileContent.getName());
            }
            createApkRequest.setParamTemplateList(paramTemplateNameList);
        }

        request.addFormValue("apkDetail", GsonUtils.getGsonBuilder().toJson(createApkRequest, CreateApkRequest.class));
    }

    private Result<String>  emptyResult(ThirdPartyDevApiClient client, SdkRequest request) {
        EmptyResponse emptyResponse =  EnhancedJsonUtils.fromJson(client.execute(request), EmptyResponse.class);
        return  new Result<>(emptyResponse);
    }

    private Result<AppDetailDTO>  dataResult(ThirdPartyDevApiClient client, SdkRequest request ) {
        AppDetailResponse dataResponse =  EnhancedJsonUtils.fromJson(client.execute(request), AppDetailResponse.class);
        return  new Result<>(dataResponse);
    }

    private Result<ApkInfoDTO>  apkDataResult(ThirdPartyDevApiClient client, SdkRequest request ) {
        ApkResponse dataResponse =  EnhancedJsonUtils.fromJson(client.execute(request), ApkResponse.class);
        return  new Result<>(dataResponse);
    }

    private Result<Long>  idResult(ThirdPartyDevApiClient client, SdkRequest request ) {
        LongIdResponse idResponse =  EnhancedJsonUtils.fromJson(client.execute(request), LongIdResponse.class);
        return  new Result<>(idResponse);
    }

    public Result<CodeInfoDTO> getAppCategory() {
        ThirdPartyDevApiClient client = new ThirdPartyDevApiClient(getBaseUrl(), getApiKey(), getApiSecret());
        SdkRequest request = createSdkRequest(GET_CODE_BY_TYPE_URL);
        request.setRequestMethod(SdkRequest.RequestMethod.GET);
        request.getRequestParams().put("codeType", "app_category");
        request.getRequestParams().put("lang", "en");
        return codeListResult(client,request);
    }

    private static Result<CodeInfoDTO> codeListResult(ThirdPartyDevApiClient client, SdkRequest request ) {
        PageResponse<CodeInfoDTO> pageResponse = EnhancedJsonUtils.fromJson(client.execute(request), CodeInfoPageResponse.class);
        return new Result<>(pageResponse);
    }

}
