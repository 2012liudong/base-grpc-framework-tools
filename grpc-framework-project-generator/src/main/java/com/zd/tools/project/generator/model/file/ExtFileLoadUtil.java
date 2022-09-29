package com.zd.tools.project.generator.model.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zd.tools.project.generator.ExtCache;
import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.consts.GenEnum;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtFileLoadUtil {
    public static Map<String, List<SourceFile>> loadExtFile(){
        Map<String, List<SourceFile>> extSourceFile = new HashMap<>();

        String configFilePath = ExtCache.currentPath + File.separator + ExtCache.sltPath + Const.PROJECT_EXT_SOURCE_CONFIG_FILE_NAME;
        if(!FileUtil.exist(configFilePath) ){
            return extSourceFile;
        }
        JSONObject jsonObject = JSONUtil.parseObj(FileUtil.readUtf8String(configFilePath));
        for(GenEnum.projectType item : GenEnum.projectType.values()){
            List<SourceFile> files = JSONUtil.toList(jsonObject.get(item.name(),String.class), SourceFile.class);
            extSourceFile.put(item.name(), files);
        }
        return extSourceFile;
    }
}
