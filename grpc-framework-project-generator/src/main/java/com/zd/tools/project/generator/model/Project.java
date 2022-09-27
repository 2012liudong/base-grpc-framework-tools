package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Project extends AbstractBo {

    //  /Users/liudong/ideaWS/GitHub/base-grpc-framework-tools/grpc-framework-project-generator/target/classes/output/base-grpc-framework/
    private String basePath;

    //  base-grpc-framework
    private String name;

    //com.zd
    private String groupId;

    //2.0-SNAPSHOT
    private String version;

    //com.zd.baseframework
    private String basePackage;

    //jar
    private String packaging;

    private Map<String, AbstractModule> modules = new HashMap<>();

    public void configOwnSourceFile(){

        getSourceFiles().add(new SourceFile("pom_parent.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }

}
