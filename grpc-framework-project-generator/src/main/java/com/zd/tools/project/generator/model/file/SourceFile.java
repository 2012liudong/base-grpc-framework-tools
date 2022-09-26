package com.zd.tools.project.generator.model.file;

import com.zd.tools.project.generator.consts.GenEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SourceFile {

    private String name;

    private String path;

    private GenEnum.fileType fileType;

    private GenEnum.fileOperatorType fileOperatorType;
}
