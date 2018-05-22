package com.kelvin.oocl.service.impl;

import com.kelvin.oocl.entity.Customer;
import com.kelvin.oocl.entity.Employee;
import com.kelvin.oocl.proxy.ServiceTransactionProxy;
import com.kelvin.oocl.service.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void testSave() throws Exception {
        final Customer customer = new Customer(8,"Jack", "111");
        final Employee emp = new Employee(8, "Tommy", "Software road");
        UserService userServiceProxy = (UserService) new ServiceTransactionProxy(userService).createProxy();
        userServiceProxy.save(customer, emp);
    }

}