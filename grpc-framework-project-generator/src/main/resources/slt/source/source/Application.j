package ${package};

/**
 * @Title: com.zd.baseframework.BaseFrameworkApplication
 * @Description Springboot MainClass，suggest to use a config database system, such as nacos.
 * @author liudong
 * @date 2022/6/13 10:41 PM
 */
@SpringBootApplication(scanBasePackages = {"${package}", "cn.hutool.extra.spring"})
@MapperScan("${package}")
@EnableConfigurationProperties
public class ${className} {

    public static void main(String []args){
        TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
        SpringApplication.run({className}.class, args);
    }
}