package com.zd.myhbase;


import com.zd.myhbase.callback.RowMapper;

import java.util.Collection;
import java.util.List;

/**
 * @Title: com.shukun.data.common.entity.hbase.IHbaseService
 * @Description hbase存储dao基类
 * @author liudong
 *
 *      * @param tableName 表名：查当于数据库表
 *      * @param rowKey rowKey：每行数据的唯一标识，全局唯一
 *      * @param columnFamily 列族：相当于逻辑分组，经常需要同时使用的列应该放在同一个列族之中
 *      * @param column 列
 *      * @param value 值
 *
 * @date 2022-08-02 3:32 p.m.
 */
public interface IHbaseService<T>    {

    boolean save(T entity);

    void saveBatch(Collection<T> entityList, int batchSize);

    boolean saveOrUpdate(T entity);

    boolean remove(String rowKey);

    void removeBatch(List<String> keyLists);

    boolean update(T entity);

    T get(String rowKey);

    long getTimeStamp(String rowKey);

    List<T> list(String rowKey);

    List<T> listByKeys(List<String> keyLists);


    /**下面是一些高级用法*/
    T get(String rowName, String qualifier, RowMapper<T> mapper);

    List<T> find(String qualifier, RowMapper<T> mapper);

}
