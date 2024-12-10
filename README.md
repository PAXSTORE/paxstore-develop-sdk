## Developer API

All APIs related to developer app operations are encapsulated in this class *com.pax.market.api.sdk.java.api.developer.DeveloperApi*.

**Constructors of DeveloperApi**

```
public DeveloperApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

| Name      | Type   | Description                                                  |
| :-------- | :----- | :----------------------------------------------------------- |
| baseUrl   | String | the base url of REST API                                     |
| apiKey    | String | the apiKey of developer, get this key from PAXSTORE admin console, refer to chapter Apply access rights |
| apiSecret | String | apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights |

## Apply access rights

You should make sure the marketplace admin has enabled the developer SDK access for you.

Below are the steps for developer to enable developer SDK access and get access key and access secret.

### Step 1

Log in to PAXSTORE developer console and click your account then click the **Developer Info**.

### Step 2

Click the **DEVELOPER SDK INTEGRATION** to show the developer SDk integration configuration page.

We know the developer SDK access is disabled by default. To enable it please click the enable/disable switch. And once user clicked the switch it will pop up a confirm dialog to let user confirm.

Click OK button to continue enabling the developer SDK access. Click the CANCEL button to cancel current operation to keep developer SDK access disabled.

After click OK button the developer SDK access is enabled and the access key is shown in the page. But the access secret is replaced by asterisks for security purpose.

### Step 3
## Developer API

All APIs related to developer app operations are encapsulated in this class *com.pax.market.api.sdk.java.api.developer.DeveloperApi*.

**Constructors of DeveloperApi**

```
public DeveloperApi(String baseUrl, String apiKey, String apiSecret);
```

**Constructor parameters description**

| Name      | Type   | Description                                                  |
| :-------- | :----- | :----------------------------------------------------------- |
| baseUrl   | String | the base url of REST API                                     |
| apiKey    | String | the apiKey of developer, get this key from PAXSTORE admin console, refer to chapter Apply access rights |
| apiSecret | String | apiSecret, get api secret from PAXSTORE admin console, refer to chapter Apply access rights |



### Upload an Apk
Create Apk by one step, it needs fully information of App and Apk.

**API**

```
public Result<String> uploadApk(CreateApkRequest createApkRequest)
```

**Input parameter(s) description**

| Parameter Name   | Type             | Nullable | Description                                         |
| :--------------- | :--------------- | :------- | :-------------------------------------------------- |
| createApkRequest | CreateApkRequest | false    | the create request object, the structure like below |

Structure of class CreateApkRequest

| Property Name         | Type          | Nullable | Description                                                                          |
|:----------------------| :------------ |:---------|:-------------------------------------------------------------------------------------|
| appName               | String        | false    | the name of app                                                                      |
| baseType              | String        | false    | the type of app, the values can be 'P' and 'N'. (P : Parameter App, N: Standard App) |
| chargeType            | Integer       | false    | the charge type of app,  the values can be 0 and 1. (0 : Free, 1: Paid App)          |
| price                 | BigDecimal    | true     | the price of app                                                                     |
| modelNameList         | List\<String> | false    | model names, the apk supported models                                                |
| categoryList          | List\<String> | false    | business category, please reference appendix part.                                   |
| appNameByVersion      | String        | true     | the name of app version                                                              |
| shortDesc             | String        | false    | short description                                                                    |
| description           | String        | false    |                                                                                      |
| releaseNotes          | String        | true     | release note                                                                         |
| appFile               | UploadedFileContent        | false    | the app file                                                                         |
| iconFile              | UploadedFileContent        | true     | the icon file                                                                        |
| featuredImgFile       | UploadedFileContent        | false    | the icon file                                                                        |
| attachment            | UploadedFileContent        | true     | the release note file(text format)                                                   |
| screenshotFileList    | List\<UploadedFileContent> | false    | the screenshots file, at least three pictures                                        |
| paramTemplateFileList | List\<UploadedFileContent> | true     | the parameter templates file path, mandatory when baseType is 'P'                    |


Structure of class UploadedFileContent

|Property Name| Type   | Description              |
|:---|:-------|:-------------------------|
|bytesContent| byte[] | bytes of file content    |
|name| String | name of file             |
|originalFilename| String | originalFilename of file |
|contentType| String | conent of file           |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  CreateApkRequest createApkRequest = new CreateApkRequest();
        createApkRequest.setAppFile(FileUtils.createUploadFile("C:\\TestApp.apk"));
        createApkRequest.setAppName("test");
        createApkRequest.setBaseType(APP_TYPE_NORMAL);
        //parameter should use APP_TYPE_PARAMETER
        //createApkRequest.setBaseType(APP_TYPE_PARAMETER);
        createApkRequest.setShortDesc("test short desc");
        createApkRequest.setDescription("test description");
        createApkRequest.setReleaseNotes("This is release note");
        createApkRequest.setChargeType(0);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("WL_PS");
        categoryList.add("WL_SK");
        createApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
        createApkRequest.setModelNameList(modelNameList);

        List<UploadedFileContent> screenshotList = new ArrayList<>();
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp1.png"));
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp2.png"));
        screenshotList.add(FileUtils.createUploadFile("C:\\TestApp3.png"));
        createApkRequest.setScreenshotFileList(screenshotList);
        
        /**List<UploadedFileContent> paramList = new ArrayList<>();
        paramList.add(FileUtils.createUploadFile("C:\\TestApp_paramTemplate.xml"));
        createApkRequest.setParamTemplateFileList(paramList);**/
        
        createApkRequest.setFeaturedImgFile(FileUtils.createUploadFile("C:\\TestApp3.png"));
        createApkRequest.setIconFile(FileUtils.createUploadFile("C:\\TestApp3.png"));

        developerApi.uploadApk(createApkRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter createApkRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1213,
	"message": "App name is mandatory"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```


