package com.zd.tools.project.generator.model.module;

import com.zd.tools.project.generator.model.module.model.ModulePropertyBo;
import lombok.Data;

@Data
public class ModuleGrpc extends ModuleCommon {

    private String grpcPort;

    private ModulePropertyBo modulePropertyBo;
}
