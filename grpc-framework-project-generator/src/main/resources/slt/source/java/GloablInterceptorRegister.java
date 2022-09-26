package com.zd.baseframework.core.grpc.interceptor;

/**
 * @Title: com.zd.baseframework.core.grpc.interceptor.GloablInterceptorRegister
 * @Description execute order depend on the code position of GloablInterceptorRegister
 * @author liudong
 * @date 2022-09-19 3:39 p.m.
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
public class {className} {

    @GrpcGlobalServerInterceptor
    @Order(value = 10000)
    GlobalServerInterceptor grpcAccessInterceptor() {
        return new GlobalServerInterceptor();
    }

    @GrpcGlobalServerInterceptor
    @Order(value = 20000)
    GrpcTokenInterceptor grpcTokenInterceptor() {
        return new GrpcTokenInterceptor();
    }

    @GrpcGlobalClientInterceptor
    @Order(value = 30000)
    GlobalClientInterceptor cClientInterceptor() {
        return new GlobalClientInterceptor();
    }
}
