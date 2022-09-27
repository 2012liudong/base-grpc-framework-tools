package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.consts.Keys;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.module.*;
import com.zd.tools.project.generator.model.module.model.ModulePropertyBo;

import java.util.Map;

public class BuildModuleUtil {

    public static AbstractModule buildModuleGrpc(String moduleName, Map<String, String> original){
        ModuleGrpc moduleBo = new ModuleGrpc();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.grpc );

        moduleBo.setGrpcPort( original.get(moduleName + Keys.KEY_MODULE_GRPC_PORT ) );

        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModulePersistence(String moduleName, Map<String, String> original){
        ModulePersistence moduleBo = new ModulePersistence();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.persistence );

        moduleBo.setMapper( original.get(moduleName + Keys.KEY_MODULE_MAPPER ) );
        moduleBo.setDbIp( original.get(moduleName + Keys.KEY_MODULE_MYSQL_IP ) );
        moduleBo.setDbPort( original.get(moduleName + Keys.KEY_MODULE_MYSQL_PORT ) );
        moduleBo.setDbName( original.get(moduleName + Keys.KEY_MODULE_MYSQL_NAME ) );
        moduleBo.setDbUsername( original.get(moduleName + Keys.KEY_MODULE_MYSQL_USERNAME ) );
        moduleBo.setDbPassword( original.get(moduleName + Keys.KEY_MODULE_MYSQL_PASSWORD ) );

        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleRestful(String moduleName, Map<String, String> original){
        ModuleRestful moduleBo = new ModuleRestful();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.restful );

        moduleBo.setPort( original.get(moduleName + Keys.KEY_MODULE_PORT ) );
        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleCommon(String moduleName, Map<String, String> original){
        ModuleCommon moduleBo = new ModuleCommon();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.common );
        moduleBo.setWrapBy( original.get(moduleName + Keys.KEY_MODULE_WRAPBY ) );

        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleApi(String moduleName, Map<String, String> original){
        ModuleCommon moduleBo = new ModuleCommon();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.api );
        moduleBo.setWrapBy( original.get(moduleName + Keys.KEY_MODULE_WRAPBY ) );

        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleApplication(String moduleName, Map<String, String> original){
        ModuleApplication moduleBo = new ModuleApplication();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.application );

        buildModuleCommProperty(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleProto(String moduleName, Map<String, String> original){
        ModuleProto moduleBo = new ModuleProto();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.proto );

        buildModuleCommProperty(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleFixed(String moduleName, Map<String, String> original){
        ModuleFixed moduleBo = new ModuleFixed();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.fixed );

        moduleBo.setPort( original.get(moduleName + Keys.KEY_MODULE_PORT ) );
        moduleBo.setGrpcPort( original.get(moduleName + Keys.KEY_MODULE_GRPC_PORT ) );
        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    private static void buildModuleCommPropertySpecial(AbstractModule moduleBo, Map<String, String> original){
        moduleBo.setWrapBy( original.get(moduleBo.getName() + Keys.KEY_MODULE_WRAPBY ) );
        buildModuleCommProperty(moduleBo, original);
    }

    private static void buildModuleCommProperty(AbstractModule moduleBo, Map<String, String> original){
        moduleBo.setArtifactId( original.get(moduleBo.getName() + Keys.KEY_MODULE_ARTIFACT_ID ) );
        String packaging = original.get(moduleBo.getName() + Keys.KEY_MODULE_PACKAGING );
        if(StrUtil.isEmpty(packaging)){
            packaging = original.get(Keys.KEY_PROJECT_PACKAGING);
        }
        moduleBo.setPackaging( packaging );

        moduleBo.setBasePath( original.get(Keys.KEY_PROJECT_NAME ) );
        moduleBo.setName( moduleBo.getName() );

    }


     static class ModuleBuildTool{
         protected static ModulePropertyBo buildProperty(String moduleName, Map<String, String> original){
            ModulePropertyBo modulePropertyBo = new ModulePropertyBo();
            modulePropertyBo.setTokenKey( original.get(moduleName + Keys.KEY_PROJECT_PROPERTY_TOKEN ) );
            modulePropertyBo.setApiPath( original.get(moduleName + Keys.KEY_PROJECT_PROPERTY_APIPATH ) );
            return modulePropertyBo;
        }
    }
}