**Possible client validation errors**

> <font color="red">Parameter CreateApkRequest can not be empty!</font>

**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 1011          | The app's developer signature is different from previous version |             |
| 1012          | Please sign your app with a self-signed certificate          |             |
| 1013          | The App signature certificate  is invalid                    |             |
| 1117          | App OS type must be same with previous version               |             |
| 1202          | App package name should be same with previous version        |             |
| 1205          | App is not editable                                          |             |
| 1209          | App detail is mandatory                                      |             |
| 1213          | App name is mandatory                                        |             |
| 1214          | App name is too long                                         |             |
| 1217          | App with same package name already exists                    |             |
| 1244          | App model is mandatory                                       |             |
| 1245          | App base type is mandatory                                   |             |
| 1247          | App icon is mandatory                                        |             |
| 1248          | App base type is invalid                                     |             |
| 1253          | Parameter template is mandatory                              |             |
| 1256          | Unsupported file type                                        |             |
| 1258          | App name is mandatory                                        |             |
| 1259          | App name is too long                                         |             |
| 1260          | Short description is mandatory                               |             |
| 1261          | Short description is too long                                |             |
| 1262          | Description is mandatory                                     |             |
| 1263          | Description is too long                                      |             |
| 1264          | Release notes is too long                                    |             |
| 1265          | At least 3 screenshots required                              |             |
| 1267          | Package name [com.pax.market.*] is not allowed               |             |
| 1273          | Only one pending app could exist at the same time            |             |
| 1274          | Package name [{0}] is not allowed                            |             |
| 1278          | App version already exists                                   |             |
| 1283          | Draft app version exists, unable to upload the new version   |             |
| 1297          | The app does not support to selected model                   |             |
| 1304          | Category is mandatory                                        |             |
| 1326          | App package name is too long                                 |             |
| 1625          | Version Name already exists                                  |             |
| 1700          | Model doesn't exist                                          |             |
| 1714          | Existed model status is not active                           |             |
| 2514          | Model is mandatory                                           |             |
| 6010          | The name in appinfo file should be same with previous version |             |
| 6100          | The APP_NAME in config file should be same with previous version |             |
| 6200          | The [info]-name in system.ini file should be same with previous version |             |
| 9203          | Chargeable app is not allowed                                |             |
| 9205          | Developer is not allowed in this market                      |             |
| 10102         | Do not upload signed file                                    |             |
| 29102         | Set up your Stripe account first                             |             |

### Create App 
Create App project with specific name simply, will get appId when success.

**API**

```
public Result<String> createApp(CreateSingleAppRequest createAppRequest)
```

**Input parameter(s) description**

| Parameter Name   | Type             | Nullable | Description                                         |
| :--------------- | :--------------- | :------- | :-------------------------------------------------- |
| createAppRequest | CreateSingleAppRequest | false    | the create request object, the structure like below |

Structure of class CreateSingleAppRequest

