package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.file.SourceFile;
import com.zd.tools.project.generator.model.module.model.ModulePropertyBo;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Project extends AbstractBo {

    //com.zd
    private String groupId;

    //2.0-SNAPSHOT
    private String version;

    //com.zd.baseframework
    private String basePackage;

    //jar
    private String packaging;

    private Map<String, AbstractModule> modules = new HashMap<>();

    private ModulePropertyBo modulePropertyBo;

    public void configOwnSourceFile(){
        getSourceFiles().add(new SourceFile("pom_parent.xml", getBasePath(), "", GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }

}
