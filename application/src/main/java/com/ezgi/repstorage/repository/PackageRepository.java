package com.ezgi.repstorage.repository;

import com.ezgi.repstorage.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
    boolean existsByNameAndVersion(String name, String version);
    PackageEntity findByNameAndVersion(String name, String version);
}
