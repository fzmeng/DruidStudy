package com.study.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomerModel {
    private Long id;

    private Timestamp createTime;

    private String userName;

    private String mobile;
}