package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.model.module.model.ModulePropertyBo;
import lombok.Data;

@Data
public class ModuleRestful extends ModuleCommon {

    private String port;

    private ModulePropertyBo modulePropertyBo;

}
