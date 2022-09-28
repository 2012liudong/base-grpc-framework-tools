package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModulePersistence extends AbstractModule {

    private String mapper;

    private String dbIp;

    private String dbPort;

    private String dbName;

    private String dbUsername;

    private String dbPassword;

    @Override
    public void configOwnDir() {
        super.configOwnDir();
        getDirs().add(getResourcesPath() + File.separator + getMapper());
    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();

        getSourceFiles().add(new SourceFile("application-persistence.properties", getResourcesPath(),"",  GenEnum.fileType.yml, GenEnum.fileOperatorType.append));

        getSourceFiles().add(new SourceFile("pom_persistence.xml", getBasePath(),"",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
