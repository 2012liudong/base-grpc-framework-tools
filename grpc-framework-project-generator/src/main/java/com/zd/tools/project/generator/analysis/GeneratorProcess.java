package com.zd.tools.project.generator.analysis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.zd.tools.project.generator.ExtCache;
import com.zd.tools.project.generator.analysis.process.FileGeneratorUtil;
import com.zd.tools.project.generator.analysis.process.SettingFileConvert;
import com.zd.tools.project.generator.analysis.process.WrapTreeUtil;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;
import com.zd.tools.project.generator.model.file.SourceFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public class GeneratorProcess extends AbstractProcess{

    private static final Log log = Log.get();

    public GeneratorProcess(Context context) {
        super(context);
    }

    @Override
    public void before() {
        log.info("---START---");
        super.before();
        if (FileUtil.exist(ExtCache.currentPath + File.separator + ExtCache.sltPath + Const.PROJECT_EXT_SOURCE_CONFIG_FILE_NAME)){
            log.info("Start parse ext-source config file: " +  ExtCache.sltPath + Const.PROJECT_EXT_SOURCE_CONFIG_FILE_NAME);
            log.info("Load ext-source success");

            for (Map.Entry<String, List<SourceFile>> entry : ExtCache.extSourceFile.entrySet()) {
                if(CollUtil.isNotEmpty(entry.getValue())){
                    log.info(Const.C_TAB + Const.C_TAB
                            + entry.getKey() + ": fileSize: " + CollUtil.size(entry.getValue()));
                }
            }

            log.info(Const.LOG_SPLIT_DOT);
        }
    }

    @Override
    public void parse() {
        log.info("Start parse SettingFile: " + ExtCache.projectSettingFile);

        final Map<String, String> original = getContext().getOriginal();
        //解析project
        Project project = SettingFileConvert.buidlProject(getContext(), original);
        getContext().setProject(project);

        //解析module
        List<AbstractModule>  moduleBos = SettingFileConvert.buildModules(getContext(), original);

        //处理wrapBy属性和setBasePath属性
        WrapTreeUtil.buildTree(project, moduleBos);
        log.info("Parse SettingFile success");
    }

    @Override
    protected void clear() {
        File file = new File(getContext().getProject().getBasePath());
        if(file.exists() &&file.isDirectory() ){
            FileUtil.clean(file);
        }
    }

    @Override
    public void build() {

        log.info("Start build project: "+ getContext().getProject().getName());
        for(String dir : getContext().getProject().getDirs()){
            log.info(Const.C_TAB + Const.C_TAB + "...dir...."
                    + StrUtil.subAfter(dir, ExtCache.currentPath + Const.PATH_ROOT, false));
            FileGeneratorUtil.createProjectStructure(getContext().getProject(), dir);
        }

        for(SourceFile file : getContext().getProject().getSourceFiles()){
            log.info(Const.C_TAB + Const.C_TAB + "...file..."
                    + StrUtil.subAfter(file.getPath(), ExtCache.currentPath + Const.PATH_ROOT, false) + file.getName()
                    + " -> "+file.getFileOperatorType().name());
            FileGeneratorUtil.createProjectFile(getContext().getProject(), file);
        }
        log.info(Const.LOG_SPLIT_DOT);


        log.info("Start build modules: totalSize="+ getContext().getProject().getModules().size());
        int count = 0;
        for(AbstractModule moduleBo : getContext().getProject().getModules().values()){
            log.info(Const.C_TAB + (++count) + "." + moduleBo.getArtifactId());

            for(String dir : moduleBo.getDirs()){
                log.info(Const.C_TAB + Const.C_TAB + "...dir...."
                        + StrUtil.subAfter(dir, ExtCache.currentPath + Const.PATH_ROOT, false));
                FileGeneratorUtil.createProjectStructure(getContext().getProject(), dir);
            }

            for (SourceFile file : moduleBo.getSourceFiles()) {
                log.info(Const.C_TAB + Const.C_TAB + "...file..."
                        + StrUtil.subAfter(file.getPath(), ExtCache.currentPath + Const.PATH_ROOT, false) + File.separator + file.getName()
                        + " -> "+file.getFileOperatorType().name());
                moduleBo.getConfigPropertyMap().put(Const.PATH_SPRINGBOOT_FILE, getContext().getSpringBootResource());
                FileGeneratorUtil.createModuleFile(getContext().getProject(), moduleBo, file);
            }
            log.info(Const.LOG_SPLIT_DOT_SHORT);
        }
        log.info("-END-");
    }
}
