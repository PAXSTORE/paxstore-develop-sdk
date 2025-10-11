package com.pax.market.api.sdk.java.api.base.dto;


import java.io.Serializable;

/**
 * @author shifan
 * @date 2025/10/11
 */

public class AppKeySecretDTO implements Serializable {

    private static final long serialVersionUID = 6886379482748335539L;
    private String appKey;

    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
