/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */

package com.pax.market.api.sdk.java.api.util;

import com.pax.market.api.sdk.java.api.io.UploadedFileContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by zcy on 2016/12/1 0001.
 */
public class FileUtils {
    /**
     * Delete file boolean.
     *
     * @param sPath the s path
     * @return the boolean
     */
    public static boolean deleteFile(String sPath) {
        if (sPath == null || "".equals(sPath.trim())) {
            return false;
        }
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }


    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串 string
     */
    public static String generateMixString(int length) {
        String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }

        return sb.toString();
    }


    public static boolean moveToFatherFolder(String pathName) {
        //判断文件夹是否存在， 是否有子文件， 父目录是否存在
        String endPath = pathName.substring(0, pathName.lastIndexOf(File.separator));
        if (!new File(pathName).exists() || new File(pathName).listFiles().length == 0 ||
                pathName.lastIndexOf(File.separator) == 0 || !new File(endPath).exists()) {
            System.out.println(" moveToFatherFolder >>> Dictionary is not exits, has no child files or has no parent dictionary");
            return false;
        }

        File[] files = new File(pathName).listFiles();
        for (File startFile : files) {
            try {
                if (startFile.renameTo(new File(endPath + startFile.getName()))) {
                } else {
                    System.out.println(startFile.getName() + " >> File is failed to move!");
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            new File(pathName).delete();
        } catch (Exception e) {
            System.out.println("Delete endPath failed!");
            return false;
        }

        return true;
    }


    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    public static int getBase64FileSize(String base64Str) {
        if(StringUtils.isEmpty(base64Str)){
            return 0;
        }
        String str = base64Str.substring(base64Str.lastIndexOf(",") + 1); // 1.需要计算文件流大小，首先把头部的data:image/png;base64,（注意有逗号）去掉。
        int equalIndex = str.indexOf("=");//2.找到等号，把等号也去掉
        if (str.indexOf("=") > 0) {
            str = str.substring(0, equalIndex);
        }
        int strLength = str.length();//3.原来的字符流大小，单位为字节
        int size = strLength - (strLength / 8) * 2;//4.计算后得到的文件流大小，单位为字节
        return size;
    }

    public static int getBase64FileSizeKB(String base64Str) {
        return getBase64FileSize(base64Str)/1024;
    }

    public static byte[] readFileToBytes(File file) {
        byte[] content = null;

        try (FileInputStream fis = new FileInputStream(file)) {
            content = new byte[(int) file.length()];
            fis.read(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static String getFileSuffix(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if(lastIndexOf!=-1) {
            //说明有后缀
            return fileName.substring(lastIndexOf+1);
        }
        return null;
    }

    public static UploadedFileContent createUploadFile(String filePath) {
        File file = new File(filePath);
        String originalName = file.getName();
        byte[] data = readFileToBytes(file);
        if (data==null) {
            return null;
        }

        return new UploadedFileContent(data, originalName, originalName, getFileSuffix(originalName));
    }
}
