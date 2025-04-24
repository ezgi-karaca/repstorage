package com.ezgi.storageobject.service;

import com.ezgi.storagecommon.service.StorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service("objectStorageService")
public class ObjectStorageService implements StorageService {
    private final MinioClient minioClient;
    private final String bucketName = "repstorage";

    public ObjectStorageService(){
        this.minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
    }

    @Override
    public void saveFile(MultipartFile file, String packageName, String version) throws Exception{
        String objectName = packageName + "/" + version + "/" + file.getOriginalFilename();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
    }

    @Override
    public InputStream getFile(String packageName, String version, String fileName) throws Exception {
        String objectName = packageName + "/" + version + "/" + fileName;

        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        );
    }
}
