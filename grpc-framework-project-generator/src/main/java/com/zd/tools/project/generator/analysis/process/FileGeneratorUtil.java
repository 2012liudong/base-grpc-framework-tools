package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.io.FileUtil;
import cn.hutool.log.Log;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;

public class FileGeneratorUtil {
    private static final Log log = Log.get();

    public static void createProjectStructure(Project project, AbstractModule moduleBo){
        for(String item : moduleBo.getDirs()){
            if(!FileUtil.exist(item)){
                log.debug(moduleBo.getName()+":"+item);
                FileUtil.mkdir(item);
            }

        }
    }
}
