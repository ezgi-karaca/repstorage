package com.ezgi.repstorage.repository;

import com.ezgi.repstorage.entity.DependencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependencyRepository extends JpaRepository<DependencyEntity, Long> {
    
}
