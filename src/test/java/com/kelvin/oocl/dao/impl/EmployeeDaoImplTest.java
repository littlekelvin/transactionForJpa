package com.kelvin.oocl.dao.impl;

import com.kelvin.oocl.dao.CustomerDao;
import com.kelvin.oocl.dao.EmployeeDao;
import com.kelvin.oocl.entity.Customer;
import com.kelvin.oocl.entity.Employee;
import com.kelvin.oocl.transaction.WriteTransactinBlock;
import org.junit.Test;

public class EmployeeDaoImplTest {
    static CustomerDao customerDao = new CustomerDaoImpl();
    static EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Test
    public void testSave() throws Exception {
        Customer customer = new Customer(1,"Jack", "111");
        customerDao.save(customer);
    }

    @Test
    public void testSaveWithTx() throws Exception {
        final Customer customer = new Customer(1,"Jack", "111");
        new WriteTransactinBlock(){

            @Override
            public void run() {
                customerDao.save(customer);
            }
        }.execute();
    }

    @Test
    public void testSaveWithTx01() throws Exception {
        final Customer customer = new Customer(8,"Jack", "111");
        final Employee emp = new Employee(3, "Tommy", "Software road");
        new WriteTransactinBlock(){

            @Override
            public void run() {
                customerDao.save(customer);
                employeeDao.save(emp);
            }
        }.execute();
    }

    public static void main(String[] args) {
        new Thread("Thread-1"){
            @Override
            public void run() {
                final Customer customer = new Customer(11,"Jack", "111");
                final Employee emp = new Employee(3, "Tommy", "Software road");
                new WriteTransactinBlock(){

                    @Override
                    public void run() {
                        customerDao.save(customer);
                        employeeDao.save(emp);
                    }
                }.execute();
            }
        }.start();

        new Thread("Thread-2"){
            @Override
            public void run() {
                final Customer customer = new Customer(16,"Jack", "111");
                final Employee emp = new Employee(10, "Tommy", "Software road");
                new WriteTransactinBlock(){

                    @Override
                    public void run() {
                        customerDao.save(customer);
                        employeeDao.save(emp);
                    }
                }.execute();
            }
        }.start();
    }
}