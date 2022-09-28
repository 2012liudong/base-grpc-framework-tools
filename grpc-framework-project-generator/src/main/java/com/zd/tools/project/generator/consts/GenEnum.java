package com.zd.tools.project.generator.consts;

public interface GenEnum {

    enum projectType implements GenEnum{
        proto,
        api,
        application,
        persistence,
        common,
        restful,
        grpc,
        fixed,
        ;
    }

    //源文件目录名
    enum fileType implements GenEnum{
        source,
        yml,
        xml,
        config,
        ;
    }

    enum fileOperatorType implements GenEnum{
        copy,
        create,
        append,
        ;
    }

}
