package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Project extends AbstractBo {

    private String groupId;

    private String version;

    private String basePackage;

    private String packaging;

    private Map<String, AbstractModule> modules = new HashMap<>();

    public void configOwnSourceFile(){

        getSourceFiles().add(new SourceFile("pom_parent.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }

}
