package com.ezgi.repstorage.service;

import com.ezgi.repstorage.dto.DependencyDto;
import com.ezgi.repstorage.dto.MetaDataDto;
import com.ezgi.repstorage.entity.DependencyEntity;
import com.ezgi.repstorage.entity.PackageEntity;
import com.ezgi.repstorage.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService{

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public void savePackage(MetaDataDto metaDataDto){
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setName(metaDataDto.getName());
        packageEntity.setVersion(metaDataDto.getVersion());
        packageEntity.setAuthor(metaDataDto.getAuthor());

        List<DependencyEntity> dependencyList = new ArrayList<>();

        for(DependencyDto dto: metaDataDto.getDependencies()){
            DependencyEntity dependency = new DependencyEntity();
            dependency.setPackageName(dto.getPackageName());
            dependency.setVersion(dto.getVersion());
            dependency.setPkg(packageEntity);
            dependencyList.add(dependency);
        }

        packageEntity.setDependencies(dependencyList);

        packageRepository.save(packageEntity);
    }

    @Override
    public PackageEntity getPackage(String name, String version){
        return packageRepository.findByNameAndVersion(name,version);
    }
}
