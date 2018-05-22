package com.kelvin.oocl.common;

import com.kelvin.oocl.transaction.WriteTransactinBlock;
import com.kelvin.oocl.util.LogUtil;

import javax.persistence.EntityManager;

public class BaseDao<T> {
    private EntityManagerAccessor entityManagerAccessor = EntityManagerAccessor.getEntityManagerAccessor();

    private EntityManager getEntityManager(String persistenceUnit){
        return entityManagerAccessor.getEntityManager(persistenceUnit);
    }

    private EntityManager getEntityManager(){
        return entityManagerAccessor.getEntityManager();
    }

    public void persist(T entity){
        LogUtil.info(BaseDao.class, Thread.currentThread().getName()+ " EntityManager " + entityManagerAccessor.getEntityManager());
        this.getEntityManager().persist(entity);
    }
}
