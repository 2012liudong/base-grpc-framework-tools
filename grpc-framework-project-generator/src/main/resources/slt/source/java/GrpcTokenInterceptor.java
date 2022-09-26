package com.zd.baseframework.core.grpc.interceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class {className} extends AbstractGrpcTokenInterceptor {
    @Override
    protected LoginUser loginUser() {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(StrUtil.toString(AppEnum.DefaultSystemUser.SYSTEM.getCode()));
        loginUser.setUserName(AppEnum.DefaultSystemUser.SYSTEM.getText());
        return loginUser;
    }
}
