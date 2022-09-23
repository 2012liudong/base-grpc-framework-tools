package com.zd.tools.project.generator.analysis.process;

import com.zd.tools.project.generator.consts.GenEnum;
import com.zd.tools.project.generator.consts.Keys;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Build;
import com.zd.tools.project.generator.model.Logging;
import com.zd.tools.project.generator.model.module.*;
import com.zd.tools.project.generator.model.module.model.ModulePropertyBo;

import java.util.Map;

public class BuildModuleUtil {

    public static AbstractModule buildModuleGrpc(String moduleName, Map<String, String> original){
        ModuleGrpc moduleBo = new ModuleGrpc();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.grpc );

        moduleBo.setGrpcPort( original.get(moduleName + Keys.KEY_MODULE_GRPC_PORT ) );
        moduleBo.setModulePropertyBo( ModuleBuildTool.buildModuleProperty(moduleName, original) );

        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModulePersistence(String moduleName, Map<String, String> original){
        ModulePersistence moduleBo = new ModulePersistence();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.persistence );

        moduleBo.setMapper( original.get(moduleName + Keys.KEY_MODULE_MAPPER ) );
        moduleBo.setIp( original.get(moduleName + Keys.KEY_MODULE_MYSQL_IP ) );
        moduleBo.setDbPort( original.get(moduleName + Keys.KEY_MODULE_MYSQL_PORT ) );
        moduleBo.setUsername( original.get(moduleName + Keys.KEY_MODULE_MYSQL_USERNAME ) );
        moduleBo.setPassword( original.get(moduleName + Keys.KEY_MODULE_MYSQL_PASSWORD ) );

        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    public static AbstractModule buildModuleRestful(String moduleName, Map<String, String> original){
        ModuleRestful moduleBo = new ModuleRestful();
        moduleBo.setName(moduleName);
        moduleBo.setType( GenEnum.projectType.restful );

        moduleBo.setPort( original.get(moduleName + Keys.KEY_MODULE_PORT ) );
        moduleBo.setModulePropertyBo( ModuleBuildTool.buildModuleProperty(moduleName, original) );

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
        moduleBo.setModulePropertyBo( ModuleBuildTool.buildModuleProperty(moduleName, original) );

        buildModuleCommPropertySpecial(moduleBo, original);
        return moduleBo;
    }

    private static void buildModuleCommPropertySpecial(AbstractModule moduleBo, Map<String, String> original){
        moduleBo.setWrapBy( original.get(moduleBo.getName() + Keys.KEY_MODULE_WRAPBY ) );
        buildModuleCommProperty(moduleBo, original);
    }

    private static void buildModuleCommProperty(AbstractModule moduleBo, Map<String, String> original){
        moduleBo.setArtifactId( original.get(moduleBo.getName() + Keys.KEY_MODULE_ARTIFACT_ID ) );
        moduleBo.setPackaging( original.get(moduleBo.getName() + Keys.KEY_MODULE_PACKAGING ) );
        moduleBo.setConfig( original.get(moduleBo.getName() + Keys.KEY_MODULE_CONFIG ) );
        moduleBo.setLogging( ModuleBuildTool.buildModuleLogging(moduleBo.getName(), original) );
        moduleBo.setBuild( ModuleBuildTool.buildModuleBuilding(moduleBo.getName(), original) );

        moduleBo.setBasePath( original.get(Keys.KEY_PROJECT_NAME ) );
        moduleBo.setName( moduleBo.getName() );
    }


     static class ModuleBuildTool{
         protected static ModulePropertyBo buildModuleProperty(String moduleName, Map<String, String> original){
            ModulePropertyBo modulePropertyBo = new ModulePropertyBo();
            modulePropertyBo.setTokenKey( original.get(moduleName + Keys.KEY_MODULE_PROPERTY_TOKEN ) );
            modulePropertyBo.setApiPath( original.get(moduleName + Keys.KEY_MODULE_PROPERTY_APIPATH ) );
            return modulePropertyBo;
        }

         protected static Logging buildModuleLogging(String moduleName, Map<String, String> original){
            Logging logging = new Logging();
            logging.setStorageTerm( original.get(moduleName + Keys.KEY_MODULE_LOG_STORAGE_TERM ) );
            return logging;
        }

         protected static  Build buildModuleBuilding(String moduleName, Map<String, String> original){
            Build build = new Build();
            build.setName( original.get(moduleName + Keys.KEY_MODULE_BUILD ) );
            return build;
        }
    }
}
