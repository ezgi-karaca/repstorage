package com.ezgi.repstorage.service;

import com.ezgi.repstorage.dto.MetaDataDto;
import com.ezgi.repstorage.entity.PackageEntity;

public interface PackageService {
    void savePackage(MetaDataDto metaDataDto);
    PackageEntity getPackage(String name, String version);
}
