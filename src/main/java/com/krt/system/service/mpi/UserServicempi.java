package com.krt.system.service.mpi;

import com.krt.system.entity.User;
import com.krt.system.mapper.UserMapper;
import com.krt.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author : huang
 * @create: 2019-${MOUTH}-23 15:15
 */
@Service
public class UserServicempi implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询用户列表
     *
     * @return
     */
    @Override
    public Map selctUserlist(Map para) {
        List<Map> list = new ArrayList<>();
        int page=Integer.parseInt(para.get("page")+"");
        int limit=Integer.parseInt(para.get("limit")+"");

        page=(page-1)*limit;
        para.put("page",page);
        para.put("limit",limit);
        list = userMapper.selctUserlist(para);
        int size=userMapper.slectUsercount();
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "");
        map.put("count", size);
        map.put("data", list);
        return map;
    }

    /**
     * 删除用户列表
     */

    @Override
    public void delectuser(int id) {
        userMapper.delectuser(id);
    }

    /**
     * 添加用户信息
     */
    @Override
    public void insertuser(User user) {
        //插入时间
        Date date = new Date();
        user.setInsertTime(date);
        userMapper.insertuser(user);
    }

    /**
     *   用于更新用户信息
     * @param user
     */
    @Override
    public  void updateusre(User user){

    userMapper.updateuser(user);
    }

    /**
     * 通过参数限制进行查询
     */
    @Override
    public Map selectuser(int id) {
        return userMapper.selectuser(id);
    }
}
