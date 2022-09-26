package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;

import java.util.Map;

public final class StrFormatterUtil extends StrFormatter {

    public static String format(CharSequence template, Map<?, ?> map, boolean ignoreNull) {
        if (null == template) {
            return null;
        }
        if (null == map || map.isEmpty()) {
            return template.toString();
        }

        String template2 = template.toString();
        String value;
        String key;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            value = StrUtil.utf8Str(entry.getValue());
            key = (String) entry.getKey();
            if (null == value && ignoreNull) {
                continue;
            }
            String [] k = key.split("\\.");
            if(CollUtil.size(k)>1){
                key = k[1];
            }
            template2 = StrUtil.replace(template2, "${" + key + "}", value);
        }
        return template2;
    }

    public static String[] strSplitDot(String str){
        String[] result = {};
        if(StrUtil.isNotEmpty(str)){
            result = str.split("\\.");
        }
        return result;
    }
}
