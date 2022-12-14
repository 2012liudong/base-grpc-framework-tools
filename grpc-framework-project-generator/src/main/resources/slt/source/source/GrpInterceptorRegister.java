package ${package};

import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import ${commonModule}.spring.grpc.AbstractGrpcAccessInterceptor;
import ${commonModule}.spring.grpc.AbstractGrpcTokenInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Title: ${package}.${className}
 * @Description execute order depend on the code position of GloablInterceptorRegister
 * @author liudong
 * @date 2022-09-19 3:39 p.m.
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
public class ${className} {

    @GrpcGlobalServerInterceptor
    @Order(value = 10000)
    AbstractGrpcAccessInterceptor grpcAccessInterceptor() {
        return new ${grpcAccessIntereptor}();
    }

    @GrpcGlobalServerInterceptor
    @Order(value = 20000)
    AbstractGrpcTokenInterceptor grpcTokenInterceptor() {
        return new ${grpcTokenIntereptor}();
    }
}

