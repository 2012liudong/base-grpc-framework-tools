package ${package};

import ${commonModule}.spring.ApperProperties;
import ${commonModule}.spring.grpc.AbstractGrpcClientInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ${className} extends AbstractGrpcClientInterceptor {

    @Autowired
    private ApperProperties apperProperties;

    @Override
    protected String tokenKey() {
        return apperProperties.getTokenKey();
    }

}
