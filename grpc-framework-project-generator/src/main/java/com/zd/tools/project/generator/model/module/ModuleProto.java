package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleProto extends AbstractModule {

    @Override
    public void configOwnDir() {
        getDirs().clear();
        getDirs().add(getBasePath()+ File.separator + "src" + File.separator + "main" + File.separator + "proto");
    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();

        getSourceFiles().add(new SourceFile("application-proto.yml", "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("pom_proto.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
