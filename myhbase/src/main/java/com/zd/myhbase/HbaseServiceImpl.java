package com.zd.myhbase;

//import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.zd.myhbase.annotation.HBTableInfo;
import com.zd.myhbase.callback.MutatorCallback;
import com.zd.myhbase.callback.RowMapper;
import com.zd.myhbase.callback.TableCallback;
import com.zd.myhbase.util.HbaseHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Data
public class HbaseServiceImpl<T>  implements IHbaseService<T> {

    @Autowired
    private Connection connection;

    @Autowired
    private ThreadPoolExecutor poolExecutor;

    private Class<T> entityClass = currentModelClass();

    /**
    * 保存数据
    * @param
    * @return
    */
    @Override
    public boolean save(T entity) {
        return saveOrUpdate(entity);
    }

    @Override
    public void saveBatch(Collection<T> entityList, int batchSize) {
        for(T entity: entityList){
            save(entity);
        }
    }

    @Override
    public boolean update(T entity) {
        return saveOrUpdate(entity);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family = HbaseHelper.getFieldValue(entity, info.getFamily());

        Put action = new Put(HbaseHelper.getFieldValue(entity, info.getRowkey()));

        for (Map.Entry<String, String> entry : info.getFieldMapping().entrySet()) {
            action.addColumn(family, HbaseHelper.toBytes(entry.getKey()), HbaseHelper.getFieldValue(entity, entry.getValue()));
        }

        HbaseHelper.execute(connection, poolExecutor, info.getTableName(), new MutatorCallback() {
            @Override
            public void doInMutator(BufferedMutator mutator) throws Throwable {
                mutator.mutate(action);
            }
        });
        return true;
    }

    /**
     * 删除数据（根据rowkey）
     * @param
     * @return
     */
    @Override
    public boolean remove(String rowKey) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family =  HbaseHelper.toBytes(rowKey);

        Delete action = new Delete(HbaseHelper.toBytes(rowKey));
        action.addFamily(family);

        HbaseHelper.execute(connection, poolExecutor, info.getTableName(), new MutatorCallback() {
            @Override
            public void doInMutator(BufferedMutator mutator) throws Throwable {
                mutator.mutate(action);
            }
        });
        return true;
    }

    @Override
    public void removeBatch(List<String> idList) {
        for(String entity: idList){
            remove(entity);
        }
    }

    /**
    * //这里的第三个参数是反序列化的一个回调模式
    * @param
    * @return
    */
    @Override
    public T get(String rowKey) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family =  HbaseHelper.toBytes(info.getFamily());
        return this.get(rowKey, null, (result, rowNum) ->{
            return HbaseHelper.newInstance(entityClass,family, result);
        });
    }

    @Override
    public long getTimeStamp(String rowKey) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family =  HbaseHelper.toBytes(info.getFamily());

        Scan scan = new Scan();
        scan.setRowPrefixFilter(rowKey.getBytes());
        scan.addFamily(family);

        return HbaseHelper.execute(connection, info.getTableName(), new TableCallback<Long>() {
            @Override
            public Long doInTable(Table table) throws Throwable {
                int caching = scan.getCaching();
                // 如果caching未设置(默认是1)，将默认配置成5000
                if (caching == 1) {
                    scan.setCaching(5000);
                }
                ResultScanner scanner = table.getScanner(scan);
                try {
                    Long maxTimeStamp = 0L;
                    for (Result result : scanner) {
                        for (Cell cell : result.rawCells()) {
                            Long timeStamp = cell.getTimestamp();
                            if (timeStamp != null && maxTimeStamp < timeStamp) {
                                maxTimeStamp = timeStamp;
                            }
                        }
                    }
                    return maxTimeStamp;
                } finally {
                    scanner.close();
                }
            }
        });
    }

    @Override
    public List<T> list(String rowKey) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family =  HbaseHelper.toBytes(info.getFamily());
        return find(rowKey, (result, rowNum) ->{
            return HbaseHelper.newInstance(entityClass,family, result);
        });
    }

    @Override
    public List<T> listByKeys(List<String> idList) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family =  HbaseHelper.toBytes(info.getFamily());
        List<T> result1 = new ArrayList<>();
        for(String entity: idList){
            T t = (T) find(entity, (result, rowNum) ->{
                return HbaseHelper.newInstance(entityClass,family, result);
            });
            result1.add(t);
        }
        return  result1;
    }


    @Override
    public List<T> find(String rowkeyPrefix, RowMapper<T> mapper) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family =  HbaseHelper.toBytes(info.getFamily());

        Scan action = new Scan();
        action.addFamily(family);
        action.setRowPrefixFilter(rowkeyPrefix.getBytes());
        return HbaseHelper.execute(this.getConnection(), info.getTableName(), new TableCallback<List<T>>() {
            @Override
            public List<T> doInTable(Table table) throws Throwable {
                int caching = action.getCaching();
                // 如果caching未设置(默认是1)，将默认配置成5000
                if (caching == 1) {
                    action.setCaching(5000);
                }
                ResultScanner scanner = table.getScanner(action);
                try {
                    List<T> rs = new ArrayList<T>();
                    int rowNum = 0;
                    for (Result result : scanner) {
                        rs.add(mapper.mapRow(result, rowNum++));
                    }
                    return rs;
                } finally {
                    scanner.close();
                }
            }
        });
    }

    @Override
    public T get(String rowKey, String qualifier, RowMapper<T> mapper) {
        HBTableInfo info = HbaseHelper.getTableInfo(entityClass);
        byte[] family =  HbaseHelper.toBytes(rowKey);

        Get action = new Get(HbaseHelper.toBytes(rowKey));
        action.addFamily(family);
        action.addColumn(family, Bytes.toBytes(qualifier));

        return HbaseHelper.execute(connection, info.getTableName(), new TableCallback<T>() {
            @Override
            public T doInTable(Table table) throws Throwable {
                Result result = table.get(action);
                return mapper.mapRow(result, 0);
            }
        });
    }

    protected Class<T> currentModelClass() {
        //todo
        return null;
        //return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }
}
