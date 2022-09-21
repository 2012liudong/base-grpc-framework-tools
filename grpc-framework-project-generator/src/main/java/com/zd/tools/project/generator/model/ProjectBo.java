package com.zd.tools.project.generator.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProjectBo extends BaseBo {

    private String groupId;

    private String version;

    private String basePackage;

    private String packaging;

    private Map<String, ModuleBo> modules = new HashMap<>();


}
