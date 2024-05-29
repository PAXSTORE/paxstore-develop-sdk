package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.developer.DeveloperApi;
import com.pax.market.api.sdk.java.api.developer.dto.step.CreateSingleAppRequest;
import com.pax.market.api.sdk.java.api.developer.dto.step.CreateSingleApkRequest;
import com.pax.market.api.sdk.java.api.developer.dto.CreateApkRequest;
import com.pax.market.api.sdk.java.api.developer.dto.step.EditSingleApkRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        createApkRequest.setAppFilePath("C:\\TestApp.apk");
        createApkRequest.setAppName("Test App Name");
        createApkRequest.setAppNameByVersion("App Name By Version");
        createApkRequest.setBaseType("N");
        createApkRequest.setShortDesc("test short desc");
        createApkRequest.setDescription("test description");
        createApkRequest.setReleaseNotes("This is release note");
        createApkRequest.setChargeType(1);
        createApkRequest.setPrice(BigDecimal.ONE);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("WL_PS");
        categoryList.add("WL_SK");
        createApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
        //modelNameList.add("Prolin");
        createApkRequest.setModelNameList(modelNameList);

        List<String> screenshotList = new ArrayList<>();
        screenshotList.add("D:\\image\\1.jpg");
        screenshotList.add("D:\\image\\2.jpg");
        screenshotList.add("D:\\image\\3.jpg");
        createApkRequest.setScreenshotFiles(screenshotList);

       /* List<String> paramList = new ArrayList<>();
        paramList.add("C:\\Users\\zhoudong\\Desktop\\template01.xml");
        paramList.add("C:\\Users\\zhoudong\\Desktop\\template02.xml");
        createApkRequest.setParamTemplateFiles(paramList);*/

        createApkRequest.setFeaturedImgPath("D:\\image\\3.jpg");
        createApkRequest.setIconFilePath("D:\\image.jpg");
        Result<String>  result = developerApi.uploadApk(createApkRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateApp() {
        CreateSingleAppRequest createAppRequest = new CreateSingleAppRequest();
        createAppRequest.setAppName("TestApp");

        Result<String>  result = developerApi.createApp(createAppRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateApk() {
        CreateSingleApkRequest createApkRequest = new CreateSingleApkRequest();
        createApkRequest.setAppId(1605025453309994L);
        createApkRequest.setAppFilePath("C:\\TestApp.apk");
        createApkRequest.setApkName("TestAppV1.0");
        createApkRequest.setApkType("P");
        createApkRequest.setShortDesc("test short desc");
        createApkRequest.setDescription("test description");
        createApkRequest.setReleaseNotes("This is release note");
        createApkRequest.setAccessUrl("www.baidu.com");
        createApkRequest.setChargeType(0);
        createApkRequest.setPrice(BigDecimal.ONE);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("JD");
        createApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
        //modelNameList.add("Prolin");
        createApkRequest.setModelNameList(modelNameList);

        List<String> screenshotList = new ArrayList<>();
        screenshotList.add("C:\\TestApp1.png");
        screenshotList.add("C:\\TestApp2.png");
        screenshotList.add("C:\\TestApp3.png");
        createApkRequest.setScreenshotFiles(screenshotList);

        List<String> paramList = new ArrayList<>();
        paramList.add("C:\\TestApp_paramTemplate.xml");
        createApkRequest.setParamTemplateFiles(paramList);

        createApkRequest.setFeaturedImgPath("C:\\TestApp3.png");
        createApkRequest.setIconFilePath("C:\\TestApp3.png");

        Result<String>  result = developerApi.createApk(createApkRequest);
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testEditApk() {
        EditSingleApkRequest editApkRequest = new EditSingleApkRequest();
        editApkRequest.setApkId(1604907870191657L);
        editApkRequest.setApkName("TestAppV1.1");
        editApkRequest.setAppFilePath("C:\\TestApp_1.apk");
        editApkRequest.setApkType("P");
        editApkRequest.setShortDesc("test short descV3");
        editApkRequest.setDescription("test descriptionV3");
        editApkRequest.setReleaseNotes("This is release noteV3");
        editApkRequest.setAccessUrl("www.baidu-v3.com");

        List<String> categoryList = new ArrayList<>();
        categoryList.add("JD");
//        categoryList.add("WL_SK");
        editApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
        modelNameList.add("A920Pro");
        //modelNameList.add("Prolin");
        editApkRequest.setModelNameList(modelNameList);

        List<String> screenshotList = new ArrayList<>();
        screenshotList.add("C:\\TestApp111.png");
//        screenshotList.add("C:\\PAX_WORK\\测试安装包\\222.png");
        screenshotList.add("C:\\TestApp333.png");
        editApkRequest.setScreenshotFiles(screenshotList);

        List<String> paramList = new ArrayList<>();
        paramList.add("C:\\TestApp_paramTemplate2.xml");
        editApkRequest.setParamTemplateFiles(paramList);

        editApkRequest.setFeaturedImgPath("C:\\TestApp333.png");
        editApkRequest.setIconFilePath("C:\\TestApp33.png");

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
        developerApi.submitApk(1593832021950503L);
    }

    @Test
    public void testGetAppByPackageOrName() {
        Result<String> testApp = developerApi.getAppByPackageOrName(null, "testApp");
        System.out.println(testApp.toString());
    }
}
