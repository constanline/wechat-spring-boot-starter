package com.magicalyang.wechat.common.util;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @author Constanline
 * @date 2019/10/28
 */
public class EncryptUtil {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * MD5 16进制转码时的映射字母表。
     */
    private static final char[] KEY_MAP = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * AES的密钥长度
     */
    private static final Integer DEFAULT_AES_SECRET_KEY_LENGTH = 128;
    /**
     * 默认加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_AES_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    /**
     * 默认加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_DES_CIPHER_ALGORITHM = "DES";

    /**
     * 使用密钥通过AES算法加密指定的文本。
     *
     * @param text 需要加密的文本。
     * @param key 8位字节的密钥。
     * @return 加密后的文本。
     */
    public static String encrypt(String text, String key) {
        try {
            return encrypt("AES", text, DEFAULT_AES_SECRET_KEY_LENGTH, key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 使用密钥通过算法加密指定的字节。
     *
     * @param algorithm 加密算法。
     * @param text 需要加密的文本。
     * @param keyLength 密钥长度。
     * @param key 密钥。
     * @return 加密后的字节。
     * @throws Exception 加密过程发生异常。
     */
    public static String encrypt(String algorithm, String text, int keyLength, String key) throws Exception {
        return Base64Utils.encodeToString(encrypt(algorithm, text.getBytes(DEFAULT_CHARSET), keyLength, key));
    }

    /**
     * 使用密钥通过AES算法加密指定的字节。
     *
     * @param bytes 需要加密的字节。
     * @param key 密钥。
     * @return 加密后的字节。
     * @throws Exception 加密过程发生异常。
     */
    public static byte[] encrypt(byte[] bytes, String key) throws Exception {
        return encrypt("AES", bytes, DEFAULT_AES_SECRET_KEY_LENGTH, key);
    }

    /**
     * 使用密钥通过DES算法加密指定的字节。
     *
     * @param algorithm 加密算法。
     * @param bytes 需要加密的字节。
     * @param keyLength 密钥长度。
     * @param key 密钥。
     * @return 加密后的字节。
     * @throws Exception 加密过程发生异常。
     */
    public static byte[] encrypt(String algorithm, byte[] bytes, int keyLength, String key) throws Exception {
        return doCipherFinal(algorithm, bytes, keyLength, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用密钥解密通过AES算法加密的字符串。
     *
     * @param text 需要解密的字符串。
     * @param key 8位字节的密钥。
     * @return 解密后的字符串。
     */
    public static String decrypt(String text, String key) {
        try {
            return decrypt("AES", text, DEFAULT_AES_SECRET_KEY_LENGTH, key);
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 使用密钥解密通过算法加密的字节。
     *
     * @param algorithm 解密算法。
     * @param text 需要解密的字符串。
     * @param keyLength 密钥长度。
     * @param key 密钥。
     * @return 解密后的字符串。
     * @throws Exception 解密过程发生异常。
     */
    public static String decrypt(String algorithm, String text, int keyLength, String key) throws Exception {
        return new String(decrypt(algorithm, Base64Utils.decodeFromString(text), keyLength, key), DEFAULT_CHARSET);
    }

    /**
     * 使用密钥解密通过AES算法加密的字节。
     *
     * @param bytes 需要解密的字节。
     * @param key 8位字节的密钥。
     * @return 解密后的字节。
     * @throws Exception
     *             解密过程发生异常。
     */
    public static byte[] decrypt(byte[] bytes, String key) throws Exception {
        return doCipherFinal("AES", bytes, DEFAULT_AES_SECRET_KEY_LENGTH, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 使用密钥解密通过算法加密的字节。
     *
     * @param algorithm 解密算法。
     * @param bytes 需要解密的字节。
     * @param keyLength 密钥长度。
     * @param key 密钥。
     * @return 解密后的字节。
     * @throws Exception 解密过程发生异常。
     */
    public static byte[] decrypt(String algorithm, byte[] bytes, int keyLength, String key) throws Exception {
        return doCipherFinal(algorithm, bytes, keyLength, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 使用密钥加解密。
     *
     * @param algorithm 加解密算法。
     * @param buffer 需要加解密的字节。
     * @param keyLength 密钥长度。
     * @param key 密钥。
     * @param mode 加解密模式。
     * @return 加解密后的字节。
     * @throws Exception 解密过程发生异常。
     */
    public static byte[] doCipherFinal(String algorithm, byte[] buffer, int keyLength, String key, int mode) throws Exception {
        byte[] keyBytes = key.getBytes(DEFAULT_CHARSET);
        SecretKey secretKey;
        if (keyBytes.length * 8 == keyLength || keyBytes.length * 8 >= keyLength * 2) {
            secretKey = new SecretKeySpec(keyBytes, algorithm);
        } else {
            KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
            keygen.init(keyLength, new SecureRandom(keyBytes));
            SecretKey originalKey = keygen.generateKey();
            byte [] raw = originalKey.getEncoded();
            secretKey = new SecretKeySpec(raw, algorithm);
        }

        Cipher cipher;
        if ("AES".equals(algorithm)) {
            cipher = Cipher.getInstance(DEFAULT_AES_CIPHER_ALGORITHM);
        } else if ("DES".equals(algorithm)) {
            cipher = Cipher.getInstance(DEFAULT_DES_CIPHER_ALGORITHM);
        } else {
            throw new Exception("Not supported algorithm");
        }
        cipher.init(mode, secretKey);
        return cipher.doFinal(buffer);
    }

    /**
     * 对指定文本使用MD5算法进行加密，使用此加密算法获得的值不可解密。
     *
     * @param text 需要加密的字节。
     * @return 32位16进制字符组成的密码。
     * @throws Exception 加密过程中发生异常。
     */
    public static String md5(String text) throws Exception {
        return md5(text, DEFAULT_CHARSET);
    }

    public static String md5(String text, String charset) throws Exception {
        return md5(text.getBytes(charset));
    }

    public static String md5(String text, Charset charset) throws Exception {
        return md5(text.getBytes(charset));
    }

    /**
     * 对指定字节使用MD5算法进行加密，使用此加密算法获得的值不可解密。
     *
     * @param bytes
     *            需要加密的字节。
     * @return 32位16进制字符组成的密码。
     * @throws Exception
     *             加密过程中发生异常。
     */
    public static String md5(byte[] bytes) throws Exception {
        return messageDigestCall("MD5", bytes);
    }

    public static String sha1(String text) throws Exception {
        return sha1(text, DEFAULT_CHARSET);
    }

    public static String sha1(String text, String charset) throws Exception {
        return sha1(text.getBytes(charset));
    }

    public static String sha1(String text, Charset charset) throws Exception {
        return sha1(text.getBytes(charset));
    }

    public static String sha1(byte[] bytes) throws Exception {
        return messageDigestCall("SHA1", bytes);
    }

    private static String messageDigestCall(String algorithm, byte[] bytes) throws Exception {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(bytes);
        byte bt;
        byte[] codes = md.digest();
        char[] str = new char[codes.length * 2];
        int i, j = 0;
        for (i = 0; i < codes.length; i++) {
            bt = codes[i];
            str[j++] = KEY_MAP[bt >>> 4 & 0xf];
            str[j++] = KEY_MAP[bt & 0xf];
        }
        return new String(str);
    }
}