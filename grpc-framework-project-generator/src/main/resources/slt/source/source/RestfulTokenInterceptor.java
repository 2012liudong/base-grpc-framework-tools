package ${package};

import cn.hutool.core.util.StrUtil;
import ${commonModule}.constant.enumeration.AppEnum;
import ${commonModule}.entity.restful.LoginUser;
import ${commonModule}.spring.restful.AbstractTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ${className} extends AbstractTokenInterceptor {

    @Override
    protected LoginUser loginUser() {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(StrUtil.toString(AppEnum.DefaultSystemUser.SYSTEM.getCode()));
        loginUser.setUserName(AppEnum.DefaultSystemUser.SYSTEM.getText());
        return loginUser;
    }
}
