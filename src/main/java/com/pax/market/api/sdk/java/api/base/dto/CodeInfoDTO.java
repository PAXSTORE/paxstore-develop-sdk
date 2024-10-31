package com.pax.market.api.sdk.java.api.base.dto;

import java.io.Serializable;

public class CodeInfoDTO implements Serializable {
    private Long id = -1L;
    private String type;    // 类型
    private String value;    // 数据值
    private String label;    // 标签名
    private String description;// 描述
    private String lang;
    private Integer sort;    // 排序

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
