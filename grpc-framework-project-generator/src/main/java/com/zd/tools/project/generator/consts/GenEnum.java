package com.zd.tools.project.generator.consts;

import cn.hutool.core.util.EnumUtil;

public interface GenEnum {

    GenEnum getEnum(String code);

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

        @Override
        public GenEnum getEnum(String code) {
            return EnumUtil.getEnumMap(GenEnum.projectType.class).get(code);
        }
    }

}
