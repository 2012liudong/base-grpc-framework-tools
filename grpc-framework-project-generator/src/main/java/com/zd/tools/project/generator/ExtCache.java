package com.zd.tools.project.generator;

import com.zd.tools.project.generator.consts.Const;
import com.zd.tools.project.generator.model.file.SourceFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtCache {

    public static String currentPath;

    public static String sltPath = Const.SOURCE_ROOT;

    public static String projectSettingFile = Const.PROJECT_SETTING_FILE_NAME;

    public static Map<String, List<SourceFile>> extSourceFile = new HashMap<>();
}
