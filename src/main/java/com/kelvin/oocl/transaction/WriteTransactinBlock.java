package com.kelvin.oocl.transaction;

import com.kelvin.oocl.common.EntityManagerAccessor;
import com.kelvin.oocl.exception.TransactionCommitException;
import com.kelvin.oocl.util.LogUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class WriteTransactinBlock implements WriteTransaction {
    private EntityManagerAccessor entityManagerAccessor = EntityManagerAccessor.getEntityManagerAccessor();

    private EntityManager getEntityManager(){
        return entityManagerAccessor.getEntityManager();
    }

    @Override
    public void execute() {
        EntityTransaction tx = null;
        try {
            LogUtil.info(WriteTransactinBlock.class, Thread.currentThread().getName()+ " EntityManager " + entityManagerAccessor.getEntityManager());
            tx = entityManagerAccessor.getEntityManager().getTransaction();
            tx.begin();
            this.run();
            tx.commit();
        } catch (Exception ex){
            throw new TransactionCommitException("Error in commit transaction");
        }
    }

    public abstract void run();
}
