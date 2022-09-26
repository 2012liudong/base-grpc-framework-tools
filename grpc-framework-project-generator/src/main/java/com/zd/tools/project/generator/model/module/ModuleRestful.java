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
        getDirs().add(getPackagePath() + File.separator + "restful");
        getDirs().add(getPackagePath() + File.separator + "restful/advice");
        getDirs().add(getPackagePath() + File.separator + "restful/interceptor");
        getDirs().add(getPackagePath() + File.separator + "restful/model");
    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();
        getSourceFiles().add(new SourceFile("log4j2.xml", "",  GenEnum.fileType.config, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("HttpExceptionAdvice.java", "/restful/advice",  GenEnum.fileType.java, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("AccessInterceptor.java","/restful/interceptor", GenEnum.fileType.java, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("InterceptorRegister.java","/restful/interceptor", GenEnum.fileType.java, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("TokenInterceptor.java","/restful/interceptor", GenEnum.fileType.java, GenEnum.fileOperatorType.create));
    }
}
