package com.kelvin.oocl;

import com.kelvin.oocl.entity.Customer;
import org.junit.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestCustomer {
	private static EntityManagerFactory factory;
	private EntityManager manager;
	
	@BeforeClass
	public static void init(){
		factory = Persistence.createEntityManagerFactory("john");
	}
	
	@AfterClass
	public static void destroy(){
		factory.close();
	}
	
	@Before
	public void start(){
		manager = factory.createEntityManager();
	}
	
	@After
	public void end(){
		manager.close();
	}
	
	@Test
	public void testSave(){
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Customer customer = new Customer(23, "Tom", "333");
		manager.persist(customer);
		tx.commit();
	}
}
