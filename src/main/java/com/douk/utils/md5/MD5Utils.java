package com.douk.utils.md5;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {
    /**
     * 获取上传文件的md5
     *
     * @param file
     * @return
     */
    public static String getMd5(MultipartFile file) {

        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {

        }
        return null;

    }
}
