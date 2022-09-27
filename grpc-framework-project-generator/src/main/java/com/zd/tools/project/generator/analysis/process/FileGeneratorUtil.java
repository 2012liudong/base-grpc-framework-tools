package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;
import com.zd.tools.project.generator.model.file.SourceFile;
import com.zd.tools.project.generator.model.module.ModulePersistence;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class FileGeneratorUtil {
    private static final Log log = Log.get();

    /**创建工程目录*/
    public static void createProjectStructure(Project project, AbstractModule moduleBo){
        for(String item : moduleBo.getDirs()){
            if(!FileUtil.exist(item)){
                FileUtil.mkdir(item);
            }
        }
    }

    /**parent 相关的文件，主要是pom.xml*/
    public static void createProjectFile(Project project, SourceFile file){
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

        String pomFilePath = project.getBasePath() + File.separator + file.getPath() + File.separator + Const.FILENAME_POP;
        File pomFile = new File(pomFilePath);
        if ( FileUtil.exist(pomFile) ){
            FileUtil.del(pomFile);
        }
        FileUtil.writeUtf8String(pomFileContext, pomFile);
    }

    public static void createModuleFile(Project project, AbstractModule module, SourceFile file){
        String sourceFileRoot = Const.SOURCE_ROOT + file.getFileType().name();
        String sourceFileName = file.getName();
        String sourceFileContext = FileUtil.readString(sourceFileRoot +File.separator+ sourceFileName, Charset.defaultCharset());

        //如果是新创建类型则重新起一个名字
        String newFileName = sourceFileName;
        if(file.getFileOperatorType() == GenEnum.fileOperatorType.create){
            newFileName = StrUtil.upperFirst(StrUtil.toCamelCase(project.getName(), CharUtil.DASHED)) + sourceFileName;
        }

        /*设置文件中需要替换的占位符*/
        Map<String, Object> placeHodlerValueMap = new HashMap<>(BeanUtil.beanToMap(module));
        placeHodlerValueMap.putAll(module.getConfigPropertyMap());

        //设置模块名称，key=模块类型+后缀, value=artifactId
        for (Map.Entry<String, AbstractModule> entry : project.getModules().entrySet()) {
            String a = project.getBasePackage() +"." + entry.getValue().getName() + StrUtil.replace(file.getPath(), File.separator, ".");
            placeHodlerValueMap.put(entry.getValue().getType().name() + Const.PLACEHOLDER_MODULE_NAME_SUFFIX, a);
        }

        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PACKAGE, project.getBasePackage() +"." + module.getName() + StrUtil.replace(file.getPath(), File.separator, "."));
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_CLASS_NAME, newFileName.substring(0, newFileName.lastIndexOf(".")));
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PARENT_MODULE, project.getName() + Const.PLACEHOLDER_MODULE_NAME_PARENT);

        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PROJECT_NAME, project.getName() );
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PARENT_GROUP_ID, project.getGroupId());
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PARENT_VERSION, project.getVersion());

        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_ARTIFACTID, module.getArtifactId());
        placeHodlerValueMap.put(Const.PLACEHOLDER_MODULE_PACKAGING, module.getPackaging());


        Map<String, Object> s = BeanUtil.beanToMap(project.getModulePropertyBo());
        for (Map.Entry<String, Object> entry : s.entrySet()) {
            // TODO 这里的值很多 System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            if(ObjectUtil.isNotEmpty(entry.getValue())){
                placeHodlerValueMap.put(entry.getKey(), (String) entry.getValue());
            }

        }
        //先设置空值，为了配合替换方法 ignoreNull 参数
        placeHodlerValueMap.put("fixedModuleArtifactId", null);
        placeHodlerValueMap.put("restfulModuleArtifactId", null);
        placeHodlerValueMap.put("grpcModuleArtifactId", null);
        for (Map.Entry<String, AbstractModule> entry : project.getModules().entrySet()) {
            placeHodlerValueMap.put(entry.getValue().getType().name()+"ModuleArtifactId"  , entry.getValue().getArtifactId());
            if(entry.getValue().getType() == GenEnum.projectType.persistence){
                ModulePersistence a = (ModulePersistence) entry.getValue();
                placeHodlerValueMap.put("mapper", a.getMapper());
            }
        }

        String newFileContext = StrFormatterUtil.format(sourceFileContext, placeHodlerValueMap, false);

        String newFileRoot = "";
        switch (file.getFileType()){
            case source: //SRC源文件根目录
                newFileName = newFileName + "ava";
                newFileRoot = module.getPackagePath();
                break;
            case xml: //pom文件模块根目录
                newFileRoot = module.getBasePath();
                newFileName = Const.FILENAME_POP;
                break;
            case yml://属性文件，放在RESOURCE目录下
                if(module.getType() != GenEnum.projectType.application){
                    newFileName = Const.FILENAME_SPRING;
                }
                newFileRoot = module.getResourcesPath();
                break;
            case config: //配置文件，放在RESOURCE目录下
                newFileRoot = module.getResourcesPath();
                break;
            default:
        }
        String newFilePath = newFileRoot + File.separator + file.getPath() + File.separator + newFileName;

        if(file.getFileType() == GenEnum.fileType.yml && file.getFileOperatorType() == GenEnum.fileOperatorType.append){
            newFilePath = placeHodlerValueMap.get(Const.PATH_SPRINGBOOT_FILE) + File.separator + Const.FILENAME_SPRING;
        }

        File newFile = new File(newFilePath);
        if (file.getFileOperatorType() == GenEnum.fileOperatorType.create && FileUtil.exist(newFile)){
            FileUtil.del(newFile);
        }

        if(file.getFileOperatorType() == GenEnum.fileOperatorType.append){
            FileUtil.appendUtf8String(newFileContext, newFile);
        }else{
            FileUtil.writeUtf8String(newFileContext, newFile);
        }
    }

}
