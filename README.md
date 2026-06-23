# PAXSTORE Developer API Java SDK


<br/>
<br/>

## Overview

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

## Sample Code

```
import com.pax.market.api.sdk.java.api.base.dto.*;
import com.pax.market.api.sdk.java.api.developer.DeveloperApi;

public class Sample {
	
	private String BASEURL = "https://api.whatspos.com/p-market-api";
	private String KEY = "YOUR KEY";
	private String SECRET = "YOUR SECRET";
	
	public Result<ApkInfoDTO> getApkInfo() {
	    	DeveloperApi developerApi = new DeveloperApi(BASEURL, KEY, SECRET);
    	    return developerApi.getApkById(1643270597771298L);
	}
}
```
### Appendix

**business category codes**

| Value | Label         | Description                                                     |
|:------|:--------------|:----------------------------------------------------------------|
| GYL   | SCM           | Supply Chain                                                    |
| WL    | Logistics     | Logistics                                                       |
| CY    | Dining/Chains | Dining/Chains                                                   |
| SH    | Life Service  | Life Service                                                    |
| JG    | Agency        | Agency                                                          |
| ...   | ...           | About other category, You can get from above getAppCategory API |

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
    <version>1.4.2</version>
</dependency>
```

## [DEVELOPER APIs](docs/DEVELOPER_API.md)  