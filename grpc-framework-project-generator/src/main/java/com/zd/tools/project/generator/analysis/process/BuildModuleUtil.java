package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.consts.Keys;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.module.*;
import com.zd.tools.project.generator.model.module.model.ModulePropertyBo;

import java.util.Map;

public class BuildModuleUtil {

    /*api*/
    public static AbstractModule buildModuleApi(String moduleName, Map<String, String> original){
        ModuleCommon moduleBo = new ModuleCommon();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.api );

        ModuleBuildTool.buildModuleCommonProperty(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleProto(String moduleName, Map<String, String> original){
        ModuleProto moduleBo = new ModuleProto();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.proto );

        ModuleBuildTool.buildModuleCommonProperty(moduleBo, original);
        return moduleBo;
    }

    /*app*/
    public static AbstractModule buildModuleApplication(String moduleName, Map<String, String> original){
        ModuleApplication moduleBo = new ModuleApplication();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.application );

        ModuleBuildTool.buildModuleCommonProperty(moduleBo, original);
        return moduleBo;
    }

    /*server*/
    public static AbstractModule buildModuleRestful(String moduleName, Map<String, String> original){
        ModuleRestful moduleBo = new ModuleRestful();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.restful );

        moduleBo.setPort( original.get(moduleName + Keys.KEY_MODULE_PORT ) );
        ModuleBuildTool.buildModuleSpecialProperty(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleGrpc(String moduleName, Map<String, String> original){
        ModuleGrpc moduleBo = new ModuleGrpc();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.grpc );

        moduleBo.setGrpcPort( original.get(moduleName + Keys.KEY_MODULE_GRPC_PORT ) );
        ModuleBuildTool.buildModuleSpecialProperty(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleFixed(String moduleName, Map<String, String> original){
        ModuleFixed moduleBo = new ModuleFixed();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.fixed );

        moduleBo.setPort( original.get(moduleName + Keys.KEY_MODULE_PORT ) );
        moduleBo.setGrpcPort( original.get(moduleName + Keys.KEY_MODULE_GRPC_PORT ) );
        ModuleBuildTool.buildModuleSpecialProperty(moduleBo, original);
        return moduleBo;
    }

    /*persistence*/
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

        ModuleBuildTool.buildModuleSpecialProperty(moduleBo, original);
        return moduleBo;
    }

    /*common*/
    public static AbstractModule buildModuleCommon(String moduleName, Map<String, String> original){
        ModuleCommon moduleBo = new ModuleCommon();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.common );

        ModuleBuildTool.buildModuleSpecialProperty(moduleBo, original);
        return moduleBo;
    }

     static class ModuleBuildTool{
         protected static ModulePropertyBo buildModuleProperty(String moduleName, Map<String, String> original){
            ModulePropertyBo modulePropertyBo = new ModulePropertyBo();
            modulePropertyBo.setTokenKey( original.get(moduleName + Keys.KEY_PROJECT_PROPERTY_TOKEN ) );
            modulePropertyBo.setApiPath( original.get(moduleName + Keys.KEY_PROJECT_PROPERTY_APIPATH ) );
            return modulePropertyBo;
        }

         protected static void buildModuleSpecialProperty(AbstractModule moduleBo, Map<String, String> original){
             moduleBo.setWrapBy( original.get(moduleBo.getName() + Keys.KEY_MODULE_WRAPBY ) );
             buildModuleCommonProperty(moduleBo, original);
         }

         protected static void buildModuleCommonProperty(AbstractModule moduleBo, Map<String, String> original){
             moduleBo.setArtifactId( original.get(moduleBo.getName() + Keys.KEY_MODULE_ARTIFACT_ID ) );
             String packaging = original.get(moduleBo.getName() + Keys.KEY_MODULE_PACKAGING );
             if(StrUtil.isEmpty(packaging)){
                 packaging = original.get(Keys.KEY_PROJECT_PACKAGING);
             }
             moduleBo.setPackaging( packaging );

             moduleBo.setBasePath( original.get(Keys.KEY_PROJECT_NAME ) );
             moduleBo.setName( moduleBo.getName() );
         }
    }
}
