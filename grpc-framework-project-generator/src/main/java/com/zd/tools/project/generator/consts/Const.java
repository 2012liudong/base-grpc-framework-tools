package com.zd.tools.project.generator.consts;

import java.io.File;

public interface Const {

    String PATH_ROOT = "output" + File.separator;

    String ERR_MODULE_NOTCONFIG = "Module({}) isn't config";
    String ERR_MODULE_TYPE = "ModuleType({}) value of module[{}] is invalid, must be one of proto、api、application、persistence、common、restful、grpc or fixed ";
}
