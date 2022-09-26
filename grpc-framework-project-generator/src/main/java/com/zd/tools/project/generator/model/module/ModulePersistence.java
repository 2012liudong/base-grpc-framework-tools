package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModulePersistence extends ModuleCommon {

    private String mapper;

    private String ip;

    private String dbPort;

    private String username;

    private String password;

    @Override
    public void configOwnDir() {
        super.configOwnDir();
        getDirs().add(getResourcesPath() + File.separator + getMapper());
    }

    @Override
    public void configOwnSourceFile(){
//        super.configOwnSourceFile();

        getSourceFiles().add(new SourceFile("application-persistence.yml", "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("pom_persistence.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
