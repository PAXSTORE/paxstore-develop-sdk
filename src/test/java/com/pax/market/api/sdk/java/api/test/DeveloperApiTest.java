package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.*;
import com.pax.market.api.sdk.java.api.developer.DeveloperApi;
import com.pax.market.api.sdk.java.api.developer.dto.ApkOfflineRequest;
import com.pax.market.api.sdk.java.api.developer.dto.step.*;
import com.pax.market.api.sdk.java.api.developer.dto.CreateApkRequest;
import com.pax.market.api.sdk.java.api.io.UploadedFileContent;
import com.pax.market.api.sdk.java.api.util.AESUtils;
import com.pax.market.api.sdk.java.api.util.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pax.market.api.sdk.java.api.constant.Constants.APP_TYPE_NORMAL;
import static com.pax.market.api.sdk.java.api.constant.Constants.APP_TYPE_PARAMETER;

/**
 * 2 * @Author: Zhou Dong
 * 3 * @Date: 2020/7/29 16:49
 * 4
 */
public class DeveloperApiTest {
    private static final Logger logger = LoggerFactory.getLogger(DeveloperApiTest.class.getSimpleName());

    static DeveloperApi developerApi;

    @Before
    public void init() {
        developerApi = new DeveloperApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }


    @Test
    public void testUploadApk() {
        CreateApkRequest createApkRequest = new CreateApkRequest();
        createApkRequest.setAppFile(FileUtils.createUploadFile("C:\\TestApp.apk"));
        createApkRequest.setAppName("Test App Name");
        createApkRequest.setAppNameByVersion("App Name By Version");
        createApkRequest.setBaseType(APP_TYPE_NORMAL);
        //parameter should use APP_TYPE_PARAMETER
        //createApkRequest.setBaseType(APP_TYPE_PARAMETER);
        createApkRequest.setShortDesc("test short desc");
        createApkRequest.setDescription("test description");
        createApkRequest.setReleaseNotes("This is release note");
        createApkRequest.setChargeType(1);
        createApkRequest.setPrice(BigDecimal.ONE);

        List<String> categoryList = new ArrayList<>();
        // business category dictionary
        // You can get it from app edit page in develop center or admin platform
        categoryList.add("WL_PS");
        categoryList.add("WL_SK");
        createApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        //
        modelNameList.add("A920");
        //modelNameList.add("Prolin");
        createApkRequest.setModelNameList(modelNameList);

        List<UploadedFileContent> screenshotList = new ArrayList<>();
        screenshotList.add(FileUtils.createUploadFile("D:\\image\\1.jpg"));
        screenshotList.add(FileUtils.createUploadFile("D:\\image\\2.jpg"));
        screenshotList.add(FileUtils.createUploadFile("D:\\image\\3.jpg"));
        createApkRequest.setScreenshotFileList(screenshotList);

        List<UploadedFileContent> paramList = new ArrayList<>();
        paramList.add(FileUtils.createUploadFile("C:\\Users\\zhoudong\\Desktop\\template01.xml"));
        paramList.add(FileUtils.createUploadFile("C:\\Users\\zhoudong\\Desktop\\template02.xml"));
        createApkRequest.setParamTemplateFileList(paramList);

        createApkRequest.setFeaturedImgFile(FileUtils.createUploadFile("D:\\image\\3.jpg"));
        createApkRequest.setIconFile(FileUtils.createUploadFile("D:\\image.jpg"));
        Result<String>  result = developerApi.uploadApk(createApkRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateApp() {
        CreateSingleAppRequest createAppRequest = new CreateSingleAppRequest();
        createAppRequest.setAppName("TestApp");

        Result<Long>  result = developerApi.createApp(createAppRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
        Assert.assertNotNull("create App failed", result.getData());
    }

    @Test
    public void testCreateApk() {
        CreateSingleApkRequest createApkRequest = new CreateSingleApkRequest();
        createApkRequest.setAppId(1643135327273002L);
        createApkRequest.setAppFile(FileUtils.createUploadFile("C:\\TestApp.apk"));
        createApkRequest.setApkName("TestAppV1.0");
        createApkRequest.setApkType(APP_TYPE_PARAMETER);
        createApkRequest.setShortDesc("test short desc");
        createApkRequest.setDescription("test description");
        createApkRequest.setReleaseNotes("This is release note");
        createApkRequest.setAccessUrl("www.baidu.com");
        createApkRequest.setChargeType(0);
        createApkRequest.setPrice(BigDecimal.ONE);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("CY");
        createApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
        //modelNameList.add("Prolin");
        createApkRequest.setModelNameList(modelNameList);

        List<UploadedFileContent> screenshotList = new ArrayList<>();
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp1.png"));
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp2.png"));
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp3.png"));
        createApkRequest.setScreenshotFileList(screenshotList);

        List<UploadedFileContent> paramList = new ArrayList<>();
        paramList.add(FileUtils.createUploadFile("C:\\TestApp_paramTemplate.xml"));
        createApkRequest.setParamTemplateFileList(paramList);

        createApkRequest.setFeaturedImgFile(FileUtils.createUploadFile("C:\\TestApp3.png"));
        createApkRequest.setIconFile(FileUtils.createUploadFile("C:\\TestApp3.png"));

        Result<Long>  result = developerApi.createApk(createApkRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
        Assert.assertNotNull("create Apk failed", result.getData());
    }

    @Test
    public void testCreateMultipleApk() {
        CreateMultipleApkRequest createApkRequest = new CreateMultipleApkRequest();
        createApkRequest.setAppId(1705773446987822L);
        Map<String, UploadedFileContent> multipleApk = new HashMap<>();
        multipleApk.put("PAX", FileUtils.createUploadFile("C:\\101.0.apk"));
        multipleApk.put("A_Multivendor", FileUtils.createUploadFile("C:\\101.0.apk"));
        createApkRequest.setMultipleAppFile(multipleApk);
        createApkRequest.setApkName("TestApp");
        createApkRequest.setApkType(APP_TYPE_PARAMETER);
        createApkRequest.setShortDesc("test short desc");
        createApkRequest.setDescription("test description");
        createApkRequest.setReleaseNotes("This is release note");
        createApkRequest.setAccessUrl("www.baidu.com");
        createApkRequest.setChargeType(0);
        createApkRequest.setPrice(BigDecimal.ONE);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("CY_WM");
        categoryList.add("CY_JXC");
        createApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
        modelNameList.add("Multivendor1");
        createApkRequest.setModelNameList(modelNameList);

        List<UploadedFileContent> screenshotList = new ArrayList<>();
        screenshotList.add(FileUtils.createUploadFile("C:\\1.jpeg"));
        screenshotList.add(FileUtils.createUploadFile("C:\\2.jpeg"));
        screenshotList.add(FileUtils.createUploadFile("C:\\3.jpeg"));
        createApkRequest.setScreenshotFileList(screenshotList);

        List<UploadedFileContent> paramList = new ArrayList<>();
        paramList.add(FileUtils.createUploadFile("C:\\TestApp_paramTemplate.xml"));
        createApkRequest.setParamTemplateFileList(paramList);

        createApkRequest.setFeaturedImgFile(FileUtils.createUploadFile("/Users/shawnshi/公司文件/app/免费看漫画大全/3.jpeg"));
        createApkRequest.setIconFile(FileUtils.createUploadFile("/Users/shawnshi/公司文件/app/免费看漫画大全/1.jpeg"));

        Result<Long>  result = developerApi.createMultipleApk(createApkRequest);
        Assert.assertEquals(0, result.getBusinessCode());
        Assert.assertNotNull("create Apk failed", result.getData());
    }

    @Test
    public void testEditApk() {
        EditSingleApkRequest editApkRequest = new EditSingleApkRequest();
        editApkRequest.setApkId(1643163756265516L);
        editApkRequest.setApkName("TestAppV1.2");
//        editApkRequest.setAppFile(FileUtils.createUploadFile("C:\\TestApp_1.apk"));
        editApkRequest.setApkType(APP_TYPE_PARAMETER);
        editApkRequest.setShortDesc("test short descV3");
        editApkRequest.setDescription("test descriptionV3");
        editApkRequest.setReleaseNotes("This is release noteV3");
        editApkRequest.setAccessUrl("www.baidu-v3.com");

        List<String> categoryList = new ArrayList<>();
        categoryList.add("GYL");
//        categoryList.add("WL_SK");
        editApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
//        modelNameList.add("E500");
        //modelNameList.add("Prolin");
        editApkRequest.setModelNameList(modelNameList);

        List<UploadedFileContent> screenshotList = new ArrayList<>();
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp111.png"));
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp222.png"));
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp333.png"));
        editApkRequest.setScreenshotFileList(screenshotList);

        List<UploadedFileContent> paramList = new ArrayList<>();
        paramList.add(FileUtils.createUploadFile("C:\\TestApp_paramTemplate2.xml"));
        editApkRequest.setParamTemplateFileList(paramList);

        editApkRequest.setFeaturedImgFile(FileUtils.createUploadFile("C:\\TestApp333.png"));
        editApkRequest.setIconFile(FileUtils.createUploadFile("C:\\TestApp333.png"));

        Result<String>  result = developerApi.editApk(editApkRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testDeleteApk() {
        developerApi.deleteApk(1593832021950503L);
    }

    @Test
    public void testDeleteApp() {
        developerApi.deleteApp(2598004800782372L);
    }

    @Test
    public void testSubmitApk() {
        developerApi.submitApk(1705958438862898L);
    }

    @Test
    public void testGetAppInfoByName() {
        Result<AppDetailDTO> testApp = developerApi.getAppInfoByName(null, "testApp");
        Assert.assertTrue(testApp.getBusinessCode() == 0);
        Assert.assertNotNull("get App failed", testApp.getData());
    }

    @Test
    public void testGetAppCategory() {
        Result<CodeInfoDTO> result = developerApi.getAppCategory();
        PageInfo<CodeInfoDTO> pageInfo = result.getPageInfo();
        Assert.assertTrue(result.getBusinessCode() == 0);
        Assert.assertNotNull("get codeList failed", pageInfo);
    }

    @Test
    public void testGetApk() {
        Result<ApkInfoDTO> apkInfo = developerApi.getApkById(1643270597771298L);
        ApkInfoDTO data = apkInfo.getData();
        Assert.assertTrue(apkInfo.getBusinessCode() == 0);
        Assert.assertNotNull("get codeList failed", data);
    }

    @Test
    public void testGetAppKeySecret() {
        Result<AppKeySecretDTO> result = developerApi.getAppKeySecret(1705773446987822L);
        logger.debug("result is {}", result);
        Assert.assertEquals(0, result.getBusinessCode());
    }

    @Test
    public void testUpdateAppKeySecret() {
        EditAppKeySecretRequest appKeySecretRequest = new EditAppKeySecretRequest();
        appKeySecretRequest.setAppKey(AESUtils.silentEncrypt("6XC042UCQG51T17F0D6X", TestConstants.API_SECRET));
        appKeySecretRequest.setAppSecret(AESUtils.silentEncrypt("P8J5JJ5801O65O3D32278999K6Y6NHZ6LU82318R",TestConstants.API_SECRET));

        Result<String> result = developerApi.updateAppKeySecret(1653632709689384L, appKeySecretRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
        Assert.assertNotNull("update AppKey Secret failed", result.getData());
    }

    @Test
    public void testOfflineApk() {
        ApkOfflineRequest offlineRequest = new ApkOfflineRequest();
        offlineRequest.setComment("too old version");
        Result<String> apkInfo = developerApi.offlineApkById(1643270597771298L, offlineRequest);
        Assert.assertTrue(apkInfo.getBusinessCode() == 0);
    }

    @Test
    public void testGetApkVersionList() {
        Result<ApkVersionDTO> result = developerApi.getApkVersionList(1643490278637602L);
        Assert.assertTrue(result.getBusinessCode() == 0);
        PageInfo<ApkVersionDTO> pageInfo = result.getPageInfo();
    }


}
