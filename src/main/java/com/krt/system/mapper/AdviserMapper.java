package com.krt.system.mapper;

import com.krt.system.entity.Adviser;

import java.util.List;
import java.util.Map;

/**
 * @Author 黄宗滨
 * @Description  map层
 * @Date  2019/5/28
 **/

public interface AdviserMapper {

    /**
     * 用户查询资源列表
     */

    List<Map> selectAdviserList(Map para);

    /**
     * 用户统计列表总数
     */
    int selectAdviserCount();

    /**
     * 用于添加
     */
    void insetrAdviser(Adviser adviser);

    /**
     * 删除用户
     */
    void deletAdviser(int id);

    /**
     * 查询一个用户信息
     */
    Map selectAdciserone(int id);

    /**
     * 修改用户信息
     */
    void updateAdiviser(Adviser adviser);

    /**
     * 更改状态
     */

    void updateStatus(int id );
}
