package com.zd.tools.project.generator.model.module;

import lombok.Data;

import java.io.File;

@Data
public class ModulePersistence extends ModuleCommon {

    private String mapper;

    private String ip;

    private String dbPort;

    private String username;

    private String password;

    @Override
    public void configOwnDir() {
        this.getDirs().add(getResourcesPath() + File.separator + getMapper());
    }
}
