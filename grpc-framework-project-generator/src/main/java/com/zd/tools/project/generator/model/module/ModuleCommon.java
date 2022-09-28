package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleCommon extends AbstractModule {

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();

//        getSourceFiles().add(new SourceFile("RestfulHttpExceptionAdvice.java", getPackagePath(), File.separator + "restful"+ File.separator + "advice",      GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        
        getSourceFiles().add(new SourceFile("pom_common.xml", getBasePath(), "", GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
