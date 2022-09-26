package com.zd.baseframework.core.restful.interceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class {className} extends AbstractTokenInterceptor {

    @Override
    protected LoginUser loginUser() {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(StrUtil.toString(AppEnum.DefaultSystemUser.SYSTEM.getCode()));
        loginUser.setUserName(AppEnum.DefaultSystemUser.SYSTEM.getText());
        return loginUser;
    }
}
