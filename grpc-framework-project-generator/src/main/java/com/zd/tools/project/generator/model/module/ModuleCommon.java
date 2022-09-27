package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

@Data
public class ModuleCommon extends AbstractModule {

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();
        getSourceFiles().add(new SourceFile("application-common.properties", "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("pom_common.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
