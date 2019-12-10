package com.krt.system.mapper;

import com.krt.system.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author 黄宗滨
 * @Description
 * @Date  2019/5/25
 **/
public interface UserMapper {
    


    /**
     * 查询所有的用户信息
     */
    List<Map> selctUserlist(Map para);

    /**
     *查询所有的用户总数
     *
     */
    int slectUsercount();


    /**
     * 删除用户信息
     */
    void delectuser(int id);

    /**
     * 用于添加用户信息, ("insetTimes") String insettime
     */
    void insertuser(User user);

    /**
     * 更新用户信息
     */
    void updateuser(User user);

    /**
     * 通过id查询用户
     */
    Map selectuser(int id);
}