| Property Name      | Type          | Nullable | Description                                                                         |
| :----------------- |:--------------| :------- |:------------------------------------------------------------------------------------|
| appName            | String        | false    | the name of app, max lenth is 64                                                    |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
    CreateSingleAppRequest createAppRequest = new CreateSingleAppRequest();
        createAppRequest.setAppName("APP20240223");
        developerApi.createApp(createAppRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter appCreateRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1213,
	"message": "App name is mandatory"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": 1634922089414693
}
```
<br>

The Json structure shows like below.

| Property Name |Type| Description                             |
|:--------------|:---|:----------------------------------------|
| data          |Long| the id of app                           |
| businessCode  |Long| 0 means success, otherwise means failed |

**Possible client validation errors**

> <font color="red">Parameter appCreateRequest cannot be null!</font>

**Possible business codes**

| Business Code | Message                                                      | Description |
| :------------ | :----------------------------------------------------------- | :---------- |
| 1011          | The app's developer signature is different from previous version |             |
| 1012          | Please sign your app with a self-signed certificate          |             |
| 1013          | The App signature certificate  is invalid                    |             |
| 1213          | App name is mandatory                                        |             |
| 1214          | App name is too long                                         |             |
| 1217          | App with same package name already exists                    |             |
| 1248          | App base type is invalid                                     |             |
| 1248          | App base type is invalid                                     |             |

### getAppInfoByName
Get App information by packageName or appName.

Note: packageName and appName should not be null at same time

**API**

```
public Result<AppDetailDTO> getAppInfoByName(String packageName, String appName)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description         |
|:---------------|:-------| :------- |:--------------------|
| packageName    | String | false    | package name of app |
| appName        | String | false    | name of app |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  developerApi.getAppInfoByName("com.kibo.xunlian", "TestApp1");
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 135,
	"message": "Invalid parameter or missing parameter"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"id": 1634804562919462,
		"name": "TestApp0920",
		"type": "G",
		"status": "A"
	}
}
```

<br>

The type in dataSet of is AppDetailDTO. And the structure shows like below.

| Property Name |Type| Description                                                                  |
|:--------------|:---|:-----------------------------------------------------------------------------|
| id            |Long| the id of app                                                                |
| name          |String| the name of app                                                              |
| type          |String| the type of app , value can be G(General App),S(Solution App)                |
| status        |String| the status of App, value can be one of A(Active), I(INACTIVE) and S(Suspend) |

<br>

**Possible business codes**

| Business Code | Message                                                                 | Description |
| :------------ |:------------------------------------------------------------------------| :---------- |
| 135          | Invalid parameter or missing parameter                                  |             |
| 1325          | App package name is mandatory                                                       |             |
| 1011          | The app's developer signature is different from previous version        |             |
| 1012          | Please sign your app with a self-signed certificate                     |             |
| 1013          | The App signature certificate  is invalid                               |             |

### Create an Apk
Create an Apk with related information

**API**

```
public Result<String> createApk(CreateSingleApkRequest createApkRequest)
```

**Input parameter(s) description**

| Parameter Name   | Type             | Nullable | Description                                         |
| :--------------- | :--------------- | :------- | :-------------------------------------------------- |
| createApkRequest | CreateSingleApkRequest | false    | the create request object, the structure like below |

Structure of class CreateSingleApkRequest

| Property Name      | Type | Nullable | Description                                                                          |
|:-------------------|:-----|:---------|:-------------------------------------------------------------------------------------|
| appId              | Long | false    | the id of app                                                                        |
| apkName            | String | false    | the name of apk                                                                      |
| apkType            | String        | false    | the type of app, the values can be 'P' and 'N'. (P : Parameter App, N: Standard App) |
| modelNameList      | List\<String> | false    | model names, the apk supported models                                                |
| categoryList       | List\<String> | false    | business category,please reference appendix part                                     |
| shortDesc          | String        | false    | short description                                                                    |
| description        | String        | false    |                                                                                      |
| releaseNotes       | String        | true     | release note                                                                         |
| appFile            | UploadedFileContent        | false    | the app file                                                                         |
| iconFile           | UploadedFileContent        | true     | the icon file                                                                        |
| featuredImgFile    | UploadedFileContent        | true     | the featured image file                                                              |
| attachment            | UploadedFileContent        | true     | the release note file(text format)                                                   |
| accessUrl          | String        | true     | url of access                                                                        |
| screenshotFileList    | List\<UploadedFileContent> | false    | the screenshots files, at least three pictures                                       |
| paramTemplateFileList | List\<UploadedFileContent> | true     | the parameter templates file , template file is mandantory when ApkType is 'P'       |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  CreateSingleApkRequest createApkRequest = new CreateSingleApkRequest();
        createApkRequest.setAppId(1598740513161250L);
        createApkRequest.setApkName("Tiktok-V1.0");
        createApkRequest.setAppFile(FileUtils.createUploadFile("C:\\TestApp.apk"));
        //createApkRequest.setApkType(APP_TYPE_NORMAL);
        createApkRequest.setApkType(APP_TYPE_PARAMETER);
        createApkRequest.setShortDesc("test short desc");
        createApkRequest.setDescription("test description");
        createApkRequest.setReleaseNotes("This is release note");
        createApkRequest.setAccessUrl("www.baidu.com");
        // business category dictionary
        // You can get it from app edit page in develop center or admin platform
        List<String> categoryList = new ArrayList<>();
        categoryList.add("WL_PS");
        categoryList.add("WL_SK");
        createApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        //model of device
        //About others models, You can get it from app edit page in develop center or admin platform
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

        developerApi.createApk(createApkRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter createApkRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1213,
	"message": "App name is mandatory"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": 1634925723779110
}
```
The JSON structure shows like below.
<br>
| Parameter Name | Type | Nullable | Description |
|:---------------|:-----| :------- |:------------|
| data           | Long | false    | id of APK   |


