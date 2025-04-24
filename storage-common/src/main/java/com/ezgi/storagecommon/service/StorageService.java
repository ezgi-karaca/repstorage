package com.ezgi.storagecommon.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface StorageService {
    void saveFile(MultipartFile file, String packageName, String version) throws Exception;
    InputStream getFile(String packageName, String version, String fileName) throws Exception;

}
