package com.kelvin.oocl.service;

import com.kelvin.oocl.annotation.Transactional;
import com.kelvin.oocl.entity.Customer;
import com.kelvin.oocl.entity.Employee;

public interface UserService {

    @Transactional
    int save(Customer customer, Employee emp);
}
