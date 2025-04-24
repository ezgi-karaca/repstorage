package com.ezgi.repstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetaDataDto {
    private String name;
    private String version;
    private String author;
    private List<DependencyDto> dependencies;

}
