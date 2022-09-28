package ${package};

import ${commonModule}.constant.Constants;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ${package}.PageQueryBo
 * @Description for query vo convert bo
 * @author liudong
 * @date 2022-09-20 2:59 p.m.
 */
@Data
public class PageQueryBo {

    private Integer pageNum = Constants.DEFAULT_PAGE_NUM;

    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;

    private String sortProperty;

    private String sortType;

    private Map<String, String> sort = new HashMap<>();
}
