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


import com.pax.market.api.sdk.java.api.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by fanjun on 2016/11/10.
 */
public class CryptoUtils {
    private static final Logger logger = LoggerFactory.getLogger(CryptoUtils.class);

    private CryptoUtils() {}


    /**
     * 给请求签名。
     *
     * @param queryString 请求参数
     * @param body        请求主体内容
     * @param secret      签名密钥
     * @param signMethod  签名方法
     * @return 签名 string
     * @throws IOException              the io exception
     * @throws GeneralSecurityException the general security exception
     */
    public static String signRequest(String queryString, String body, String secret, String signMethod) throws IOException, GeneralSecurityException {

        // 1. 判断参数是否存在
        StringBuilder query = new StringBuilder();
        if(queryString != null){
            query.append(queryString);
        }

        // 2. 把请求主体拼接在参数后面
        if (body != null) {
            query.append(body);
        }

        // 3. 使用MD5/HMAC加密
        byte[] bytes;
        if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 4. 把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    private static byte[] encryptHMAC(String data, String secret) throws GeneralSecurityException, IOException {
        byte[] bytes;
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
        return bytes;
    }

    /**
     * 对字符串采用UTF-8编码后，用MD5进行摘要。
     *
     * @param data the data
     * @return the byte [ ]
     * @throws IOException              the io exception
     * @throws GeneralSecurityException the general security exception
     */
    public static byte[] encryptMD5(String data) throws IOException, GeneralSecurityException {
        return encryptMD5(data.getBytes(Constants.CHARSET_UTF8));
    }

    /**
     * 对字节流进行MD5摘要。
     *
     * @param data the data
     * @return the byte [ ]
     * @throws IOException              the io exception
     * @throws GeneralSecurityException the general security exception
     */
    public static byte[] encryptMD5(byte[] data) throws IOException, GeneralSecurityException {
        byte[] bytes;
        MessageDigest md = MessageDigest.getInstance("MD5");//NOSONAR
        bytes = md.digest(data);
        return bytes;
    }

    /**
     * 把字节流转换为十六进制表示方式。
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

}
