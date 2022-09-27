package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.consts.GenEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public abstract class AbstractModule extends AbstractBo {

    private GenEnum.projectType type;

    //base-grpc-framework-application
    private String artifactId;

    //pom
    private String packaging;

    private String config;

    private Logging logging;

    private String wrapBy;

    private Build build;

    // .../base-grpc-framework/base-grpc-framework-app/src/main/java
    private String srcPath;

    // .../base-grpc-framework/base-grpc-framework-app/src/main/java/com/zd/baseframework/app
    private String packagePath;

    // .../base-grpc-framework/base-grpc-framework-app/src/main/resources
    private String resourcesPath;

    //.../base-grpc-framework/base-grpc-framework-app
//    private String basePath;
    // app
//    private String name;

    public void configOwnDir(){ }

    public void configOwnSourceFile(){ }

    public Map<String, String> configProperty(){
        Map<String, String> map = new HashMap<>();
        return map;
    }

    public Set validate(){
        return null;
    }
}
