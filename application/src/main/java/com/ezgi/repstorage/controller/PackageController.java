package com.ezgi.repstorage.controller;

import com.ezgi.repstorage.dto.MetaDataDto;
import com.ezgi.repstorage.entity.PackageEntity;
import com.ezgi.repstorage.service.PackageService;
import com.ezgi.storagecommon.service.StorageService;
import com.ezgi.storagefilesystem.service.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    private final PackageService packageService;
    private final StorageService storageService;

    @Autowired
    public PackageController(PackageService packageService, @Qualifier("storageService") StorageService storageService){
        this.packageService = packageService;
        this.storageService = storageService;
    }

    @PostMapping("/{packageName}/{version}")
    public ResponseEntity<String> uploadPackage(@PathVariable("packageName") String packageName,
                                                @PathVariable("version") String version,
                                                @RequestParam("file") MultipartFile file,
                                                @RequestPart("metaDataDto") @Valid MetaDataDto metaDataDto){
        try{
            storageService.saveFile(file, packageName, version);

            packageService.savePackage(metaDataDto);

            return new ResponseEntity<>("Package uploaded successfully", HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Package already exists", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>("Error saving file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error uploading package", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{packageName}/{version}/{fileName}")
    public ResponseEntity<Object> downloadPackage(@PathVariable String packageName, @PathVariable String version, @PathVariable String fileName){
        PackageEntity packageEntity = packageService.getPackage(packageName, version);

        if(packageEntity == null) {
            return new ResponseEntity<>("Package not found", HttpStatus.NOT_FOUND);
        }



        try{
            InputStream inputStream = storageService.getFile(packageName, version, fileName);
            InputStreamResource resource = new InputStreamResource(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + fileName);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>("Error reading file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
