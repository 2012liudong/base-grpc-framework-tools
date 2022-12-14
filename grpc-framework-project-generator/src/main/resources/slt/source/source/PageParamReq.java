package ${package};

import ${commonModule}.constant.Constants;
import ${commonModule}.constant.enumeration.AppEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* extends PageParamReq*/
@Data
public class PageParamReq implements Serializable {

    private Integer pageNum = Constants.DEFAULT_PAGE_NUM;

    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;

    private String sortProperty;

    private String sortType = AppEnum.DataSort.DESC.getText();

    private Map<String, String> sorts = new HashMap<>();

}
