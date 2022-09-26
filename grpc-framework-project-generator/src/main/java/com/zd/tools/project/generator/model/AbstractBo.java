package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public abstract class AbstractBo {

    private String basePath;

    private String name;

    private Set<String> dirs = new HashSet<>();

    private Set<SourceFile> sourceFiles = new HashSet<>();

}
