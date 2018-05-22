package com.kelvin.oocl.service.impl;

import com.kelvin.oocl.annotation.Transactional;
import com.kelvin.oocl.dao.CustomerDao;
import com.kelvin.oocl.dao.EmployeeDao;
import com.kelvin.oocl.dao.impl.CustomerDaoImpl;
import com.kelvin.oocl.dao.impl.EmployeeDaoImpl;
import com.kelvin.oocl.entity.Customer;
import com.kelvin.oocl.entity.Employee;
import com.kelvin.oocl.service.UserService;

public class UserServiceImpl implements UserService {
    CustomerDao customerDao = new CustomerDaoImpl();
    EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public int save(Customer customer, Employee emp) {
        customerDao.save(customer);
        employeeDao.save(emp);
        return 1;
    }
}
