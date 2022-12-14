package ${package};

import ${commonModule}.spring.ApperProperties;
import ${commonModule}.spring.restful.AbstractAccessInterceptor;
import ${commonModule}.spring.restful.AbstractTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Component
public class ${className} implements WebMvcConfigurer {

    @Autowired
    private AbstractAccessInterceptor accessInterceptor;

    @Autowired
    private AbstractTokenInterceptor tokenInterceptor;

    @Autowired
    private ApperProperties apperProperties;

    /*Be sure put the AccessInterceptor to first*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns(apperProperties.getHttpApiPath());
        registry.addInterceptor(tokenInterceptor).addPathPatterns(apperProperties.getHttpApiPath());
    }
}
