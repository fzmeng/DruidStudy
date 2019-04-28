package com.study.service;

import com.study.mapper.CustomerMapper;
import com.study.model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 2019/4/28 15:33
 * @Version 1.0
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerModel> queryCustomers(CustomerModel model) {
        return customerMapper.query(model);
    }

    public Long updateCustomer(CustomerModel model) {
        return customerMapper.update(model);
    }

    public Long insertCustomer(CustomerModel model) {
        return customerMapper.insert(model);
    }
}
