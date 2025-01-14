package com.pax.market.api.sdk.java.api.base.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ApkInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /** apk info */
    private Long apkId;
    private String apkType;
    private String apkIconFileId;
    private String displayFileSize;
    private String versionName;
    private Boolean allowUpdateParamTemplate;

    private List<String> paramTemplateNameList;
    /** 分类列表 -这里只返回value*/
    private List<String> apkCategoryList;
    /** 机型列表 -这里只返回modelId */
    private List<Long> apkModelList;
    /** 机型列表 -这里只返回modelName */
    private List<String> apkModelNameList;

    /** apk file info  */
    private String signatureProvider;
    private String minSdkVersion;
    private String storeSdkVersion;
    /** 开启多厂商时对应apk文件需要的厂商信息 */
    private List<ApkFileVo> apkFileFactoryList;

    /** apk detail info */
    private String appName;
    private String shortDesc;
    private String description;
    private String releaseNotes;
    private String screenshot0;
    private String screenshot1;
    private String screenshot2;
    private String screenshot3;
    private String screenshot4;
    private String featuredImg;
    private String accessUrl;
    private String attachment;
    private String attachmentName;


    /** app info **/
    private Long appId;
    private String packageName;
    private BigDecimal price;
    private String currency;
    private Integer chargeType;
    private Integer freeTrialDay;
    private String osType;
    /** 应用类型 General、Solution **/
    private String appType;
    /** 禁止更改应用类型 */
    private Boolean disableApkTypeChange;
    /** 当前没有上线版本 */
    private Boolean isFirstApkVersion;

    /** solution app charge mode**/
    private Integer chargeMode;
    private String status;
    private Long versionCode;

    private static class ApkFileVo implements Serializable{
        private static final long serialVersionUID = 1L;
        private Long factoryId;
        private String factoryName;
        private String name;

        public Long getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(Long factoryId) {
            this.factoryId = factoryId;
        }

        public String getFactoryName() {
            return factoryName;
        }

        public void setFactoryName(String factoryName) {
            this.factoryName = factoryName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Long getApkId() {
        return apkId;
    }

    public void setApkId(Long apkId) {
        this.apkId = apkId;
    }

    public String getApkType() {
        return apkType;
    }

    public void setApkType(String apkType) {
        this.apkType = apkType;
    }

    public String getApkIconFileId() {
        return apkIconFileId;
    }

    public void setApkIconFileId(String apkIconFileId) {
        this.apkIconFileId = apkIconFileId;
    }

    public String getDisplayFileSize() {
        return displayFileSize;
    }

    public void setDisplayFileSize(String displayFileSize) {
        this.displayFileSize = displayFileSize;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Boolean getAllowUpdateParamTemplate() {
        return allowUpdateParamTemplate;
    }

    public void setAllowUpdateParamTemplate(Boolean allowUpdateParamTemplate) {
        this.allowUpdateParamTemplate = allowUpdateParamTemplate;
    }

    public List<String> getParamTemplateNameList() {
        return paramTemplateNameList;
    }

    public void setParamTemplateNameList(List<String> paramTemplateNameList) {
        this.paramTemplateNameList = paramTemplateNameList;
    }

    public List<String> getApkCategoryList() {
        return apkCategoryList;
    }

    public void setApkCategoryList(List<String> apkCategoryList) {
        this.apkCategoryList = apkCategoryList;
    }

    public List<Long> getApkModelList() {
        return apkModelList;
    }

    public void setApkModelList(List<Long> apkModelList) {
        this.apkModelList = apkModelList;
    }

    public String getSignatureProvider() {
        return signatureProvider;
    }

    public void setSignatureProvider(String signatureProvider) {
        this.signatureProvider = signatureProvider;
    }

    public String getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(String minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public String getStoreSdkVersion() {
        return storeSdkVersion;
    }

    public void setStoreSdkVersion(String storeSdkVersion) {
        this.storeSdkVersion = storeSdkVersion;
    }

    public List<ApkFileVo> getApkFileFactoryList() {
        return apkFileFactoryList;
    }

    public void setApkFileFactoryList(List<ApkFileVo> apkFileFactoryList) {
        this.apkFileFactoryList = apkFileFactoryList;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseNotes() {
        return releaseNotes;
    }

    public void setReleaseNotes(String releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public String getScreenshot0() {
        return screenshot0;
    }

    public void setScreenshot0(String screenshot0) {
        this.screenshot0 = screenshot0;
    }

    public String getScreenshot1() {
        return screenshot1;
    }

    public void setScreenshot1(String screenshot1) {
        this.screenshot1 = screenshot1;
    }

    public String getScreenshot2() {
        return screenshot2;
    }

    public void setScreenshot2(String screenshot2) {
        this.screenshot2 = screenshot2;
    }

    public String getScreenshot3() {
        return screenshot3;
    }

    public void setScreenshot3(String screenshot3) {
        this.screenshot3 = screenshot3;
    }

    public String getScreenshot4() {
        return screenshot4;
    }

    public void setScreenshot4(String screenshot4) {
        this.screenshot4 = screenshot4;
    }

    public String getFeaturedImg() {
        return featuredImg;
    }

    public void setFeaturedImg(String featuredImg) {
        this.featuredImg = featuredImg;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getFreeTrialDay() {
        return freeTrialDay;
    }

    public void setFreeTrialDay(Integer freeTrialDay) {
        this.freeTrialDay = freeTrialDay;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Boolean getDisableApkTypeChange() {
        return disableApkTypeChange;
    }

    public void setDisableApkTypeChange(Boolean disableApkTypeChange) {
        this.disableApkTypeChange = disableApkTypeChange;
    }

    public Boolean getFirstApkVersion() {
        return isFirstApkVersion;
    }

    public void setFirstApkVersion(Boolean firstApkVersion) {
        isFirstApkVersion = firstApkVersion;
    }

    public Integer getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(Integer chargeMode) {
        this.chargeMode = chargeMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public List<String> getApkModelNameList() {
        return apkModelNameList;
    }

    public void setApkModelNameList(List<String> apkModelNameList) {
        this.apkModelNameList = apkModelNameList;
    }
}
