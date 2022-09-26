package com.zd.tools.project.generator.consts;

public interface GenEnum {

//    GenEnum getEnum(String code);

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
//        @Override
//        public GenEnum getEnum(String code) {
//            return EnumUtil.getEnumMap(GenEnum.projectType.class).get(code);
//        }
    }

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
