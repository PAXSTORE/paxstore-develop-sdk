package com.pax.market.api.sdk.java.api.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * The type Aes utils.
 */
public class AESUtils {

    /**
     * Encrypt string.
     *
     * @param content    the content
     * @param encryptKey the encrypt key
     * @return the string
     * @throws Exception the exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());

        kgen.init(128, random);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        byte[] encryptedBytes = cipher.doFinal(content.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypt string.
     *
     * @param encryptedString the encrypted string
     * @param encryptKey      the encrypt key
     * @return the string
     * @throws Exception the exception
     */
    public static String decrypt(String encryptedString, String encryptKey) throws Exception {
        if (StringUtils.isEmpty(encryptedString) || StringUtils.isEmpty(encryptKey)) {
            return null;
        }
        byte[] decryptedBytes;
        try {
            decryptedBytes = Base64.getDecoder().decode(encryptedString);
        } catch (Exception e) {
            return encryptedString;
        }

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, random);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(decryptedBytes);

        return new String(decryptBytes);
    }


    /**
     * Silent encrypt string.
     *
     * @param content    the content
     * @param encryptKey the encrypt key
     * @return the string
     */
    public static String silentEncrypt(String content, String encryptKey) {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(encryptKey)) {
            return content;
        }

        try {
            return encrypt(content, encryptKey);
        } catch (Exception ignore) {
            return content;
        }
    }

    /**
     * Silent decrypt string.
     *
     * @param encryptedString the encrypted string
     * @param encryptKey      the encrypt key
     * @return the string
     */
    public static String silentDecrypt(String encryptedString, String encryptKey) {
        if (StringUtils.isEmpty(encryptedString) || StringUtils.isEmpty(encryptKey)) {
            return encryptedString;
        }

        try {
            return decrypt(encryptedString, encryptKey);
        } catch (Exception ignore) {
            return encryptedString;
        }
    }

    public static void main(String[] args) {
        String appKey="6XC042UCQG51T17F0D6Z";
        String appSecret="5VP7582J1B0S27NC1MGN9G43890OAYD993DJ6144";

        String apiSecret="D4S5LG6GOWLVD47SQEEMJ9H96MHHMT19XZLKGYIO";

        String encryptString = silentEncrypt(appSecret, apiSecret);
        String decryptString = silentDecrypt(encryptString, apiSecret);

        System.out.println(encryptString);
        System.out.println(decryptString);
    }

}
