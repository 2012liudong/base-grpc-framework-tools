package ${package};

import ${commonModule}.spring.advice.AbstractHttpExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice(value = {"${restfulModule}${fixedModule}"})
public class ${className} extends AbstractHttpExceptionAdvice {
}
