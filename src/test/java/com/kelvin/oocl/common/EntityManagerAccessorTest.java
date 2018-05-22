package com.kelvin.oocl.common;

import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class EntityManagerAccessorTest {
    @Test
    public void testGetEntityManager() throws Exception {
        EntityManager em = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("kelvin");
        System.out.println(em);
    }

    @Test
    public void testGetEntityManager01() throws Exception {
        EntityManager em = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("kelvin");
        EntityManager em1 = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("john");
        System.out.println(em);
        System.out.println(em1);
    }

    @Test
    public void testGetEntityManager02() throws Exception {
        EntityManager em = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("john");
        EntityManager em1 = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager();
        System.out.println(em);
        System.out.println(em1);
    }

    public static void main(String[] args) {
        new Thread("thead-1") {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    EntityManager em = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("kelvin");
                    EntityManager em1 = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("john");
                    System.out.println(Thread.currentThread().getName() +"-> "+ em);
                    System.out.println(Thread.currentThread().getName() +"-> "+ em1);
                }
            }
        }.start();

        new Thread("thead-2") {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    EntityManager em = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("kelvin");
                    EntityManager em1 = EntityManagerAccessor.getEntityManagerAccessor().getEntityManager("john");
                    System.out.println(Thread.currentThread().getName() +"-> "+ em);
                    System.out.println(Thread.currentThread().getName() +"-> "+ em1);
                }
            }
        }.start();
    }
}