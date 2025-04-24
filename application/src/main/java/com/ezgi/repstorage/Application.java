package com.ezgi.repstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ezgi.repstorage",
        "com.ezgi.storagefilesystem",
        "com.ezgi.storageobject",
        "com.ezgi.storagecommon"
})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
