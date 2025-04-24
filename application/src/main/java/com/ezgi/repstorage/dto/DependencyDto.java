package com.ezgi.repstorage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DependencyDto {
    @JsonProperty("package")
    private String packageName;

    private String version;
}
