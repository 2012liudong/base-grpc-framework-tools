package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.consts.GenEnum;
import lombok.Data;

@Data
public class ModuleBo extends BaseBo {

    private GenEnum.projectType type;

    private String artifactId;

    private String packaging;

    private String wrapBy;

    private String config;

    private LoggingBo loggingBo;

    private BuildBo buildBo;

}
