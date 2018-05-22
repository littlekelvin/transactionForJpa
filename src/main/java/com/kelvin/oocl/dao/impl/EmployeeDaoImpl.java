package com.kelvin.oocl.dao.impl;
import com.kelvin.oocl.common.BaseDao;
import com.kelvin.oocl.dao.EmployeeDao;
import com.kelvin.oocl.entity.Employee;

public class EmployeeDaoImpl extends BaseDao<Employee> implements EmployeeDao {

    @Override
    public int save(Employee emp) {
        this.persist(emp);
        return 1;
    }
}
