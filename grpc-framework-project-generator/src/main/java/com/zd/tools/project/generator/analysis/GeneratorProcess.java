package com.zd.tools.project.generator.analysis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.log.Log;
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
    public void parse() {
        final Map<String, String> original = getContext().getOriginal();
        //解析project
        Project project = SettingFileConvert.buidlProject(getContext(), original);
        getContext().setProject(project);

        //解析module
        List<AbstractModule>  moduleBos = SettingFileConvert.buildModules(getContext(), original);

        //处理wrapBy属性和setBasePath属性
        WrapTreeUtil.buildTree(project, moduleBos);
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

        log.info("-START-");
        log.info("Start build project:"+ getContext().getProject().getName());
        for(SourceFile file : getContext().getProject().getSourceFiles()){
            log.info(Const.C_TAB + Const.C_TAB + "...file... " + file.getPath() + file.getName());
            FileGeneratorUtil.createProjectFile(getContext().getProject(), file);
        }
        log.info(Const.LOG_SPLIT_DOT);

        log.info("Start build modules totalSize:"+ getContext().getProject().getModules().size());
        int count = 0;
        for(AbstractModule moduleBo : getContext().getProject().getModules().values()){
            log.info(Const.C_TAB + (++count) + "." + moduleBo.getArtifactId());

            for(String dir : moduleBo.getDirs()){
                log.info(Const.C_TAB + Const.C_TAB + "...dir... " + dir);
                FileGeneratorUtil.createProjectStructure(getContext().getProject(), dir);
            }

            for (SourceFile item : moduleBo.getSourceFiles()) {
                log.info(Const.C_TAB + Const.C_TAB + "...file... " + item.getPath() +File.separator+ item.getName());
                moduleBo.getConfigPropertyMap().put(Const.PATH_SPRINGBOOT_FILE, getContext().getSpringBootResource());
                FileGeneratorUtil.createModuleFile(getContext().getProject(), moduleBo, item);
            }
            log.info(Const.LOG_SPLIT_DOT_SHORT);
        }
        log.info("-END-");
    }
}
