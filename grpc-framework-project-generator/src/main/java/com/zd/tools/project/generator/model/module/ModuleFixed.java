package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleFixed extends ModuleRestful {

    private String grpcPort;

    @Override
    public void configOwnDir() {
        super.configOwnDir();
        getDirs().add(getPackagePath() + File.separator + "grpc");
        getDirs().add(getPackagePath() + File.separator + "grpc/interceptor");
        getDirs().add(getPackagePath() + File.separator + "grpc/dto");
    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();
        getSourceFiles().add(new SourceFile("log4j2.xml", "",  GenEnum.fileType.config, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("GloablInterceptorRegister.java","/grpc/interceptor", GenEnum.fileType.java, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GlobalClientInterceptor.java","/grpc/interceptor", GenEnum.fileType.java, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GlobalServerInterceptor.java","/grpc/interceptor", GenEnum.fileType.java, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GrpcTokenInterceptor.java","/grpc/interceptor", GenEnum.fileType.java, GenEnum.fileOperatorType.create));
    }
}
