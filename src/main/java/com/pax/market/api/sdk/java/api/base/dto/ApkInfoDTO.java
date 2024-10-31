package com.pax.market.api.sdk.java.api.base.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApkInfoDTO implements Serializable {
    private Long id;
    private Long appId;        // 应用编号
    private String appName;
    private String packageName;
    private String appStatus;
    private String status;        // 状态
    private Long versionCode;        // 版本代号
    private String versionName;        // 版本名称
    private String osType;
    private String apkType;
    private String apkFileType;
    private Long fileSize;
    private String displayFileSize;
    private String apkIconFileId;
    private Date submitDate;
    private Date updatedDate;
    private Date approvedDate;
    private Date createdDate;

    // 状态
    private Integer statusRequest;  //上下线的申请状态
    private String statusRequestComment; //上下线申请时的评论
    private String apkSignatureStatus;

    private ApkDetailDTO apkDetail;

    private Integer appChargeType;
    private Integer chargeMode;
    private BigDecimal appPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
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

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getApkType() {
        return apkType;
    }

    public void setApkType(String apkType) {
        this.apkType = apkType;
    }

    public String getApkFileType() {
        return apkFileType;
    }

    public void setApkFileType(String apkFileType) {
        this.apkFileType = apkFileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDisplayFileSize() {
        return displayFileSize;
    }

    public void setDisplayFileSize(String displayFileSize) {
        this.displayFileSize = displayFileSize;
    }

    public String getApkIconFileId() {
        return apkIconFileId;
    }

    public void setApkIconFileId(String apkIconFileId) {
        this.apkIconFileId = apkIconFileId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatusRequest() {
        return statusRequest;
    }

    public void setStatusRequest(Integer statusRequest) {
        this.statusRequest = statusRequest;
    }

    public String getStatusRequestComment() {
        return statusRequestComment;
    }

    public void setStatusRequestComment(String statusRequestComment) {
        this.statusRequestComment = statusRequestComment;
    }

    public String getApkSignatureStatus() {
        return apkSignatureStatus;
    }

    public void setApkSignatureStatus(String apkSignatureStatus) {
        this.apkSignatureStatus = apkSignatureStatus;
    }

    public ApkDetailDTO getApkDetail() {
        return apkDetail;
    }

    public void setApkDetail(ApkDetailDTO apkDetail) {
        this.apkDetail = apkDetail;
    }

    public Integer getAppChargeType() {
        return appChargeType;
    }

    public void setAppChargeType(Integer appChargeType) {
        this.appChargeType = appChargeType;
    }

    public Integer getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(Integer chargeMode) {
        this.chargeMode = chargeMode;
    }

    public BigDecimal getAppPrice() {
        return appPrice;
    }

    public void setAppPrice(BigDecimal appPrice) {
        this.appPrice = appPrice;
    }

    private static class ApkDetailDTO implements Serializable {
        private Long apkId;
        private String appName;
        private String shortDesc;
        private String keyWords;
        private String description;
        private String releaseNotes;
        private String screenshot0;
        private String screenshot1;
        private String screenshot2;
        private String screenshot3;
        private String screenshot4;
        private String screenShotType;
        private String featuredImg;
        private String accessUrl;
        //Release note attachment
        private String attachment;
        private String attachmentName;

        public Long getApkId() {
            return apkId;
        }

        public void setApkId(Long apkId) {
            this.apkId = apkId;
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

        public String getKeyWords() {
            return keyWords;
        }

        public void setKeyWords(String keyWords) {
            this.keyWords = keyWords;
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

        public String getScreenShotType() {
            return screenShotType;
        }

        public void setScreenShotType(String screenShotType) {
            this.screenShotType = screenShotType;
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
    }

}
