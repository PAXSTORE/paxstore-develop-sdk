package com.pax.market.api.sdk.java.api.base.dto;

import java.io.Serializable;

public class CodeInfoDTO implements Serializable {
    private String value;    // 数据值
    private String label;    // 标签名

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "CodeInfoDTO [ value=" + value + ", label="
                + label +"]";
    }
}