**Possible client validation errors**

> <font color="red">Parameter CreateApkRequest can not be empty!</font>

**Possible business codes**

| Business Code | Message                                                                 | Description |
| :------------ |:------------------------------------------------------------------------| :---------- |
| 135          | Invalid parameter or missing parameter                                  |             |
| 1000          | The app not found                                                       |             |
| 1011          | The app's developer signature is different from previous version        |             |
| 1012          | Please sign your app with a self-signed certificate                     |             |
| 1013          | The App signature certificate  is invalid                               |             |
| 1117          | App OS type must be same with previous version                          |             |
| 1202          | App package name should be same with previous version                   |             |
| 1205          | App is not editable                                                     |             |
| 1209          | App detail is mandatory                                                 |             |
| 1213          | Apk name is mandatory                                                   |             |
| 1214          | Apk name is too long                                                    |             |
| 1217          | App with same package name already exists                               |             |
| 1244          | App model is mandatory                                                  |             |
| 1247          | App icon is mandatory                                                   |             |
| 1253          | Parameter template is mandatory                                         |             |
| 1256          | Unsupported file type                                                   |             |
| 1258          | App name is mandatory                                                   |             |
| 1259          | App name is too long                                                    |             |
| 1260          | Short description is mandatory                                          |             |
| 1261          | Short description is too long                                           |             |
| 1262          | Description is mandatory                                                |             |
| 1263          | Description is too long                                                 |             |
| 1264          | Release notes is too long                                               |             |
| 1265          | At least 3 screenshots required                                         |             |
| 1267          | Package name [com.pax.market.*] is not allowed                          |             |
| 1273          | Only one pending app could exist at the same time                       |             |
| 1274          | Package name [{0}] is not allowed                                       |             |
| 1278          | App version already exists                                              |             |
| 1283          | Draft app version exists, unable to upload the new version              |             |
| 1297          | The app does not support to selected model                              |             |
| 1304          | Category is mandatory                                                   |             |
| 1326          | App package name is too long                                            |             |
| 1625          | Version Name already exists                                             |             |
| 1700          | Model doesn't exist                                                     |             |
| 1714          | Existed model status is not active                                      |             |
| 2514          | Model is mandatory                                                      |             |
| 6010          | The name in appinfo file should be same with previous version           |             |
| 6100          | The APP_NAME in config file should be same with previous version        |             |
| 6200          | The [info]-name in system.ini file should be same with previous version |             |
| 9203          | Chargeable app is not allowed                                           |             |
| 9205          | Developer is not allowed in this market                                 |             |
| 10102         | Do not upload signed file                                               |             |
| 29102         | Set up your Stripe account first                                        |             |

### Edit an Apk
Edit the information of an Apk

**API**

```
public Result<String> editApk(EditSingleApkRequest editApkRequest)
```

