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

}
