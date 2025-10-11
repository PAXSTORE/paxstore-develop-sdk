package com.pax.market.api.sdk.java.api.developer.dto.step;


import com.pax.market.api.sdk.java.api.io.UploadedFileContent;
import java.util.Map;

public class CreateMultipleApkRequest extends CreateBaseApkRequest {

    private static final long serialVersionUID = -764109260436848850L;
    private Map<String, UploadedFileContent> multipleAppFile;

    public Map<String, UploadedFileContent> getMultipleAppFile() {
        return multipleAppFile;
    }

    public void setMultipleAppFile(Map<String, UploadedFileContent> multipleAppFile) {
        this.multipleAppFile = multipleAppFile;
    }
}
