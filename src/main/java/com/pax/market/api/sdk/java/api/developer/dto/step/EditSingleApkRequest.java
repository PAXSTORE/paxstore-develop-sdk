package com.pax.market.api.sdk.java.api.developer.dto.step;

import com.pax.market.api.sdk.java.api.io.UploadedFileContent;

import java.io.Serializable;
import java.util.List;

/**
 * 2 * @Author: maozhengwen
 * 3 * @Date: 2024/02/06
 * 4
 */
public class EditSingleApkRequest implements Serializable {
    private static final long serialVersionUID = -8497600704617845846L;
    private Long apkId;
    /**
     * P:   PAYMENT_APP.
     * N:   NORMAL_APP.
     */
    private String apkType;
    private String apkName;
    private List<String> modelNameList;
    private List<String> categoryList;
    private String shortDesc;
    private String description;
    private String releaseNotes;
    private String accessUrl;
    private List<String> paramTemplateList;

    private UploadedFileContent appFile;
    private UploadedFileContent iconFile;
    private UploadedFileContent featuredImgFile;
    private List<UploadedFileContent> screenshotFileList;
    private List<UploadedFileContent> paramTemplateFileList;


    public List<String> getModelNameList() {
        return modelNameList;
    }

    public void setModelNameList(List<String> modelNameList) {
        this.modelNameList = modelNameList;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
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

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public List<String> getParamTemplateList() {
        return paramTemplateList;
    }

    public void setParamTemplateList(List<String> paramTemplateList) {
        this.paramTemplateList = paramTemplateList;
    }

    public String getApkType() {
        return apkType;
    }

    public void setApkType(String apkType) {
        this.apkType = apkType;
    }

    public Long getApkId() {
        return apkId;
    }

    public void setApkId(Long apkId) {
        this.apkId = apkId;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public UploadedFileContent getAppFile() {
        return appFile;
    }

    public void setAppFile(UploadedFileContent appFile) {
        this.appFile = appFile;
    }

    public UploadedFileContent getIconFile() {
        return iconFile;
    }

    public void setIconFile(UploadedFileContent iconFile) {
        this.iconFile = iconFile;
    }

    public UploadedFileContent getFeaturedImgFile() {
        return featuredImgFile;
    }

    public void setFeaturedImgFile(UploadedFileContent featuredImgFile) {
        this.featuredImgFile = featuredImgFile;
    }

    public List<UploadedFileContent> getScreenshotFileList() {
        return screenshotFileList;
    }

    public void setScreenshotFileList(List<UploadedFileContent> screenshotFileList) {
        this.screenshotFileList = screenshotFileList;
    }

    public List<UploadedFileContent> getParamTemplateFileList() {
        return paramTemplateFileList;
    }

    public void setParamTemplateFileList(List<UploadedFileContent> paramTemplateFileList) {
        this.paramTemplateFileList = paramTemplateFileList;
    }
}
