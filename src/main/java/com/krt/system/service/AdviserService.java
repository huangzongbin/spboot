package com.krt.system.service;

import com.krt.common.entity.LayuiTable;
import com.krt.system.entity.Adviser;
import com.krt.system.entity.User;

import java.util.Map;

/**
 * @Author 黄宗滨
 * @Description
 * @Date  2019/5/28
 **/
public interface AdviserService {

    /**
     * 查询顾问列表
     */

    public LayuiTable selectAdviserList(Map para);

    /**
     * 用于添加
     */
    public  void insetrAdviser(Adviser adviser);

    /**
     * 删除用户
     */

    public  void  deletAdviser(int id);
    /**
     * 查询单个用户信息
     */
    public Map selectAdviserone(int id);

    /**
     * 修改添加信息
     */
    public  void  updateAdiviser(Adviser adviser);

    /**
     * 更改状态
     */

    public  void updateStatus(int id);
}
