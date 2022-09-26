package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.model.AbstractModule;
import lombok.Data;

import java.io.File;

@Data
public class ModuleProto extends AbstractModule {

    @Override
    public void configOwnDir() {
        getDirs().clear();
        getDirs().add(getBasePath()+ File.separator + "src" + File.separator + "main" + File.separator + "proto");
    }
}
