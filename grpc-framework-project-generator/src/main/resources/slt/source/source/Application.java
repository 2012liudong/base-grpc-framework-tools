package ${package};

/**
 * @Title: com.zd.baseframework.BaseFrameworkApplication
 * @Description Springboot MainClass，suggest to use a config database system, such as nacos.
 * @author liudong
 * @date 2022/6/13 10:41 PM
 */
@SpringBootApplication(scanBasePackages = {"${restfulModule}${fixedModule}${grpcModule}", "cn.hutool.extra.spring"})
@MapperScan("${restfulModule}${fixedModule}${grpcModule}")
@EnableConfigurationProperties
public class ${className} {

    public static void main(String []args){
        TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
        SpringApplication.run(${className}.class, args);
    }
}