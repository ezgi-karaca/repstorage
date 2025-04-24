package com.ezgi.repstorage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dependencies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DependencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_name")
    private String packageName;

    private String version;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private PackageEntity pkg;
}
