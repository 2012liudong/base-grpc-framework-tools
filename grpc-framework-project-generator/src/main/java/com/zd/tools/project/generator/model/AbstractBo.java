package com.zd.tools.project.generator.model;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public abstract class AbstractBo {

    private String basePath;

    private String name;

    private Set<String> dirs = new HashSet<>();

    private Map<String, String> files = new HashMap<>();

}
