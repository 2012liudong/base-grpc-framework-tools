package com.zd.myhbase.util;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import com.zd.myhbase.annotation.HBTableInfo;
import com.zd.myhbase.annotation.HBaseFieldName;
import com.zd.myhbase.annotation.HBaseRowKey;
import com.zd.myhbase.annotation.HBaseTableName;
import com.zd.myhbase.callback.MutatorCallback;
import com.zd.myhbase.callback.TableCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class HbaseHelper {

    private static final Map<Class, HBTableInfo> TABLE_INFO_CACHE = new HashMap<>();

    private static final String DEFAULT_ROWKEY_NAME = "rowkey";

    private static final String ANNOCATION_TABLENAME_NAME = "value";

    private static final String ANNOCATION_TABLENAME_FAMILY = "family";

    /**
    *  把实体类的信息按class解析后放在缓存中
    * 〈功能详细描述〉
    * @param clazz Object.class
    * @return [返回类型说明]
    */
    public static HBTableInfo putTableInfo(Class clazz) {
        HBTableInfo hbTableInfo = new HBTableInfo();
        String tableName = (String) AnnotationUtil.getAnnotationValue(clazz, HBaseTableName.class, ANNOCATION_TABLENAME_NAME);
        String family = (String) AnnotationUtil.getAnnotationValue(clazz, HBaseTableName.class, ANNOCATION_TABLENAME_FAMILY);
        hbTableInfo.setTableName(tableName);
        hbTableInfo.setFamily(family);

        Field[] fileds = ReflectUtil.getFields(clazz);
        for(Field item : fileds){
            String filedName = item.getName();
            HBaseFieldName hBaseFieldName = item.getAnnotation(HBaseFieldName.class);
            if(hBaseFieldName!=null){
                hbTableInfo.getFieldMapping().put(filedName, hBaseFieldName.value());
            }else{
                hbTableInfo.getFieldMapping().put(filedName, filedName);
            }

            HBaseRowKey rowKey = item.getAnnotation(HBaseRowKey.class);
            if(rowKey!=null){
                hbTableInfo.setRowkey(rowKey.value());
            }
        }
        if (StrUtil.isEmpty(hbTableInfo.getRowkey())) {
            hbTableInfo.setRowkey(DEFAULT_ROWKEY_NAME);
        }
        return hbTableInfo;
    }

    public static HBTableInfo getTableInfo(Class clazz){
        HBTableInfo hbTableInfo =  TABLE_INFO_CACHE.get(clazz);
        if( hbTableInfo == null){
            hbTableInfo = HbaseHelper.putTableInfo(clazz);
            TABLE_INFO_CACHE.put(clazz, hbTableInfo);
        }
        return hbTableInfo;
    }

  /**
  * 从实体中返回指定属性的值
  * @param
  * @return
  */
    public static <T> byte[] getFieldValue(T entity, String propertyName){
        Object obj = ReflectUtil.getFieldValue(entity, propertyName);
        return HbaseHelper.toBytes(StrUtil.toString(obj));
    }

    public static byte[] toBytes(String value) {
        if (value == null) {
            return null;
        }
        return Bytes.toBytes(value);
    }

    /**
     * 判断是否存在HBASE表
     * @param
     * @return
     */
    public boolean isTableExsit(Connection connection, String tableName) {
        boolean tableExists = false;
        try {
            TableName table = TableName.valueOf(tableName);
            tableExists = connection.getAdmin().tableExists(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tableExists;
    }

    public static<T> T newInstance(Class<T> clazz, byte[] family,Result result){
        T t = ReflectUtil.newInstance(clazz);
        HBTableInfo hbTableInfo = HbaseHelper.getTableInfo(clazz);
        for (Map.Entry<String, String> entry : hbTableInfo.getFieldMapping().entrySet()) {
            if(StrUtil.equals(hbTableInfo.getRowkey(), entry.getKey())){
                ReflectUtil.setFieldValue(t, entry.getKey(),  Bytes.toString(result.getRow()));
            }else{
                ReflectUtil.setFieldValue(t, entry.getKey(),  Bytes.toString(result.getValue(family, HbaseHelper.toBytes(entry.getKey()))));
            }
        }
        return t;
    }

    /**
    * 通用操作，固定线程池
    * @param
    * @return
    */
    public static  <T> T execute(Connection connection, String tableName, TableCallback<T> mapper) {

        StopWatch sw = new StopWatch();
        sw.start();
        Table table = null;
        try {
            table = connection.getTable(TableName.valueOf(tableName));
            return mapper.doInTable(table);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            if (null != table) {
                try {
                    table.close();
                    sw.stop();
                } catch (IOException e) {
                    log.error("create HBbase resource fail", e.getMessage());
                }
            }
        }
    }

    /**
    * 通用操作，单独固定线程池
    * @param
    * @return
    */
    public static boolean execute(Connection connection, ThreadPoolExecutor poolExecutor, String tableName, MutatorCallback action) {

        StopWatch sw = new StopWatch();
        sw.start();
        BufferedMutator mutator = null;
        try {
            BufferedMutatorParams mutatorParams = new BufferedMutatorParams(TableName.valueOf(tableName));
            mutator = connection.getBufferedMutator(mutatorParams.writeBufferSize(3 * 1024 * 1024).pool(poolExecutor));
            action.doInMutator(mutator);
            return true;
        } catch (Throwable throwable) {
            sw.stop();
            throw new RuntimeException(throwable);
        } finally {
            if (null != mutator) {
                try {
                    mutator.flush();
                    mutator.close();
                    sw.stop();
                } catch (IOException e) {
                    log.error("create HBbase resource fail", e.getMessage());
                }
            }
        }
    }


}
