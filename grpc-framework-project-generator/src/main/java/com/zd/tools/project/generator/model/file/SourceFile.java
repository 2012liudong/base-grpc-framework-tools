package com.zd.tools.project.generator.model.file;

import com.zd.tools.project.generator.consts.GenEnum;
import lombok.Data;

@Data
public class SourceFile {

    private String name;

    private String path;

    private String basePath;

    private String packagePath;

    private GenEnum.fileType fileType;

    private GenEnum.fileOperatorType fileOperatorType;

    public SourceFile(String name, String basePath, String packagePath, GenEnum.fileType fileType, GenEnum.fileOperatorType fileOperatorType) {
        this.name = name;
        this.basePath = basePath;
        this.fileType = fileType;
        this.fileOperatorType = fileOperatorType;
        this.packagePath = packagePath;
        this.path = basePath + packagePath;
    }
}
