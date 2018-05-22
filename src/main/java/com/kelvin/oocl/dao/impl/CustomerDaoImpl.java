package com.kelvin.oocl.dao.impl;

import com.kelvin.oocl.common.BaseDao;
import com.kelvin.oocl.dao.CustomerDao;
import com.kelvin.oocl.entity.Customer;

public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao {
    @Override
    public int save(Customer customer) {
        this.persist(customer);
        return 1;
    }
}
