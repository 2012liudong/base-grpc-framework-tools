package com.zd.myhbase.annotation;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class HBTableInfo {

    private String tableName;

    private String family;

    private String rowkey;

    private Map<String, String> fieldMapping = new HashMap<>();
}