**Input parameter(s) description**

| Parameter Name   | Type             | Nullable | Description                                       |
| :--------------- | :--------------- | :------- |:--------------------------------------------------|
| editApkRequest | EditSingleApkRequest | false    | the edit request object, the structure like below |

Structure of class CreateSingleApkRequest

| Property Name      | Type | Nullable | Description                                                                          |
|:-------------------|:-----|:---------|:-------------------------------------------------------------------------------------|
| apkId              | Long | false    | the id of app                                                                        |
| apkName            | String | false    | the name of apk                                                                      |
| apkType            | String        | false    | the type of app, the values can be 'P' and 'N'. (P : Parameter App, N: Standard App) |
| modelNameList      | List\<String> | false    | model names, the apk supported models                                                |
| categoryList       | List\<String> | false    | business category                                                                    |
| shortDesc          | String        | false    | short description                                                                    |
| description        | String        | false    |                                                                                      |
| releaseNotes       | String        | true     | release note                                                                         |
| appFile            | UploadedFileContent        | true     | the appfile                                                                          |
| iconFile           | UploadedFileContent        | false    | the icon file                                                                        |
| featuredImg        | UploadedFileContent        | true     | the featured image file                                                              |
| attachment            | UploadedFileContent        | true     | the release note file(text format)                                                   |
| accessUrl          | String        | true     | url of access                                                                        |
| screenshotFileList    | List\<UploadedFileContent> | false    | the screenshots files                                                                |
| paramTemplateFileList | List\<UploadedFileContent> | true     | the parameter templates file, mandantory when ApkType is 'P'                         |


**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  EditSingleApkRequest editApkRequest = new EditSingleApkRequest();
        editApkRequest.setApkId(1598744592121890L);
        editApkRequest.setApkName("Tiktok");
        editApkRequest.setAppFile(FileUtils.createUploadFile("C:\\TestApp_1.apk"));
        editApkRequest.setApkType(APP_TYPE_PARAMETER);
        editApkRequest.setShortDesc("test short descV2");
        editApkRequest.setDescription("test descriptionV2");
        editApkRequest.setReleaseNotes("This is release noteV2");
        editApkRequest.setAccessUrl("www.baidu-v2.com");

        List<String> categoryList = new ArrayList<>();
        categoryList.add("WL_PS");
        categoryList.add("WL_SK");
        editApkRequest.setCategoryList(categoryList);
        List<String> modelNameList = new ArrayList<>();
        modelNameList.add("A920");
        modelNameList.add("A920Pro");
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

        developerApi.editApk(editApkRequest);
```

**Client side validation failed sample result(JSON formatted)**

```
{
	"businessCode": -1,
	"validationErrors": ["Parameter editApkRequest cannot be null!"]
}
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1002,
	"message": "APK not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```


**Possible client validation errors**

> <font color="red">Parameter editApkRequest can not be empty!</font>

**Possible business codes**

| Business Code | Message                                                                 | Description |
| :------------ |:------------------------------------------------------------------------| :---------- |
| 135          | Invalid parameter or missing parameter                                  |             |
| 1002          | APK not found                                                       |             |
| 1011          | The app's developer signature is different from previous version        |             |
| 1012          | Please sign your app with a self-signed certificate                     |             |
| 1013          | The App signature certificate  is invalid                               |             |
| 1117          | App OS type must be same with previous version                          |             |
| 1202          | App package name should be same with previous version                   |             |
| 1205          | App is not editable                                                     |             |
| 1209          | App detail is mandatory                                                 |             |
| 1213          | App name is mandatory                                                   |             |
| 1214          | App name is too long                                                    |             |
| 1217          | App with same package name already exists                               |             |
| 1244          | App model is mandatory                                                  |             |
| 1247          | App icon is mandatory                                                   |             |
| 1253          | Parameter template is mandatory                                         |             |
| 1256          | Unsupported file type                                                   |             |
| 1258          | App name is mandatory                                                   |             |
| 1259          | App name is too long                                                    |             |
| 1260          | Short description is mandatory                                          |             |
| 1261          | Short description is too long                                           |             |
| 1262          | Description is mandatory                                                |             |
| 1263          | Description is too long                                                 |             |
| 1264          | Release notes is too long                                               |             |
| 1265          | At least 3 screenshots required                                         |             |
| 1267          | Package name [com.pax.market.*] is not allowed                          |             |
| 1273          | Only one pending app could exist at the same time                       |             |
| 1274          | Package name [{0}] is not allowed                                       |             |
| 1278          | App version already exists                                              |             |
| 1283          | Draft app version exists, unable to upload the new version              |             |
| 1297          | The app does not support to selected model                              |             |
| 1304          | Category is mandatory                                                   |             |
| 1326          | App package name is too long                                            |             |
| 1625          | Version Name already exists                                             |             |
| 1700          | Model doesn't exist                                                     |             |
| 1714          | Existed model status is not active                                      |             |
| 2514          | Model is mandatory                                                      |             |
| 6010          | The name in appinfo file should be same with previous version           |             |
| 6100          | The APP_NAME in config file should be same with previous version        |             |
| 6200          | The [info]-name in system.ini file should be same with previous version |             |
| 9203          | Chargeable app is not allowed                                           |             |
| 9205          | Developer is not allowed in this market                                 |             |
| 10102         | Do not upload signed file                                               |             |
| 29102         | Set up your Stripe account first                                        |             |

