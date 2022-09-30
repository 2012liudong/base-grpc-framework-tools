package com.zd.myhbase.callback;

import org.apache.hadoop.hbase.client.BufferedMutator;

public interface MutatorCallback {

    /**
     * 使用mutator api to update put and delete
     * @param mutator
     * @throws Throwable
     */
    void doInMutator(BufferedMutator mutator) throws Throwable;
}
