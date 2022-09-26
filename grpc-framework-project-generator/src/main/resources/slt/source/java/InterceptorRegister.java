package com.zd.baseframework.core.restful.interceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class {className} implements WebMvcConfigurer {

    @Autowired
    private AccessInterceptor accessInterceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private ApperProperties apperProperties;

    /*Be sure put the AccessInterceptor to first*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns(apperProperties.getHttpApiPath());
        registry.addInterceptor(tokenInterceptor).addPathPatterns(apperProperties.getHttpApiPath());
    }
}
