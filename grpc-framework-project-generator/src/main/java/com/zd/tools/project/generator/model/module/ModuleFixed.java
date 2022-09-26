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
        getSourceFiles().add(new SourceFile("GloablInterceptorRegister.j","/grpc/interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GlobalClientInterceptor.j","/grpc/interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GlobalServerInterceptor.j","/grpc/interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("GrpcTokenInterceptor.j","/grpc/interceptor", GenEnum.fileType.source, GenEnum.fileOperatorType.create));
        getSourceFiles().add(new SourceFile("application-fixed.yml", "",  GenEnum.fileType.yml, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("pom_fixed.xml", "",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
