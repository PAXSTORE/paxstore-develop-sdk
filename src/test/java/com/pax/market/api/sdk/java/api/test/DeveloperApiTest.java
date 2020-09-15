package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.developer.DeveloperApi;
import com.pax.market.api.sdk.java.api.developer.dto.CreateApkRequest;
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
        createApkRequest.setAppFilePath("D:\\2.apk");
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
}