### Submit Apk
Submit Apk for administrator approval

**API**

```
public Result<String> submitApk(Long apkId)
```

**Input parameter(s) description**

| Parameter Name   | Type   | Nullable | Description |
| :--------------- |:-------| :------- |:------------|
| apkId | Long | false    | id of apk   |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  developerApi.submitApk(1598744592121890L);
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1002,
	"message": "APK not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                                                          | Description |
|:--------------|:-----------------------------------------------------------------| :---------- |
| 135           | Invalid parameter or missing parameter                           |             |
| 1000          | APP not found                                                    |             |
| 1002          | APK not found                                                    |             |
| 1011          | The app's developer signature is different from previous version |             |
| 1012          | Please sign your app with a self-signed certificate              |             |
| 1013          | The App signature certificate  is invalid                        |             |


### Delete Apk
Delete Apk by apkId

**API**

```
public Result<String> deleteApk(Long apkId)
```

**Input parameter(s) description**

| Parameter Name   | Type   | Nullable | Description |
| :--------------- |:-------| :------- |:------------|
| apkId | Long | false    | id of apk   |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  developerApi.deleteApk(1598563819716651L);
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1002,
	"message": "APK not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                                                          | Description |
|:--------------|:-----------------------------------------------------------------| :---------- |
| 131           | Insufficient access right                           |             |
| 135           | Invalid parameter or missing parameter                           |             |
| 1002          | APK not found                                                    |             |
| 1205          | App is not editable                                                    |             |
| 1011          | The app's developer signature is different from previous version |             |
| 1012          | Please sign your app with a self-signed certificate              |             |
| 1013          | The App signature certificate  is invalid                        |             |

### Delete App
Delete App project by appId

**API**

```
public Result<String> deleteApp(Long appId)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description |
|:---------------|:-------| :------- |:------------|
| appId          | Long | false    | id of app   |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  developerApi.deleteApp(2598004800782372L);
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 1000,
	"message": "APP not found"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0
}
```

**Possible business codes**

| Business Code | Message                                                          | Description |
|:--------------|:-----------------------------------------------------------------| :---------- |
| 131           | Insufficient access right                           |             |
| 135           | Invalid parameter or missing parameter                           |             |
| 1000          | APP not found                                                    |             |
| 1205          | App is not editable                                                    |             |
| 1011          | The app's developer signature is different from previous version |             |
| 1012          | Please sign your app with a self-signed certificate              |             |
| 1013          | The App signature certificate  is invalid                        |             |

### Get code by type
App category list, it is dictionary

**API**

```
Result<CodeInfoDTO> getCodeByType(String codeType)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description                                                        |
|:---------------|:-------| :------- |:-------------------------------------------------------------------|
| codeType          | String | false    | codeType of dictionary, Value can be 'app_category','product_type' |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  Result<CodeInfoDTO> result = developerApi.getCodeByType("app_category");
  PageInfo<CodeInfoDTO> pageInfo = result.getPageInfo();
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 135,
	"message": "Request parameter is missing or invalid"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"pageInfo": {
		"pageNo": 1,
		"limit": 22,
		"totalCount": 22,
		"hasNext": false,
		"dataSet": [{
			"id": 1200,
			"type": "app_category",
			"value": "GYL_DK",
			"label": "Loan",
			"description": "Loan",
			"lang": "en",
			"sort": 0
		}, {
			"id": 2200,
			"type": "app_category",
			"value": "WL_PS",
			"label": "Delivery",
			"description": "Delivery",
			"lang": "en",
			"sort": 0
		}, {
			"id": 4100,
			"type": "app_category",
			"value": "SH_HY",
			"label": "Membership",
			"description": "Membership",
			"lang": "en",
			"sort": 0
		}]
	}
}
```
Structure of class PageInfo

