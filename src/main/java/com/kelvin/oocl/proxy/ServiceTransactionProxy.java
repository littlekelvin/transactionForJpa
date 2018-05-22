package com.kelvin.oocl.proxy;

import com.kelvin.oocl.annotation.Transactional;
import com.kelvin.oocl.common.EntityManagerAccessor;
import com.kelvin.oocl.exception.TransactionCommitException;
import com.kelvin.oocl.util.LogUtil;

import javax.persistence.EntityTransaction;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceTransactionProxy implements InvocationHandler{
    private Object target;

    public ServiceTransactionProxy(Object target) {
        super();
        this.target = target;
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!method.isAnnotationPresent(Transactional.class)){
            LogUtil.info(ServiceTransactionProxy.class, "do not start transaction");
            return method.invoke(target, args);
        }
        try {
            LogUtil.info(ServiceTransactionProxy.class, "do start transaction");
            EntityTransaction tx = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager().getTransaction();
            tx.begin();
            Object result = method.invoke(target, args);
            tx.commit();
            LogUtil.info(ServiceTransactionProxy.class, "do commit transaction");
            return result;
        } catch (Exception ex){
            throw new TransactionCommitException("Error in commit transaction");
        }
    }
}
