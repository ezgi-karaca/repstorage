package com.ezgi.repstorage.config;

import com.ezgi.storagecommon.service.StorageService;
import com.ezgi.storagefilesystem.service.FileStorageService;
import com.ezgi.storageobject.service.ObjectStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageStrategyConfig {
    @Value("${storage.strategy:file-system}")
    private String strategy;

    @Bean
    public StorageService storageService(FileStorageService fileStorageService, ObjectStorageService objectStorageService){
        if("object-storage".equalsIgnoreCase(strategy)){
            return objectStorageService;
        } else {
            return fileStorageService;
        }
    }
}
