package ${package};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;
/**
 * @Title: ${package}.${className}
 * @Description Springboot MainClass，suggest to use a config database system, such as nacos.
 * @author liudong
 * @date 2022/6/13 10:41 PM
 */
@SpringBootApplication(scanBasePackages = {"${restfulModule}${fixedModule}${grpcModule}","${commonModule}", "cn.hutool.extra.spring"})
@MapperScan("${persistenceModule}")
@EnableConfigurationProperties
public class ${className} {

    public static void main(String []args){
        TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
        SpringApplication.run(${className}.class, args);
    }
}