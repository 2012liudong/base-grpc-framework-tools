package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.file.SourceFile;
import com.zd.tools.project.generator.model.module.model.ModulePropertyBo;
import lombok.Data;

import java.io.File;

@Data
public class ModuleRestful extends ModuleCommon {

    private String port;

    private ModulePropertyBo modulePropertyBo;

    @Override
    public void configOwnDir() {
        super.configOwnDir();
        getDirs().add(getPackagePath() + File.separator + "server");
        getDirs().add(getPackagePath() + File.separator + "restful");
        getDirs().add(getPackagePath() + File.separator + "restful/advice");
        getDirs().add(getPackagePath() + File.separator + "restful/interceptor");
        getDirs().add(getPackagePath() + File.separator + "restful/model");
    }

    @Override
    public void configOwnSourceFile(){
//        super.configOwnSourceFile();
        getSourceFiles().add(new SourceFile("log4j2.xml", "",  GenEnum.fileType.config, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("HttpExceptionAdvice.j", "/restful/advice",  GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("AccessInterceptor.j","/restful/interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("InterceptorRegister.j","/restful/interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("TokenInterceptor.j","/restful/interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));

        getSourceFiles().add(new SourceFile("application-restful.properties", "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("pom_restful.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
