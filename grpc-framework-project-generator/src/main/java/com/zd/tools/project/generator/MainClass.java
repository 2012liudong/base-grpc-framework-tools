package com.zd.tools.project.generator;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;
import com.zd.tools.project.generator.analysis.Context;
import com.zd.tools.project.generator.analysis.GeneratorProcess;
import com.zd.tools.project.generator.consts.Const;

import java.io.File;
import java.util.List;

public class MainClass {
    private static final Log log = Log.get();

    public static void main(String []args){

        ExtCache.currentPath = MainClass.mainPath();

        if(ArrayUtil.isNotEmpty(args)){
            for(String param : args){
                List<String> item = StrUtil.split(param, "=");
                if("setting".equals(StrUtil.trim(item.get(0)))){
                    ExtCache.projectSettingFile = StrUtil.trim(item.get(1));
                }
                if("slt".equals(StrUtil.trim(item.get(0)))){
                    ExtCache.sltPath = File.separator + StrUtil.trim(item.get(1)) + File.separator;
                }
            }
        }

        Setting setting =  SettingUtil.get(ExtCache.currentPath + File.separator + ExtCache.projectSettingFile);

        Context context = new Context(ExtCache.currentPath, setting.getGroupedMap().get(""));

        new GeneratorProcess(context).process();
    }

    private static String mainPath(){
        String path = MainClass.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf("."));
            path = path.substring(0, path.lastIndexOf(Const.C_SLASH));
        }
        return path;
    }
}
