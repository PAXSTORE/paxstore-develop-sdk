package com.pax.market.api.sdk.java.api.base.dto;

import java.io.Serializable;

public class AppDetailDTO implements Serializable {
    private Long id;
    private String name;
    private String packageName;
    private String type;
    private String osType;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppDetailDTO [id=" + id + ", name=" + name + ", packageName=" + packageName + ", type="
                + type + ", osType=" + osType + ", status=" + status+ "]";
    }
}
