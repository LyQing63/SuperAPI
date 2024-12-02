package com.github.lyqing63.superapi.utils.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

public class PasswordEncoder {

    /**
     * 密钥
     */
    private static String key = "LyQing6312345612";
    /**
     * 初始化向量
     */
    private static String iv = "LyQing6312345612";

    private static AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));

    public static String encode(String password) {
        return aes.encryptBase64(password);
    }

    public static Boolean matches(String input, String ori) {
        return input.equals(aes.decryptStr(ori));
    }


}
