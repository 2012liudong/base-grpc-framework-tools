package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.analysis.process.StrFormatterUtil;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleCommon extends AbstractModule {

    @Override
    public void configOwnDir() {
        super.configOwnDir();

        getDirs().add(getPackagePath() + File.separator + "constant");
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("constant/enumeration"));
        getDirs().add(getPackagePath() + File.separator + "entity");
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("entity/convert"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("entity/dao"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("entity/grpc"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("entity/restful"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("entity/restful/response"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("entity/service"));
        getDirs().add(getPackagePath() + File.separator + "exception");
        getDirs().add(getPackagePath() + File.separator + "spring");
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("spring/advice"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("spring/dao"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("spring/grpc"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("spring/grpc/server"));
        getDirs().add(getPackagePath() + File.separator + StrFormatterUtil.replaceSlash("spring/restful"));
        getDirs().add(getPackagePath() + File.separator + "util");

    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();

        getSourceFiles().add(new SourceFile("Constants.java",       getPackagePath(), StrFormatterUtil.replaceSlash("/constant"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("ResponseConst.java",   getPackagePath(), StrFormatterUtil.replaceSlash("/constant"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("AppEnum.java", getPackagePath(),               StrFormatterUtil.replaceSlash("/constant/enumeration"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("AppEnumInitializer.java", getPackagePath(),    StrFormatterUtil.replaceSlash("/constant/enumeration"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("AppEnumTranslatorUtil.java", getPackagePath(), StrFormatterUtil.replaceSlash("/constant/enumeration"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("MapStructMapper.java", getPackagePath(),         StrFormatterUtil.replaceSlash("/entity/convert"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("MapStructTransaltorUtil.java", getPackagePath(), StrFormatterUtil.replaceSlash("/entity/convert"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("BaseEntity.java", getPackagePath(),            StrFormatterUtil.replaceSlash("/entity/dao"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("BaseMetaObjectHandler.java", getPackagePath(), StrFormatterUtil.replaceSlash("/entity/dao"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("BaseVo.java", getPackagePath(),        StrFormatterUtil.replaceSlash("/entity/restful"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("DateRange.java", getPackagePath(),     StrFormatterUtil.replaceSlash("/entity/restful"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("EnumVo.java", getPackagePath(),        StrFormatterUtil.replaceSlash("/entity/restful"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("LoginUser.java", getPackagePath(),     StrFormatterUtil.replaceSlash("/entity/restful"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("PageParamReq.java", getPackagePath(),  StrFormatterUtil.replaceSlash("/entity/restful"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("BaseResponse.java", getPackagePath(), StrFormatterUtil.replaceSlash("/entity/restful/response"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("FileResponse.java", getPackagePath(), StrFormatterUtil.replaceSlash("/entity/restful/response"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("ListResponse.java", getPackagePath(), StrFormatterUtil.replaceSlash("/entity/restful/response"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("PageResponse.java", getPackagePath(), StrFormatterUtil.replaceSlash("/entity/restful/response"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("BaseBo.java",      getPackagePath(), StrFormatterUtil.replaceSlash("/entity/service"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("PageBo.java",      getPackagePath(), StrFormatterUtil.replaceSlash("/entity/service"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("PageQueryBo.java", getPackagePath(), StrFormatterUtil.replaceSlash("/entity/service"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("AppException.java",  getPackagePath(),  StrFormatterUtil.replaceSlash("/exception"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("UserException.java", getPackagePath(), StrFormatterUtil.replaceSlash("/exception"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("ApperProperties.java", getPackagePath(), StrFormatterUtil.replaceSlash("/spring"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("AbstractHttpExceptionAdvice.java", getPackagePath(), StrFormatterUtil.replaceSlash("/spring/advice"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("MybatisPlugs.java", getPackagePath(), StrFormatterUtil.replaceSlash("/spring/dao"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("AbstractGrpcAccessInterceptor.java", getPackagePath(), StrFormatterUtil.replaceSlash("/spring/grpc"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("AbstractGrpcClientInterceptor.java", getPackagePath(), StrFormatterUtil.replaceSlash("/spring/grpc"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("AbstractGrpcTokenInterceptor.java",  getPackagePath(), StrFormatterUtil.replaceSlash("/spring/grpc"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("CONST.java", getPackagePath(),                 StrFormatterUtil.replaceSlash("/spring/grpc/server"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("DelegateCall.java", getPackagePath(),          StrFormatterUtil.replaceSlash("/spring/grpc/server"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("DelegateCallListener.java", getPackagePath(),  StrFormatterUtil.replaceSlash("/spring/grpc/server"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("AbstractAccessInterceptor.java", getPackagePath(), StrFormatterUtil.replaceSlash("/spring/restful"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("AbstractTokenInterceptor.java",  getPackagePath(), StrFormatterUtil.replaceSlash("/spring/restful"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("AssertUtil.java", getPackagePath(), StrFormatterUtil.replaceSlash("/util"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("EnumVoUtil.java", getPackagePath(), StrFormatterUtil.replaceSlash("/util"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("LogGenerator.java", getPackagePath(), StrFormatterUtil.replaceSlash("/util"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("LoginUserUtil.java", getPackagePath(), StrFormatterUtil.replaceSlash("/util"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("PageUtil.java", getPackagePath(), StrFormatterUtil.replaceSlash("/util"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));
        getSourceFiles().add(new SourceFile("UUIDUtil.java", getPackagePath(), StrFormatterUtil.replaceSlash("/util"), GenEnum.fileType.source, GenEnum.fileOperatorType.copy));

        getSourceFiles().add(new SourceFile("pom_common.xml", getBasePath(), "", GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
