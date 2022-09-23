package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.consts.GenEnum;
import lombok.Data;

import java.util.Set;

@Data
public abstract class AbstractModule extends AbstractBo {

    private GenEnum.projectType type;

    private String artifactId;

    private String packaging;

    private String config;

    private Logging logging;

    private String wrapBy;

    private Build build;

    private String srcPath;

    private String packagePath;

    private String resourcesPath;

    public void configOwnDir(){ }

    public void configOwnFile(){ }

    public Set validate(){
        return null;
    }
}
