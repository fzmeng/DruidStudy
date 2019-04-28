package com.study;

import com.study.model.CustomerModel;
import com.study.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
public class DruidTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void queryCustomer(){
        List<CustomerModel> customerModelList = customerService.queryCustomers(new CustomerModel());
        System.out.println(customerModelList.size());
    }

    @Test
    public void insertCustomer(){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setUserName("张三");
        customerModel.setMobile("13322221111");
        System.out.println("save ==== "+customerService.insertCustomer(customerModel));
        updateCustomer();
    }

    @Test
    public void updateCustomer(){
        List<CustomerModel> customerModelList = customerService.queryCustomers(new CustomerModel());
        CustomerModel updModel = customerModelList.get(0);
        updModel.setUserName("张三upd");
        System.out.println("update ==== "+customerService.updateCustomer(updModel));
    }
}
