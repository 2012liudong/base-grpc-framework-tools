package com.zd.tools.project.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class MainClass {

    private final static String projectSettingFileName = "project.setting";

    public static void main(String []args){
        String mainPath = MainClass.mainPath();
        Setting setting =  SettingUtil.get(mainPath + File.separator + projectSettingFileName);
        Map<String, String> map =  new HashMap<>();
        map.put("ddddd", null);
        String readString = FileUtil.readString(new File("/Users/liudong/ideaWS/GitHub/base-grpc-framework-tools/grpc-framework-project-generator/src/main/resources/slt/pom_parent.xml"),
                Charset.forName("UTF-8"));
        System.out.println(StrFormatter.format(readString, map, false));
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
