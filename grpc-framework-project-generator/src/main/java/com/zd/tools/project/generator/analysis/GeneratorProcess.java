package com.zd.tools.project.generator.analysis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.zd.tools.project.generator.analysis.process.FileGeneratorUtil;
import com.zd.tools.project.generator.analysis.process.SettingFileConvert;
import com.zd.tools.project.generator.analysis.process.WrapTreeUtil;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.consts.GenEnum;
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
    protected void validate() {

    }

    @Override
    public void parse() {
        final Map<String, String> original = getContext().getOriginal();
        Project project = SettingFileConvert.buidlProject(getContext(), original);
        project.setBasePath(getContext().getRootPath() + Const.PATH_ROOT + project.getName() + File.separator);
        getContext().setProject(project);

        List<AbstractModule>  moduleBos = SettingFileConvert.buildModules(getContext(), original);

        WrapTreeUtil.buildTree(project, moduleBos);

        project.configOwnSourceFile();

        for(AbstractModule item: moduleBos){
            //设置文件目录
            item.setSrcPath(item.getBasePath()+ File.separator + "src" + File.separator + "main" + File.separator + "java");
            item.setResourcesPath(item.getBasePath()+ File.separator + "src" + File.separator + "main" + File.separator + "resources");
            item.setPackagePath(item.getBasePath()+ File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + StrUtil.replace(project.getBasePackage(), ".", File.separator) + File.separator + item.getName());

            //设置公共的目录
            item.getDirs().add(item.getBasePath());
            item.getDirs().add(item.getSrcPath());
            item.getDirs().add(item.getResourcesPath());
            item.getDirs().add(item.getPackagePath());

            //设置个性化的目录
            item.configOwnDir();

            item.configOwnSourceFile();

            project.getModules().put(item.getName(), item);

            if(item.getType() == GenEnum.projectType.application ){
                getContext().setSpringBoot(item.getResourcesPath());
            }
        }
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
        for(SourceFile item : getContext().getProject().getSourceFiles()){
            FileGeneratorUtil.createProjectFile(getContext().getProject(), item);
        }

        for(AbstractModule moduleBo : getContext().getProject().getModules().values()){
            log.info("start build "+ moduleBo.getName());
            FileGeneratorUtil.createProjectStructure(getContext().getProject(), moduleBo);

            for (SourceFile item : moduleBo.getSourceFiles()) {
                moduleBo.getConfigPropertyMap().put(Const.PATH_SPRINGBOOT_FILE, getContext().getSpringBoot());
                FileGeneratorUtil.createModuleFile(getContext().getProject(), moduleBo, item);
            }
        }
    }
}
