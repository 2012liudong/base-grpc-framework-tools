package com.zd.tools.project.generator.consts;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.StrPool;

import java.io.File;

public interface Const {

    String PATH_ROOT = "output" + File.separator;

    String ERR_MODULE_NOTCONFIG = "Module({}) isn't config";
    String ERR_MODULE_TYPE = "ModuleType({}) value of module[{}] is invalid, must be one of proto、api、application、persistence、common、restful、grpc or fixed ";

    String ERR_MODULE_NOSUPPORT = "{} isn't support wrapBy attribute";


    String SOURCE_ROOT = ResourceUtil.getResource(".").getPath() + "slt/source/";

    String FILENAME_POP = "pom.xml";

    String FILENAME_SPRING = "application-dev.properties";


    String C_LF = String.valueOf(StrPool.C_LF);//换行符
    String C_TAB = String.valueOf(StrPool.C_TAB);//TAB符

    //project
    String PLACEHOLDER_PROJECT_POM_MODULES  = "moduleList";

    //persistence
    String PLACEHOLDER_MODULE_PERSISTENCE_MAPPER = "mapper";



    //module
    String PLACEHOLDER_MODULE_NAME_PARENT = "-parent";
    String PLACEHOLDER_MODULE_NAME_SUFFIX = "Module";

    String PLACEHOLDER_MODULE_PACKAGE = "package";
    String PLACEHOLDER_MODULE_CLASS_NAME = "className";
    String PLACEHOLDER_MODULE_PARENT_MODULE = "parentModule";
    String PLACEHOLDER_MODULE_PROJECT_NAME = "projectName";
    String PLACEHOLDER_MODULE_PARENT_GROUP_ID = "parentGroupId";
    String PLACEHOLDER_MODULE_PARENT_VERSION = "parentVersion";
    String PLACEHOLDER_MODULE_ARTIFACTID = "artifactId";
    String PLACEHOLDER_MODULE_PACKAGING = "packaging";

    String PATH_MODULE_RESTFUL = "restful";
    String PATH_MODULE_GRPC = "grpc";

    String PATH_SPRINGBOOT_FILE = "springBoot";

    String PLACEHOLDER_SPRINGBOOT_PROJECT_NAME = "projectName";
    String PLACEHOLDER_SPRINGBOOT_APPLICATION_TOKENKEY = "tokenKey";
    String PLACEHOLDER_SPRINGBOOT_APPLICATION_APIPATH = "apiPath";
    String PLACEHOLDER_SPRINGBOOT_RESTFUL_PROT = "port";

    String PLACEHOLDER_SPRINGBOOT_GRPC_PORT = "grpcPort";

    String PLACEHOLDER_SPRINGBOOT_MYSQL_IP = "ip";
    String PLACEHOLDER_SPRINGBOOT_MYSQL_PORT = "dbPort";
    String PLACEHOLDER_SPRINGBOOT_MYSQL_DBNAME = "dbName";
    String PLACEHOLDER_SPRINGBOOT_MYSQL_USERNAME = "username";
    String PLACEHOLDER_SPRINGBOOT_MYSQL_PASSWORD = "password";
    String PLACEHOLDER_SPRINGBOOT_MAPPER = "mapper";

}