|Property Name| Type              | Description              |
|:---|:------------------|:-------------------------|
|pageNo| Integer           | bytes of file content    |
|limit| Integer           | name of file             |
|totalCount| Integer           | originalFilename of file |
|dataSet| List<CodeInfoDTO> | code list                |

Structure of class CodeInfoDTO

|Property Name| Type              | Description                       |
|:---|:------------------|:----------------------------------|
|type| String            | code type                         |
|value| String           | code value, used by createApk API |
|label| String | label of code                     |
|description| String | description                       |


**Possible business codes**

| Business Code | Message                                                          | Description |
|:--------------|:-----------------------------------------------------------------| :---------- |
| 131           | Insufficient access right                           |             |
| 135           | Invalid parameter or missing parameter                           |             | 
| 1011          | The app's developer signature is different from previous version |             |
| 1012          | Please sign your app with a self-signed certificate              |             |
| 1013          | The App signature certificate  is invalid                        |             |

### Get APK
get apk by apkId

**API**

```
Result<ApkInfoDTO> getApkById(Long apkId)
```

**Input parameter(s) description**

| Parameter Name | Type   | Nullable | Description |
|:---------------|:-------| :------- |:------------|
| apkId          | String | false    | apkId       |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  Result<ApkInfoDTO> apkInfo = developerApi.getApkById(1643270597771298L);
  ApkInfoDTO data = apkInfo.getData();
