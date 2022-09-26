package com.zd.tools.project.generator.consts;

import cn.hutool.core.io.resource.ResourceUtil;

import java.io.File;

public interface Const {

    String PATH_ROOT = "output" + File.separator;

    String ERR_MODULE_NOTCONFIG = "Module({}) isn't config";
    String ERR_MODULE_TYPE = "ModuleType({}) value of module[{}] is invalid, must be one of proto、api、application、persistence、common、restful、grpc or fixed ";

    String ERR_MODULE_NOSUPPORT = "{} isn't support wrapBy attribute";


    String SOURCE_ROOT = ResourceUtil.getResource(".").getPath() + "slt/source/";
}
