package com.zd.tools.project.generator.analysis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.model.Project;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Context {

    private final String rootPath;

    private final Map<String, String> original;

    private Project project;

    List<String> errorList = new ArrayList<>();

    public void addError(String msg, Object... params) {
        this.errorList.add("Error:" + StrUtil.format(msg, params));
    }

    public boolean hasError() {
        return CollUtil.isNotEmpty(errorList);
    }
}
