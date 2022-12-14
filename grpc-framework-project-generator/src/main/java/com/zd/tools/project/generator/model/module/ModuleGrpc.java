package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.analysis.process.StrFormatterUtil;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleGrpc extends AbstractModule {

    private String grpcPort;

    @Override
    public void configOwnDir() {
        super.configOwnDir();
        getDirs().add(getPackagePath() + File.separator + "server");
        getDirs().add(getPackagePath() + File.separator + "grpc");
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("grpc/interceptor"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("grpc/dto"));
    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();
        getSourceFiles().add(new SourceFile("log4j2.xml",                 getResourcesPath(), "", GenEnum.fileType.config, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("GrpInterceptorRegister.java",getPackagePath(),   StrFormatterUtil.replaceSlash("/grpc/interceptor"), GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GrpcAccessInterceptor.java",  getPackagePath(),  StrFormatterUtil.replaceSlash("/grpc/interceptor"), GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GrpcTokenInterceptor.java",     getPackagePath(),StrFormatterUtil.replaceSlash("/grpc/interceptor"), GenEnum.fileType.source, GenEnum.fileOperatorType.create));

        getSourceFiles().add(new SourceFile("application-grpc.properties", getResourcesPath(), "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.append));

        getSourceFiles().add(new SourceFile("pom_grpc.xml", getBasePath(), "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
