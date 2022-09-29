package com.zd.tools.project.generator.consts;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;

import java.io.File;

public interface Const {

    String C_LF = StrPool.LF;//换行符
    String C_TAB = StrPool.TAB;//TAB符
    String C_SLASH = StrPool.SLASH;//斜杠
    String C_BACKSLASH = StrPool.BACKSLASH;//反斜杠

    String PROJECT_SETTING_FILE_NAME = "project.setting";
    String PROJECT_EXT_SOURCE_CONFIG_FILE_NAME = "sltext.json";

    // 工程输出目录
    String PATH_ROOT = "/output" + File.separator;

    //工具内置的源文件根目录
    String SOURCE_ROOT = "/slt/source/";

    String FILENAME_POP = "pom.xml";
    String FILENAME_SPRING = "application-dev.properties";

    /*-----创建project相关，没有目录只有一个根pom.xml文件*/
    //pom.xml中<modules>中内容
    String PLACEHOLDER_PROJECT_POM_MODULES  = "moduleList";

    /*------创建源文件*/
    //package 声明
    String PLACEHOLDER_MODULE_PACKAGE = "package";
    //import 引入
    String PLACEHOLDER_MODULE_NAME_SUFFIX = "Module";
    String PRE_PLACEHOLDER_FIXED_MODULE_IMPORT = GenEnum.projectType.fixed +PLACEHOLDER_MODULE_NAME_SUFFIX;
    String PRE_PLACEHOLDER_RESTFUL_MODULE_IMPORT = GenEnum.projectType.restful +PLACEHOLDER_MODULE_NAME_SUFFIX;
    String PRE_PLACEHOLDER_GRPC_MODULE_IMPORT = GenEnum.projectType.grpc +PLACEHOLDER_MODULE_NAME_SUFFIX;
    //className定义
    String PLACEHOLDER_MODULE_CLASS_NAME = "className";
    //grpc拦截器特殊处理
    String PLACEHOLDER_MODULE_CLASS_NAME_GRPC_ACCESS = "grpcAccessIntereptor";
    String PLACEHOLDER_MODULE_CLASS_NAME_GRPC_TOKEN = "grpcTokenIntereptor";
    String CLASS_NAME_GRPC_REGISTER = "GrpInterceptorRegister";


    /*------创建module结构相关*/
    //创建root工程相关内容
    String MODULE_PARENT_NAME__SUFFIX = "-parent";
    String PLACEHOLDER_MODULE_PROJECT_NAME = "projectName";

    String PATH_MODULE_SOURCE   = File.separator + "src" + File.separator + "main" + File.separator + "java";
    String PATH_MODULE_RESOURCE = File.separator + "src" + File.separator + "main" + File.separator + "resources";

    //创建子工程pom.xml相关
    //<parent>节点设置
    String PLACEHOLDER_MODULE_PARENT_MODULE_ARTIFACT_ID = "parentModule";
    String PLACEHOLDER_MODULE_PARENT_GROUP_ID = "parentGroupId";
    String PLACEHOLDER_MODULE_PARENT_VERSION = "parentVersion";

    //子模块设置
    String PLACEHOLDER_MODULE_ARTIFACT_ID = "artifactId";
    String PLACEHOLDER_MODULE_PACKAGING = "packaging";

    //模块间相互引用
    //application特殊设置
    String PRE__MODULE_ARTIFACT_ID_SUFFIX = "ModuleArtifactId";
    String PRE_PLACEHOLDER_FIXED_MODULE_ARTIFACT_ID = GenEnum.projectType.fixed +PRE__MODULE_ARTIFACT_ID_SUFFIX;
    String PRE_PLACEHOLDER_RESTFUL_MODULE_ARTIFACT_ID = GenEnum.projectType.restful +PRE__MODULE_ARTIFACT_ID_SUFFIX;
    String PRE_PLACEHOLDER_GRPC_MODULE_ARTIFACT_ID = GenEnum.projectType.grpc +PRE__MODULE_ARTIFACT_ID_SUFFIX;
    String PLACEHOLDER_MODULE_PERSISTENCE_MAPPER = "mapper";

    /*------创建springboot配置文件*/
    String PATH_SPRINGBOOT_FILE = "springBootResource";

    //错误信息
    String ERR_MODULE_NOTCONFIG = "Module({}) isn't config";
    String ERR_MODULE_TYPE = "ModuleType({}) value of module[{}] is invalid, must be one of proto、api、application、persistence、common、restful、grpc or fixed ";
    String ERR_MODULE_NOSUPPORT = "{} isn't support wrapBy attribute";

    String LOG_SPLIT_DOT =       "--------------------------------------------------------------------------------------";
    String LOG_SPLIT_DOT_SHORT = ".........................................................................";

}
