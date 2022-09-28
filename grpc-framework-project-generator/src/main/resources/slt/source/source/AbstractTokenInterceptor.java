package ${package};

import cn.hutool.core.util.StrUtil;
import ${commonModule}.constant.Constants;
import ${commonModule}.constant.ResponseConst;
import ${commonModule}.entity.restful.LoginUser;
import ${commonModule}.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: ${package}.AbstractTokenInterceptor
 * @Description TokenInterceptor
 *  -- extends AbstractTokenInterceptor
 *  -- @Component
 *
 * @author liudong
 * @date 2022-09-17 12:15 a.m.
 */
@Slf4j
public abstract class AbstractTokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = MDC.get(Constants.TOKEN);

        try{
            if(StrUtil.isEmpty(token)){
                log.error("Token is null, no login user");
                throw new AppException(ResponseConst.Msg.INVALID_USER);
            }

            LoginUser loginUser = loginUser();
            MDC.put(Constants.USER_PARAM_ID, loginUser.getId());
            MDC.put(Constants.USER_PARAM_USERNAME, loginUser.getUserName());
            return true;
        } catch (AppException e) {
            log.error("Token is invalid, login user isn't exsit..{"+MDC.get(Constants.TOKEN)+"}");
            throw new AppException(ResponseConst.Msg.INVALID_USER);
        }
    }

    protected abstract LoginUser loginUser();

}


/*@Slf4j
@Component
public class InterceptorRegister implements WebMvcConfigurer {

    @Autowired
    private AccessInterceptor accessInterceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private ApperProperties apperProperties;

    *//*下面拦截器accessInterceptor必须放在第一位*//*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns(apperProperties.getHttpApiPath());
        registry.addInterceptor(tokenInterceptor).addPathPatterns(apperProperties.getHttpApiPath());
    }
}*/
