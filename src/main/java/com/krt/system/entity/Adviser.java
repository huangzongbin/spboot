package com.krt.system.entity;


import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 黄宗滨
 * @Description   资源实体
 * @Date  2019/5/28
 **/
@Data
public class Adviser {
    private  int id;
    private String name;
    private String department;
    private String university;
    private Date time;
    private String profession;
    private String phone;
    private int status;
    private String insertName;
    private String remark;
    private String qq_wc;

    public Adviser(int id, String name, String department, String university, Date time, String profession, String phone, int status, String insertName, String remark, String qq_wc) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.university = university;
        this.time = time;
        this.profession = profession;
        this.phone = phone;
        this.status = status;
        this.insertName = insertName;
        this.remark = remark;
        this.qq_wc = qq_wc;
    }

    public Adviser() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(String time) {
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=sf.parse(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.time=date;
    }
}
