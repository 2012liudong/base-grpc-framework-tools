package com.zd.tools.project.generator;

import cn.hutool.log.Log;
import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;
import com.zd.tools.project.generator.analysis.Context;
import com.zd.tools.project.generator.analysis.GeneratorProcess;

import java.io.File;

public class MainClass {
    private static final Log log = Log.get();

    private final static String projectSettingFileName = "project.setting";

    public static void main(String []args){
        String currentPath = MainClass.mainPath();
        Setting setting =  SettingUtil.get(currentPath + File.separator + projectSettingFileName);

        Context context = new Context(currentPath, setting.getGroupedMap().get(""));

        new GeneratorProcess(context).process();
    }

    private static String mainPath(){
        String path = MainClass.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf("."));
            path = path.substring(0, path.lastIndexOf("/"));
        }
        return path;
    }
}
