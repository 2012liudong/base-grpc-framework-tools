package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.model.AbstractModule;
import lombok.Data;

import java.io.File;

@Data
public class ModuleApplication extends AbstractModule {

    @Override
    public void configOwnDir() {
        this.getDirs().add(getPackagePath() + File.separator + "config");
    }
}
