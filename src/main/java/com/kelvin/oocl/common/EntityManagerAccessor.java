package com.kelvin.oocl.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityManagerAccessor {
    private static EntityManagerAccessor entityManagerAccessor;
    private Map<String, EntityManagerFactory> emfMap = new ConcurrentHashMap<>();
    private static ThreadLocal<Map<String, EntityManager>> emHolder = new ThreadLocal<>();

    public EntityManagerAccessor() {
        initialize();
    }

    /**
     * init emfMap
     */
    private void initialize(){
        List<String> puIds = PersistenceUnitUtil.getPersistenceUnitIds();
        for(String puId : puIds){
            emfMap.put(puId, Persistence.createEntityManagerFactory(puId));
        }
    }

    public static EntityManagerAccessor getEntityManagerAccessor(){
        if(null == entityManagerAccessor){
            synchronized (EntityManagerAccessor.class) {
                entityManagerAccessor = new EntityManagerAccessor();
            }
        }
        return entityManagerAccessor;
    }

    public EntityManager getEntityManager() {
        return this.getEntityManager(PersistenceUnitUtil.getPersistenceUnitIds().get(0));
    }

    /**
     * return Entity Manager for special persistence Unit
     * @param persistentUnitName
     * @return
     */
    public EntityManager getEntityManager(String persistentUnitName) {
        Map<String, EntityManager> emMap = emHolder.get();
        if(null == emMap){
            emMap = createAllEntityManagerForCurrentThread();
            emHolder.set(emMap);
        }
        return emMap.get(persistentUnitName);
    }

    private Map<String,EntityManager> createAllEntityManagerForCurrentThread() {
        Map<String,EntityManager> emMap = new HashMap<>();
        for(Map.Entry<String, EntityManagerFactory> entry : this.emfMap.entrySet()){
            String puId = entry.getKey();
            EntityManagerFactory factory = entry.getValue();
            emMap.put(puId, factory.createEntityManager());
        }
        return emMap;
    }
}
