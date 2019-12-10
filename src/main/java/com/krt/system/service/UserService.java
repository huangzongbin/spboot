package com.krt.system.service;

import com.krt.system.entity.User;

import java.util.Map;

/**
 * @Author : huang
 * @create: 2019-${MOUTH}-23 15:15
 */

public interface UserService {

    /**
     *  查询用于列表
     * @return
     */
    public Map selctUserlist(Map para);

    /**
     * 通过参数进行删选
     */
    public Map selectuser(int id);
    /**
     * 用于删除用户
     */
    public  void delectuser(int id);

    /**
     * 用户添加用户信息
     */
    public void insertuser(User user);


    /**
     * 更改用户信息
     */
    public  void updateusre(User user);
}
