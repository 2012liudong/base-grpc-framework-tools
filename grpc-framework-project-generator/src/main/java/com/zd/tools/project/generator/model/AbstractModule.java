package com.zd.tools.project.generator.model;

import cn.hutool.core.collection.CollUtil;
import com.zd.tools.project.generator.ExtCache;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public abstract class AbstractModule extends AbstractBo {

    private GenEnum.projectType type;

    //base-grpc-framework-application
    private String artifactId;

    //pom
    private String packaging;

    private String wrapBy;

    // .../base-grpc-framework/base-grpc-framework-app/src/main/java
    private String srcPath;

    // .../base-grpc-framework/base-grpc-framework-app/src/main/java/com/zd/baseframework/app
    private String packagePath;

    // .../base-grpc-framework/base-grpc-framework-app/src/main/resources
    private String resourcesPath;

    private Map<String, String> configPropertyMap = new HashMap<>();

    public void configOwnDir(){ }

    public void configOwnSourceFile(){
        List<SourceFile> extFile = ExtCache.extSourceFile.get(getType().name());
        if(CollUtil.isNotEmpty(extFile)){
            for(SourceFile file : extFile){
                file.setBasePath(getPackagePath());
                file.setPath(file.getBasePath() + file.getPackagePath());
                getSourceFiles().add(file);
            }
        }
    }

    public Set validate(){
        return null;
    }
}
