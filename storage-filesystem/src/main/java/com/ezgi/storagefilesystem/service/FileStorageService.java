package com.ezgi.storagefilesystem.service;


import com.ezgi.storagecommon.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service("fileStorageService")
public class FileStorageService implements StorageService {
    private static final String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/storage/";

    @Override
    public void saveFile(MultipartFile file, String packageName, String version) throws IOException {
        String filePath = STORAGE_DIRECTORY + packageName + "/" + version + "/" + file.getOriginalFilename();
        File targetFile = new File(filePath);

        targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);

    }

    @Override
    public InputStream getFile(String packageName, String version, String fileName) throws FileNotFoundException {
        String filePath = STORAGE_DIRECTORY + packageName + "/" + version + "/" + fileName;
        return new FileInputStream(new File(filePath));
    }

}
