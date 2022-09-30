package com.zd.myhbase.test.entity;

import com.zd.myhbase.annotation.HBaseFieldName;
import com.zd.myhbase.annotation.HBaseTableName;
import lombok.Data;

/**
 * @Title: com.shukun.data.persistence.hbase.casefile.entity.HBaseCaseFileStorageInfoEntity
 * @Description
 *             put(" sh ", toBytes ( " sh_dicom "));
 *             put("bj", toBytes("bj_dicom"));
 * @author liudong
 * @date 2022-08-05 5:27 p.m.
 */
@Data
@HBaseTableName(value = "case_dicom", family = "bj_dicom")
public class HBaseCaseFileStorageInfoEntity {
    private String rowkey;

    @HBaseFieldName("caseId")
    private String caseId;

    private String fid;

    private String name;

    private String url;

    @HBaseFieldName("jpgUrl")
    private String jpgUrl;

    @HBaseFieldName("publicUrl")
    private String publicUrl;

    private String path;

    private String loc;

    private Integer order;

    private Long timestamp;
}
