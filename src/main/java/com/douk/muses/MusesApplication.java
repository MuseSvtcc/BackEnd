package com.douk.muses;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.douk.muses.mapper",value = "com.douk.muses.mapper")
@EnableCaching
public class MusesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusesApplication.class, args);
    }

}
