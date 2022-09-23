package com.zd.tools.project.generator.analysis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.zd.tools.project.generator.analysis.process.FileGeneratorUtil;
import com.zd.tools.project.generator.analysis.process.SettingFileConvert;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;

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

        for(AbstractModule item: moduleBos){
            item.setBasePath( project.getBasePath() + project.getName() + "-" +item.getName());
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

            project.getModules().put(item.getName(), item);
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
        for(AbstractModule moduleBo : getContext().getProject().getModules().values()){
            FileGeneratorUtil.createProjectStructure(getContext().getProject(), moduleBo);
        }
    }
}
