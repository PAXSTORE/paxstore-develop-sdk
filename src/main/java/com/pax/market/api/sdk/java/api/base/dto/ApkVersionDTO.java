package com.pax.market.api.sdk.java.api.base.dto;

import java.io.Serializable;

public class ApkVersionDTO implements Serializable {
    private Long apkId;
    private String status;
    private Long versionCode;
    private String versionName;

    public Long getApkId() {
        return apkId;
    }

    public void setApkId(Long apkId) {
        this.apkId = apkId;
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

    @Override
    public String toString() {
        return "ApkVersionDTO [ apkId=" + apkId + ", status="
                + status+ ", versionCode="
                + versionCode+ ", versionName="
                + versionName +"]";
    }
}
