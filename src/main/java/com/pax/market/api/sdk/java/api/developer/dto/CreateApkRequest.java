package com.pax.market.api.sdk.java.api.developer.dto;

import com.pax.market.api.sdk.java.api.io.UploadedFileContent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 2 * @Author: Zhou Dong
 * 3 * @Date: 2020/7/28 23:00
 * 4
 */
public class CreateApkRequest implements Serializable {
    private static final long serialVersionUID = -1828764677771826323L;

    private UploadedFileContent appFile;
    private UploadedFileContent iconFile;
    private UploadedFileContent featuredImgFile;
    private List<UploadedFileContent> screenshotFileList;
    private List<UploadedFileContent> paramTemplateFileList;

    private String appName;
    private String appNameByVersion;
    private String baseType;
    private List<String> modelNameList;
    private List<String> categoryList;
    private String shortDesc;
    private String description;
    private String releaseNotes;
    private List<String> paramTemplateList;
    private Integer chargeType;
    private BigDecimal price;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

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

    public List<String> getParamTemplateList() {
        return paramTemplateList;
    }

    public void setParamTemplateList(List<String> paramTemplateList) {
        this.paramTemplateList = paramTemplateList;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAppNameByVersion() {
        return appNameByVersion;
    }

    public void setAppNameByVersion(String appNameByVersion) {
        this.appNameByVersion = appNameByVersion;
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
