package com.zd.tools.project.generator.analysis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.model.Project;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Context {

    private final String rootPath;

    private final Map<String, String> original;

    private Project project;

    Set<String> errorList = new HashSet<>();

    public void addError(String msg, Object... params) {
        this.errorList.add(StrUtil.format(msg, params));
    }

    public void addError(Set<String> errors){
        this.errorList.addAll(errors);
    }

    public boolean hasError() {
        return CollUtil.isNotEmpty(errorList);
    }
}
