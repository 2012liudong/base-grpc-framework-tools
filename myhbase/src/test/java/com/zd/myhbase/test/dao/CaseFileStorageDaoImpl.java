package com.zd.myhbase.test.dao;

import com.zd.myhbase.HbaseServiceImpl;
import com.zd.myhbase.test.ICaseFileStorageDao;
import com.zd.myhbase.test.entity.HBaseCaseFileStorageInfoEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class CaseFileStorageDaoImpl extends HbaseServiceImpl<HBaseCaseFileStorageInfoEntity> implements ICaseFileStorageDao {

}
