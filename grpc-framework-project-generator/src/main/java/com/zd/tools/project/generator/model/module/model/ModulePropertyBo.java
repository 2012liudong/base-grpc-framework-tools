package com.zd.tools.project.generator.model.module.model;

import com.zd.tools.project.generator.model.AbstractBo;
import lombok.Data;

@Data
public class ModulePropertyBo extends AbstractBo {

    private String tokenKey;

    private String apiPath;

}
