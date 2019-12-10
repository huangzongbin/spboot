package com.krt.common.entity;


import java.util.List;

/**
 * @Author 黄宗滨
 * @Description  用于分页实体
 * @Date  2019/5/28
 **/
public class LayuiTable {

    private String code = "0";
    private String msg="";
    private int count;
    private List data;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public LayuiTable(String code, String msg, int count, List data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public LayuiTable() {
    }
}