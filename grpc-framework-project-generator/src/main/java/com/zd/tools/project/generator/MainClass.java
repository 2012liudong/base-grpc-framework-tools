package com.zd.tools.project.generator;

import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;

import java.io.File;

public class MainClass {

    private final static String projectSettingFileName = "project.setting";

    public static void main(String []args){
        String mainPath = MainClass.mainPath();
        Setting setting =  SettingUtil.get(mainPath + File.separator + projectSettingFileName);
        System.out.println(setting);
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