```

**Server side validation failed sample result(JSON formatted)**

```
{
	"businessCode": 135,
	"message": "Request parameter is missing or invalid"
}
```

**Successful sample result(JSON formatted)**

```
{
	"businessCode": 0,
	"data": {
		"apkId": 1643502907686947,
		"apkType": "N",
		"apkIconFileId": "https://www.dev.paxsit.com/group1/M00/02/59/wKjIGWcjUjmADLs9AAAIOm3Mexw5393549",
		"displayFileSize": "40.9 MB",
		"versionName": "7.9",
		"allowUpdateParamTemplate": true,
		"paramTemplateNameList": [],
		"apkCategoryList": ["GYL_DK", "GYL_JZ", "GYL_SK"],
		"apkModelList": [1],
		"apkModelNameList": ["A920","A930"],
		"signatureProvider": "NONE",
		"minSdkVersion": "21",
		"storeSdkVersion": null,
		"apkFileFactoryList": [],
		"appName": "七猫免费小说",
		"shortDesc": "一句话简介",
		"description": "描述",
		"releaseNotes": null,
		"screenshot0": "https://www.dev.paxsit.com/group1/M00/02/59/wKjIGWcjO5qAC6f2AAPHv517xvo197.png",
		"screenshot1": "https://www.dev.paxsit.com/group1/M00/02/59/wKjIGWcjO5uAPYrUAAdFKTEjRV4261.png",
		"screenshot2": "https://www.dev.paxsit.com/group1/M00/02/59/wKjIGWcjO5uAA8uOAASbj8LASiw607.png",
		"screenshot3": null,
		"screenshot4": null,
		"featuredImg": null,
		"accessUrl": null,
		"attachment": null,
		"attachmentName": null,
		"appId": 1643490278637602,
		"packageName": "com.kmxs.reader",
		"price": null,
		"currency": null,
		"chargeType": 0,
		"freeTrialDay": null,
		"osType": "A",
		"appType": "G",
		"disableApkTypeChange": true,
		"isFirstApkVersion": false,
		"chargeMode": null,
		"versionCode":10020,
		"status":"O"
	}
}
```
Structure of data field

| Property Name | Type    | Description      |
|:--------------|:--------|:-----------------|
| businessCode          | Integer | business code    |
| data         | ApkInfoDTO  | apk informations |

Structure of class ApkInfoDTO

| Property Name            | Type            | Description                                                                      |
|:-------------------------|:----------------|:---------------------------------------------------------------------------------|
| apkId                    | Long            | apkId                                                                            |
| apkType                  | String          | 'P' is PARAMETER_APP, 'N' is NORMAL_APP                                          |
| apkIconFileId            | String          | icon file url                                                                    |
| displayFileSize          | String          | APK file size, like '40.0MB'                                                     |
| versionName              | String          | version name of APK                                                              |
| allowUpdateParamTemplate | Boolean         |                                                                                  |
| paramTemplateNameList    | List<String>    | templateName list                                                                |
| apkCategoryList          | List<String>    | category list                                                                    |
| apkModelList             | List<Long>      | model id list                                                                    |
| apkModelNameList             | List<String>    | model name list                                                                  |
| signatureProvider        | String          | signature provider                                                               |
| minSdkVersion            | String          | minimum version of SDK                                                           |
| apkFileFactoryList       | List<ApkFileVo> | apkFile for support multi vendor factory                                         |
| appName                  | String          | app name                                                                         |
| shortDesc                | String          | shortDesc of apk                                                                 |
| description              | String          | APK status                                                                       |
| releaseNotes             | String          | release Notes                                                                    |
| screenshot0              | String          | picture url                                                                      |
| screenshot1              | String          | picture url                                                                      |
| screenshot2              | String          | picture url                                                                      |
| screenshot3              | String          | picture url                                                                      |
| screenshot4              | String          | picture url                                                                      |
| featuredImg              | String          | featured image url                                                               |
| accessUrl                | String          | apk signature status                                                             |
| attachment               | String          | attachment url of release note file(text file)                                   |
| attachmentName           | String          | attachment name of release note(text file)                                       |
| appId                    | Long            | appId                                                                            |
| packageName              | String          | package name of app                                                              |
| price                    | BigDecimal      | price of app                                                                     |
| currency                 | String          | currency of app, such as 'USD'                                                   |
| chargeType               | Integer         | 0 is free , 1 is not free                                                        |
| freeTrialDay             | Integer         | such as 100                                                                      |
| osType                   | String          | 'A' is Android ,'T' is tranditional, 'NIL' is None                               |
| appType                  | String          | 'G' is genernal app, 'S' is industry solution                                    |
| disableApkTypeChange     | Boolean         |                                                                                  |
| isFirstApkVersion        | Boolean         |                                                                                  |
| chargeMode               | String          | 0 is TERMINAL_INSTALLED, 1 is QUANTITY                                           |
| versionCode              | Long            |                                                                                  |
| status                   | String          | 'O' is ONLINE, 'U' is UNAVAILABLE, 'R' is REJECTED, 'P' is PENDING, 'D' is DRAFT |

Structure of class ApkFileVo

| Property Name | Type            | Description                             |
|:--------------|:----------------|:----------------------------------------|
| factoryId          | Long            | factory id                              |
| factoryName         | String          | factoryName |
| name         | String          | name                           |

**Possible business codes**

| Business Code | Message                                                          | Description |
|:--------------|:-----------------------------------------------------------------| :---------- |
| 131           | Insufficient access right                           |             |
| 135           | Invalid parameter or missing parameter                           |             | 
| 1011          | The app's developer signature is different from previous version |             |
| 1012          | Please sign your app with a self-signed certificate              |             |
| 1013          | The App signature certificate  is invalid                        |             |


### Appendix

**business category codes**

| Value | Label         | Description                                                                                 |
|:------|:--------------|:--------------------------------------------------------------------------------------------|
| GYL   | SCM           | Supply Chain                                                                                |
| WL    | Logistics     | Logistics                                                                                   |
| CY    | Dining/Chains | Dining/Chains                                                                               |
| SH    | Life Service  | Life Service                                                                                |
| JG    | Agency        | Agency                                                                                      |
| ...   | ...           | About other category, You can get it from app edit page in develop center or admin platform |

**Model of device**

| Value   | Description                                                                                |
|:--------|:-------------------------------------------------------------------------------------------|
| A920    | A920                                                                                       |
| A920MAX | A920MAX                                                                                    |
| ...     | About others models, You can get it from app edit page in develop center or admin platform |

## Intergrate with SDK

The minimal JDK version is 1.8.
To integrate with this SDK please add below dependency by updating the pom.xml file in your maven project.

```
<dependency>
    <groupId>com.whatspos.sdk</groupId>
    <artifactId>3rdsys-developerapi</artifactId>
    <version>1.2.1</version>
</dependency>
```