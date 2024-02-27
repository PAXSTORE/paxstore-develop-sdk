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

Click the eye icon in developer SDK integration configuration page to get the access secret. It will show a dialog .

Please click OK button. And it will show the access secret instead of asterisks.

Please keep the access key and access secret safely. Once the access key or access secret leaks please goto developer SDK integration configuration page to disable developer SDK access or reset the access key and access secret.

## Intergrate with SDK

The minimal JDK version is 1.8.
To integrate with this SDK please add below dependency by updating the pom.xml file in your maven project.

```
<dependency>
    <groupId>com.whatspos.sdk</groupId>
    <artifactId>paxstore-developerapi</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Upload an apk

**API**

```
public Result<String> uploadApk(CreateApkRequest createApkRequest)
```

**Input parameter(s) description**

| Parameter Name   | Type             | Nullable | Description                                         |
| :--------------- | :--------------- | :------- | :-------------------------------------------------- |
| createApkRequest | CreateApkRequest | false    | the create request object, the structure like below |

Structure of class CreateApkRequest

| Property Name      | Type          | Nullable | Description                                                  |
| :----------------- | :------------ | :------- | :----------------------------------------------------------- |
| appName            | String        | false    | the name of app                                              |
| baseType           | String        | false    | the type of app, the values can be 'P' and 'N'. (P : Parameter App, N: Standard App) |
| chargeType         | Integer       | false    | the charge type of app,  the values can be 0 and 1. (0 : Free, 1: Paid App) |
| price              | BigDecimal    | true     | the price of app                                             |
| modelNameList      | List\<String> | false    | model names, the apk supported models                        |
| categoryList       | List\<String> | false    | business category                                            |
| appNameByVersion   | String        | true     | the name of app version                                      |
| shortDesc          | String        | false    | short description                                            |
| description        | String        | false    |                                                              |
| releaseNotes       | String        | true     | release note                                                 |
| appFilePath        | String        | false    | the appfile path                                             |
| iconFilePath       | String        | true     | the icon file path                                           |
| screenshotFiles    | List\<String> | false    | the screenshots file path                                    |
| paramTemplateFiles | List\<String> | true     | the parameter templates file path                            |

**Sample codes**

```
DeveloperApi developerApi = new DeveloperApi("https://api.whatspos.com/p-market-api", "7AN2R0ROMLCOZI39H0MV", "I43OHYX91TL96IB7324E0FP2IG5YSWZGFJOUZIKY");
  CreateApkRequest createApkRequest = new CreateApkRequest();
        createApkRequest.setAppFilePath("D:\\test.apk");
        createApkRequest.setAppName("test");
        createApkRequest.setBaseType("N");
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

        List<String> screenshotList = new ArrayList<>();
        screenshotList.add("D:\\1.jpg");
        screenshotList.add("D:\\2.jpg");
        screenshotList.add("D:\\3.jpg");
        createApkRequest.setScreenshotFiles(screenshotList);

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