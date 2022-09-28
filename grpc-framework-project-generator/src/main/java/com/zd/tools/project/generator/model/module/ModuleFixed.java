package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleFixed extends AbstractModule {

    private String port;

    private String grpcPort;

    @Override
    public void configOwnDir() {
        super.configOwnDir();

        getDirs().add(getPackagePath() + File.separator + "server");
        getDirs().add(getPackagePath() + File.separator + "restful");
        getDirs().add(getPackagePath() + File.separator + "restful/config");
        getDirs().add(getPackagePath() + File.separator + "restful/advice");
        getDirs().add(getPackagePath() + File.separator + "restful/interceptor");
        getDirs().add(getPackagePath() + File.separator + "restful/model");

        getDirs().add(getPackagePath() + File.separator + "grpc");
        getDirs().add(getPackagePath() + File.separator + "grpc/interceptor");
        getDirs().add(getPackagePath() + File.separator + "grpc/dto");


    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();
        getSourceFiles().add(new SourceFile("log4j2.xml",                      getResourcesPath(), "", GenEnum.fileType.config, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("RestfulHttpExceptionAdvice.java", getPackagePath(), File.separator + "restful"+ File.separator + "advice",      GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("RestfulAccessInterceptor.java",   getPackagePath(), File.separator + "restful"+ File.separator + "interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("RestfulInterceptorRegister.java", getPackagePath(), File.separator + "restful"+ File.separator + "interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("RestfulTokenInterceptor.java",    getPackagePath(), File.separator + "restful"+ File.separator + "interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("SwaggerConfig.java",              getPackagePath(), File.separator + "restful"+ File.separator + "config",      GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("GrpInterceptorRegister.java",getPackagePath(),   File.separator + "grpc"+ File.separator + "interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GrpcAccessInterceptor.java",  getPackagePath(),  File.separator + "grpc"+ File.separator + "interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GrpcTokenInterceptor.java",     getPackagePath(),File.separator + "grpc"+ File.separator + "interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));


        getSourceFiles().add(new SourceFile("application-fixed.properties", getResourcesPath(), "", GenEnum.fileType.yml, GenEnum.fileOperatorType.append));

        getSourceFiles().add(new SourceFile("pom_fixed.xml", getBasePath(), "", GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
