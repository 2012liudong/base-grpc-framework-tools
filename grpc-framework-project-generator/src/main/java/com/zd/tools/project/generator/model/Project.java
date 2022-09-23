package com.zd.tools.project.generator.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Project extends AbstractBo {

    private String groupId;

    private String version;

    private String basePackage;

    private String packaging;

    private Map<String, AbstractModule> modules = new HashMap<>();

}
