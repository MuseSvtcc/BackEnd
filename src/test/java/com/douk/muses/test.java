package com.douk.muses;

import com.douk.utils.md5.MD5Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class test {
    public static void main(String[] args) {

        File F=new File("D:\\demo\\springboot\\muses\\src\\main\\resources\\灵感-导出.jpg");
        String md5= MD5Utils.getMd5((MultipartFile) F);
        System.out.println(md5);
    }
}
