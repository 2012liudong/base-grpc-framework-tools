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

    //当前jar包所在的目录
    private final String rootPath;

    //setting文件的内容
    private final Map<String, String> original;

    // 当前项目的信息
    private Project project;

    // springboot配置文件所在的路径
    private String springBootResource;

    Set<String> errorList;

    public Context(String rootPath, Map<String, String> original) {
        this.rootPath = rootPath;
        this.original = original;
        errorList = new HashSet<>();
    }

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
