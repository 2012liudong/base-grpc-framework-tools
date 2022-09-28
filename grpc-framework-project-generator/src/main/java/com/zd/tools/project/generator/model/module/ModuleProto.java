package com.zd.tools.project.generator.model.module;

import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.io.File;

@Data
public class ModuleProto extends AbstractModule {

    @Override
    public void setWrapBy(String wrapBy) {
        throw new RuntimeException(StrUtil.format(Const.ERR_MODULE_NOSUPPORT, getName()));
    }

    @Override
    public void configOwnDir() {
        getDirs().clear();
        getDirs().add(getBasePath()+ File.separator + "src" + File.separator + "main" + File.separator + "proto");
    }

    @Override
    public void configOwnSourceFile(){
        super.configOwnSourceFile();


        getSourceFiles().add(new SourceFile("pom_proto.xml", getBasePath(),"",  GenEnum.fileType.xml, GenEnum.fileOperatorType.copy));
    }
}
