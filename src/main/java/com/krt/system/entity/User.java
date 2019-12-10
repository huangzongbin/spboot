package com.krt.system.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author huang
 */
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private String sex;
    private int age;
    private  String phone;
    //    @DateTimeFormat(pattern = "yyyy-mm-dd HH;mm;ss")
    private Date insertTime;
    private String address;
    private int roleId;
    private Date updateTime;

    public User(int id, String username, String password, String name, String sex, int age, String phone, Date insertTime, String address, int roleId, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.insertTime = insertTime;
        this.address = address;
        this.roleId = roleId;
        this.updateTime = updateTime;
    }

    public User() {
    }
}
