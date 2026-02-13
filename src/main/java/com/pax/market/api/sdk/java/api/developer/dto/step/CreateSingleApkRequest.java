package com.pax.market.api.sdk.java.api.developer.dto.step;


import com.pax.market.api.sdk.java.api.io.UploadedFileContent;

public class CreateSingleApkRequest extends CreateBaseApkRequest {

    private static final long serialVersionUID = 8305870666615955921L;

    private UploadedFileContent appFile;


    public UploadedFileContent getAppFile() {
        return appFile;
    }


    public void setAppFile(UploadedFileContent appFile) {
        this.appFile = appFile;
    }
}
