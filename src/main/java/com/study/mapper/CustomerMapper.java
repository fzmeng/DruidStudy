package com.study.mapper;


import com.study.model.CustomerModel;

import java.util.List;

public interface CustomerMapper{

    List<CustomerModel> query(CustomerModel model);

    Long insert(CustomerModel customerModel);

    Long update(CustomerModel customerModel);
}