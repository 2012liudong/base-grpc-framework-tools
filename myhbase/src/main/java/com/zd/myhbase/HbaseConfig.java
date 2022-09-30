package com.zd.myhbase;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Data
@Component
public class HbaseConfig {

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";
    private static final String HBASE_ROOTDIR = "hbase.rootdir";
    private static final String HBASE_ZOOKEEPER_CLIENT_PORT = "hbase.zookeeper.property.clientPort";
    private static final String HBASE_HDFS_IMPL = "fs.hdfs.impl";

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2048, 2048, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private Connection connection;

    @Autowired
    private HBaseProperties hBaseProperties;

    @Bean("coreHbase")
    public Connection getConnection() {
        if (null == this.connection) {
            synchronized (this) {
                if (null == this.connection) {
                    try {
                        Configuration configuration = new Configuration();
                        configuration.set(HBASE_QUORUM, hBaseProperties.getQuorum());
                        configuration.set(HBASE_ROOTDIR, hBaseProperties.getRootDir());
                        configuration.set(HBASE_HDFS_IMPL, hBaseProperties.getHdfsImpl());
                        configuration.set(HBASE_ZOOKEEPER_CLIENT_PORT, hBaseProperties.getZkClientPort());
//                        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(128, 512, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
                        // init pool
                        poolExecutor.prestartCoreThread();
                        this.connection = ConnectionFactory.createConnection(configuration, poolExecutor);
                    } catch (IOException e) {
                        log.error("create HBbase resource fail");
                    }
                }
            }
        }
        return this.connection;
    }

//    /*这个方法主要用于测试使用，后续需要删除掉*/
//    protected  Connection getConnectionTest() {
//        if (null == this.connection) {
//            synchronized (this) {
//                if (null == this.connection) {
//                    try {
//                        Configuration configuration = new Configuration();
//                        configuration.set(HBASE_QUORUM, "10.11.10.142:2181");
//                        configuration.set(HBASE_ROOTDIR, "hdfs://10.11.10.142:9000/hbase");
//                        configuration.set(HBASE_HDFS_IMPL, "org.apache.hadoop.hdfs.DistributedFileSystem");
//                        configuration.set(HBASE_ZOOKEEPER_CLIENT_PORT, "2181");
////                        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(128, 512, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
//                        // init pool
//                        poolExecutor.prestartCoreThread();
//                        this.connection = ConnectionFactory.createConnection(configuration, poolExecutor);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return this.connection;
//    }

    @Bean("coreHbaseThread")
    public ThreadPoolExecutor tablePoolExecutor(){
        return poolExecutor;
    }

    @Data
    @org.springframework.context.annotation.Configuration
    @ConfigurationProperties(prefix = "hbase")
    public static class HBaseProperties {
        private String quorum;
        private String rootDir;
        private String hdfsImpl;
        private String zkClientPort;
    }

}
