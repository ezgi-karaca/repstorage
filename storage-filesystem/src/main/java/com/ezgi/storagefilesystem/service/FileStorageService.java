package com.ezgi.storagefilesystem.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {
    private static final String STORAGE_DIRECTORY = "/storage/";

    public String saveFile(MultipartFile file, String packageName, String version) throws IOException {
        String filePath = STORAGE_DIRECTORY + packageName + "/" + version + "/" + file.getOriginalFilename();
        File targetFile = new File(filePath);

        targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);

        return filePath;

    }

    public File getFile(String packageName, String version, String fileName) {
        String filePath = STORAGE_DIRECTORY + packageName + "/" + version + "/" + fileName;
        return new File(filePath);
    }

}
