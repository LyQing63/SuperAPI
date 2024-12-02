package com.github.lyqing63.superapi.utils.utils;

import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class PasswordEncoder {

    private static SymmetricCrypto aes = new SymmetricCrypto("AES");

    public static String encode(String password) {
        return aes.encryptHex(password);
    }

    public static Boolean matches(String input, String ori) {
        return input.equals(aes.decryptStr(ori));
    }


}
