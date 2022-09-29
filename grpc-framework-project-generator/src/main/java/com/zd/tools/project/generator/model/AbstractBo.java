package com.zd.tools.project.generator.model;

import com.zd.tools.project.generator.model.file.SourceFile;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public abstract class AbstractBo {

    /** 模块的磁盘根目录
       /target/classes/output/base-grpc-framework/
       /target/classes/output/base-grpc-framework/base-grpc-framework-app
     */
    private String basePath;

    /**名称
    * base-grpc-framework || api
    * */
    private String name;

    private List<String> dirs = new ArrayList<>();

    private List<SourceFile> sourceFiles = new ArrayList<>();

}
