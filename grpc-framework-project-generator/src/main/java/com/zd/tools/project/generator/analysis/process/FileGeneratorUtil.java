package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;
import com.zd.tools.project.generator.model.file.SourceFile;
import com.zd.tools.project.generator.model.module.ModulePersistence;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class FileGeneratorUtil {

    /**创建模块目录*/
    public static void createProjectStructure(Project project, String dir){
        if(!FileUtil.exist(dir)){
            FileUtil.mkdir(dir);
        }
    }

    /**创建主pom.xml文件*/
    public static void createProjectFile(final Project project, final SourceFile file){
        //读取源文件内容
        String sourceFileRoot = Const.SOURCE_ROOT + file.getFileType().name();
        String sourceFileContext = FileUtil.readString(sourceFileRoot +File.separator+ file.getName(), Charset.defaultCharset());

        //替换文件成最终内容
        Map<String, Object> map = new HashMap<>(BeanUtil.beanToMap(project));
        StringBuilder sb = new StringBuilder();
        sb.append(Const.C_LF);
        for (Map.Entry<String, AbstractModule> entry : project.getModules().entrySet()) {
            sb.append(Const.C_TAB).append(Const.C_TAB).append("<module>"+project.getName()+"-"+entry.getValue().getName()+"</module>");
            sb.append(Const.C_LF);
        }
        map.put(Const.PLACEHOLDER_PROJECT_POM_MODULES, sb.toString());
        String pomFileContext = StrFormatterUtil.format(sourceFileContext, map, true);

        String pomFilePath = file.getPath() + File.separator + Const.FILENAME_POP;
        File pomFile = new File(pomFilePath);
        if ( FileUtil.exist(pomFile) ){
            FileUtil.del(pomFile);
        }
        FileUtil.writeUtf8String(pomFileContext, pomFile);
    }

    public static void createModuleFile(final Project project, final AbstractModule module, final SourceFile file){
        String sourceFileRoot = Const.SOURCE_ROOT + file.getFileType().name();
        String sourceFileName = file.getName();
        String sourceFileContext = FileUtil.readString(sourceFileRoot +File.separator+ sourceFileName, Charset.defaultCharset());

        /*设置文件中需要替换的占位符*/
        Map<String, Object> placeHodlerValueMap = new HashMap<>(BeanUtil.beanToMap(module));
        placeHodlerValueMap.putAll(module.getConfigPropertyMap());

        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PROJECT_NAME, project.getName() );

        switch (file.getFileType()){
            case source:
                configSourcePlaceHolderProperties(project, module, file, placeHodlerValueMap);
                break;
            case xml:
                configPomPlaceHolderProperties(project, module, file, placeHodlerValueMap);
                break;
            case yml:
                configSpringBootPlaceHolderProperties(project, module, file, placeHodlerValueMap);
                break;
            case config:
                break;
            default:
                break;
        }

        //如果是新创建类型则重新起一个名字，因为涉及到类的名称，所以要
        String newFileName = sourceFileName;
        if(file.getFileOperatorType() == GenEnum.fileOperatorType.create){
            newFileName = String.valueOf(placeHodlerValueMap.get( Const.PLACEHOLDER_MODULE_CLASS_NAME )
            +sourceFileName.substring(sourceFileName.lastIndexOf(".")));
        }

        //得到用占位符替换过的新文件内容
        String newFileContext = StrFormatterUtil.format(sourceFileContext, placeHodlerValueMap, false);

        switch (file.getFileType()){
            case xml:
                newFileName = Const.FILENAME_POP;
                break;
            case yml:
            case source:
            case config:
            default:
                break;
        }

        //新文件全路径
        String newFilePath = file.getPath() + File.separator + newFileName;

        /*append文件写入：append，取出原文件内容*/
        if(file.getFileOperatorType() == GenEnum.fileOperatorType.append){
            if(file.getFileType() == GenEnum.fileType.yml){
                newFilePath = placeHodlerValueMap.get(Const.PATH_SPRINGBOOT_FILE) + File.separator + Const.FILENAME_SPRING;
            }
            FileUtil.appendUtf8String(newFileContext, newFilePath);
        }else{
            FileUtil.writeUtf8String(newFileContext, newFilePath);
        }
    }

    /**拷贝源码文件：1、设置模块包的根目录，用于拷贝源码文件，格式key=moduleName+"."+Module + SourceFile.filePath，
     * 例子如：import ${commonModule}.spring.ApperProperties; map={commonModule, AbstractModuleName};
     * 2、package，用于替换源码包路径，例子如：package ${package};
     * */
    private static void configSourcePlaceHolderProperties(Project project, AbstractModule module, SourceFile file, Map<String, Object> placeHodlerValueMap){
        placeHodlerValueMap.put(Const.PRE_PLACEHOLDER_FIXED_MODULE_IMPORT, null);
        placeHodlerValueMap.put(Const.PRE_PLACEHOLDER_RESTFUL_MODULE_IMPORT, null);
        placeHodlerValueMap.put(Const.PRE_PLACEHOLDER_GRPC_MODULE_IMPORT, null);
        for (Map.Entry<String, AbstractModule> entry : project.getModules().entrySet()) {
            String packageImport = project.getBasePackage() +"." + entry.getValue().getName();// + StrUtil.replace(file.getPath(), File.separator, ".");
            placeHodlerValueMap.put(entry.getValue().getType().name() + Const.PLACEHOLDER_MODULE_NAME_SUFFIX, packageImport);
        }
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PACKAGE, project.getBasePackage() +"." + module.getName() + StrUtil.replace(file.getPackagePath(), File.separator, "."));

        if(file.getFileOperatorType() == GenEnum.fileOperatorType.create){
            String newFileName = StrUtil.upperFirst(StrUtil.toCamelCase(project.getName(), CharUtil.DASHED)) + file.getName();
            placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_CLASS_NAME, newFileName.substring(0, newFileName.lastIndexOf(".")));
            if(module.getType() == GenEnum.projectType.grpc || module.getType() == GenEnum.projectType.fixed){
                if(StrUtil.contains(file.getName(), Const.CLASS_NAME_GRPC_REGISTER)){
                    placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_CLASS_NAME_GRPC_TOKEN, StrUtil.upperFirst(StrUtil.toCamelCase(project.getName(), CharUtil.DASHED)) + "GrpcTokenInterceptor");
                    placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_CLASS_NAME_GRPC_ACCESS, StrUtil.upperFirst(StrUtil.toCamelCase(project.getName(), CharUtil.DASHED)) + "GrpcAccessInterceptor");
                }
            }
        }
    }

    /**创建pom.xml文件：
     * 1、先创建pom.xml，要优先设置一些节点，<parent></parent>
     * 2、自定义模块引入 <dependency></dependency>
     * 3、特殊定义<plugs></plugs>
     * */
    private static void configPomPlaceHolderProperties(Project project, AbstractModule module, SourceFile file, Map<String, Object> placeHodlerValueMap){
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PARENT_MODULE_ARTIFACT_ID, project.getName() + Const.MODULE_PARENT_NAME__SUFFIX);
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PARENT_GROUP_ID, project.getGroupId());
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PARENT_VERSION, project.getVersion());

        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_ARTIFACT_ID, module.getArtifactId());
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PACKAGING, module.getPackaging());

        //先设置空值，为了配合替换方法 ignoreNull 参数
        placeHodlerValueMap.put(Const.PRE_PLACEHOLDER_FIXED_MODULE_ARTIFACT_ID, null);
        placeHodlerValueMap.put(Const.PRE_PLACEHOLDER_RESTFUL_MODULE_ARTIFACT_ID, null);
        placeHodlerValueMap.put(Const.PRE_PLACEHOLDER_GRPC_MODULE_ARTIFACT_ID, null);

        for (Map.Entry<String, AbstractModule> entry : project.getModules().entrySet()) {
            placeHodlerValueMap.put(entry.getValue().getType().name() + Const.PRE__MODULE_ARTIFACT_ID_SUFFIX, entry.getValue().getArtifactId());
            if(entry.getValue().getType() == GenEnum.projectType.persistence){
                placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PERSISTENCE_MAPPER, ((ModulePersistence) entry.getValue()).getMapper());
            }
        }
    }

    /**创建springboot资源文件：
     * 1、project中的ModulePropertyBo属性;
     * 2、parentVersion*/
    private static void configSpringBootPlaceHolderProperties(Project project, AbstractModule module, SourceFile file, Map<String, Object> placeHodlerValueMap){
        placeHodlerValueMap.putAll(BeanUtil.beanToMap(project.getModulePropertyBo()));
    }

}
