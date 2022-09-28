package ${package};

import ${commonModule}.spring.ApperProperties;
import ${commonModule}.spring.restful.AbstractAccessInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ${className} extends AbstractAccessInterceptor {

    @Autowired
    private ApperProperties apperProperties;

    @Override
    protected String tokenKey() {
        return apperProperties.getTokenKey();
    }
}
