package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;
import com.zd.tools.project.generator.model.file.SourceFile;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class FileGeneratorUtil {
    private static final Log log = Log.get();

    public static void createProjectStructure(Project project, AbstractModule moduleBo){
        for(String item : moduleBo.getDirs()){
            if(!FileUtil.exist(item)){
//                log.debug(moduleBo.getName()+":"+item);
                FileUtil.mkdir(item);
            }
        }
    }

    public static void createProjectFile(Project project, SourceFile file){
        String sourceFileRoot = Const.SOURCE_ROOT + file.getFileType().name();
        String sourceFileName = file.getName(); //aa.java
        String sourceFileContext = FileUtil.readString(sourceFileRoot +File.separator+ sourceFileName, Charset.defaultCharset());
        String newFileName = "pom" + "."+ file.getFileType().name();

        Map<String, Object> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, AbstractModule> entry : project.getModules().entrySet()) {
            sb.append("\t\t<module>"+project.getName()+"-"+entry.getValue().getName()+"</module>");
            sb.append("\n");
        }
        map.put("moduleList", sb.toString());
        map.putAll(BeanUtil.beanToMap(project));
        String newFileContext = StrFormatterUtil.format(sourceFileContext, map, true);

        String newFilePath = project.getBasePath() + File.separator + file.getPath() + File.separator + newFileName;
        File newFile = new File(newFilePath);
        if (file.getFileOperatorType() == GenEnum.fileOperatorType.create && FileUtil.exist(newFile)){
            FileUtil.del(newFile);
        }

        FileUtil.writeUtf8String(newFileContext, newFile);
    }

    /*
    * project 根目录basePackage ： com.zd.baseframework
    * module       srcpath     ： src/main/java
    *              resourcepth ： src/main/resource
    * 公共的内容，   basePackage ：  src/main/java/com/ddd.../app
    * 差一个复制文件的路径，这个最好用package来表示,再把/再转换到.
    * 所以当前文件的包路径为 moudle的basePackage + file.path
    * new SourceFile("SwaggerConfig","/config/", GenEnum.fileType.java, GenEnum.fileOperatorType.copy));
    * */
    public static void createModuleFile(Project project, AbstractModule module, SourceFile file){
        String sourceFileRoot = Const.SOURCE_ROOT + file.getFileType().name();
        String sourceFileName = file.getName(); //aa.java
        String sourceFileContext = FileUtil.readString(sourceFileRoot +File.separator+ sourceFileName, Charset.defaultCharset());
        String newFileName = sourceFileName;
        if(file.getFileOperatorType() == GenEnum.fileOperatorType.create){
            newFileName = StrUtil.upperFirst(StrUtil.toCamelCase(project.getName(), CharUtil.DASHED)) + sourceFileName;
        }

        Map<String, String> map = module.configProperty();

        for (Map.Entry<String, AbstractModule> entry : project.getModules().entrySet()) {
            map.put(entry.getValue().getType().name() + "Module", project.getBasePackage()+"."+entry.getValue().getName());
            if(entry.getValue().getType()== GenEnum.projectType.fixed){
                map.put(GenEnum.projectType.restful.name() + "Module", project.getBasePackage()+"."+entry.getValue().getName() + ".restful");
                map.put(GenEnum.projectType.grpc.name() + "Module", project.getBasePackage()+"."+entry.getValue().getName() +".grpc");
            }
        }

        map.put("package", project.getBasePackage() + module.getName() + StrUtil.replace(file.getPath(), File.separator, "."));
        map.put("className", newFileName.substring(0, newFileName.lastIndexOf(".")));
        String newFileContext = StrFormatterUtil.format(sourceFileContext, map, true);

        String newFileRoot = "";
        switch (file.getFileType()){
            case source:
                newFileName = newFileName + "ava";//todo 这块的后缀需做成配置的
                newFileRoot = module.getPackagePath();
                break;
            case xml:
                newFileRoot = module.getBasePath();
                newFileName = "pom" + "."+ file.getFileType().name();
                break;
            case yml:
                if(module.getType() != GenEnum.projectType.application){

                }
//                newFileName = "application-dev" + "."+ file.getFileType().name();
                newFileRoot = module.getResourcesPath();
                break;
            case config:
                newFileRoot = module.getResourcesPath();
                break;
            default:
        }
        String newFilePath = newFileRoot + File.separator + file.getPath() + File.separator + newFileName;
        File newFile = new File(newFilePath);
        if (file.getFileOperatorType() == GenEnum.fileOperatorType.create && FileUtil.exist(newFile)){
            FileUtil.del(newFile);
        }

        if(file.getFileOperatorType() == GenEnum.fileOperatorType.append){
            FileUtil.appendUtf8String(newFileContext, newFile);//TODO 取出原来的内容，再写入，这里的pom和yml要特殊处理有顺序的
        }
        FileUtil.writeUtf8String(newFileContext, newFile);
    }

}
