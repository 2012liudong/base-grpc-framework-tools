package ${package};

import lombok.Data;

import java.io.Serializable;

@Data
public class EnumVo implements Serializable {

    private Integer code;

    private String text;
}
