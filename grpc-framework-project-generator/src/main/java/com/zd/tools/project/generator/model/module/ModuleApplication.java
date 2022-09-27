package com.zd.tools.project.generator.model.module;

import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleApplication extends AbstractModule {

    @Override
    public String getWrapBy() {
        return null;
    }

    @Override
    public void setWrapBy(String wrapBy) {
        throw new RuntimeException(StrUtil.format(Const.ERR_MODULE_NOSUPPORT, getName()));
    }

    @Override
    public void configOwnDir() {
        super.configOwnDir();
        getDirs().add(getPackagePath() + File.separator + "config");
    }

    @Override
    public void configOwnSourceFile(){
//        super.configOwnSourceFile();
        getSourceFiles().add(new SourceFile("Application.j", "",  GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("SwaggerConfig.j","/config", GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("application-dev.properties", "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("application-application.properties", "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("pom_application.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
