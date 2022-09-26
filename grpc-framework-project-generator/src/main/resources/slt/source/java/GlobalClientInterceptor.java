package com.zd.baseframework.core.grpc.interceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class {className} extends AbstractGrpcClientInterceptor {

    @Autowired
    private ApperProperties apperProperties;

    @Override
    protected String tokenKey() {
        return apperProperties.getTokenKey();
    }

}
