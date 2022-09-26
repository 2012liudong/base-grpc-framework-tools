package com.zd.baseframework.core.grpc.interceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: com.zd.baseframework.core.grpc.interceptor.GlobalServerInterceptorConfiguration
 * @Description
 * @author liudong
 * @date 2022/1/13 4:40 PM
 */
@Slf4j
public class {className} extends AbstractGrpcAccessInterceptor {

    @Autowired
    private ApperProperties apperProperties;

    @Override
    protected String tokenKey() {
        return apperProperties.getTokenKey();
    }

}
