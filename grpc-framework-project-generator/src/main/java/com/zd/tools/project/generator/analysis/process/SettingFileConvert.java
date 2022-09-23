package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.consts.Keys;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class SettingFileConvert {
    private static final Log log = Log.get();

    public static Project buidlProject(Map<String, String> original){
        Project project = new Project();
        project.setName(original.get(Keys.KEY_PROJECT_NAME ));
        project.setGroupId(original.get(Keys.KEY_PROJECT_GROUP_ID ));
        project.setVersion(original.get(Keys.KEY_PROJECT_VERSION ));
        project.setBasePackage(original.get(Keys.KEY_PROJECT_BASE_PACKAGE ));
        project.setPackaging(original.get(Keys.KEY_PROJECT_PACKAGING ));
        return project;
    }

    public static List<AbstractModule> buildModules(Map<String, String> setting){
        String []moduleNames = setting.get( Keys.KEY_PROJECT_MODULES ).split(",");

        List<AbstractModule> moduleBos = new ArrayList<>();
        for(String moduleName: moduleNames){
            Function<Map<String, String>, AbstractModule> function = (original)->{
                String tempModuleName = StrUtil.trim(moduleName);
                String moduleType =original.get(tempModuleName + Keys.KEY_MODULE_TYPE);
                if(StrUtil.isEmpty(moduleType)){
                    return null;
                }
                GenEnum.projectType projectType = EnumUtil.getEnumMap(GenEnum.projectType.class).get( moduleType );
                AbstractModule result;
                switch (projectType){
                    case persistence:
                        result = BuildModuleUtil.buildModulePersistence(tempModuleName, original); break;
                    case restful:
                        result = BuildModuleUtil.buildModuleRestful(tempModuleName, original);     break;
                    case grpc:
                        result = BuildModuleUtil.buildModuleGrpc(tempModuleName, original);        break;
                    case fixed:
                        result = BuildModuleUtil.buildModuleFixed(tempModuleName, original);       break;
                    case proto:
                        result = BuildModuleUtil.buildModuleProto(tempModuleName, original);       break;
                    case application:
                        result = BuildModuleUtil.buildModuleApplication(tempModuleName, original); break;
                    case api:
                    case common:
                    default:
                        result = BuildModuleUtil.buildModuleCommon(tempModuleName, original);
                }
                return result;
            };

            AbstractModule moduleBo = function.apply(setting);
            if( moduleBo !=null ){
                moduleBos.add(function.apply(setting));
            }
        }

        return moduleBos;
    }
}
