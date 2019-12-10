package com.krt.system.controller;

import com.krt.common.bean.ReturnBean;
import com.krt.system.entity.User;
import com.krt.system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : huang
 * @create: 2019-${MOUTH}-23 15:13
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用于调转用户列表页面
     *
     * @return
     */
    @GetMapping("system/userlist")
    public String userlist() {
        return "system/userAction/userlist";
    }

    /**
     * 用于数据请求，返回数据列表
     *
     * @return
     */
    @GetMapping("system/userlists")
    @ResponseBody
    public Map userlists(@RequestParam Map para) {
        Map map = new HashMap();
        map = userService.selctUserlist(para);
        return map;
    }

    /**
     * 用户删除列表
     */
    @PostMapping("system/deletuser")
    @ResponseBody
    public ReturnBean delectuser(int id) {
        ReturnBean rb;
        try {
            userService.delectuser(id);
            rb = ReturnBean.ok();
        } catch (Exception e) {
            rb = ReturnBean.error("删除错误");
        }
        return rb;
    }

    /**
     * 添加用户信息
     */
    @GetMapping("system/adduser")
    public String adduser() {
        return "system/userAction/useradd";
    }

    /**
     * 添加用户信息
     */
    @PostMapping("system/useradd")
    @ResponseBody
    public ReturnBean adduser(User user) {
        ReturnBean rb;
        try {
            userService.insertuser(user);
            rb = ReturnBean.ok();
        } catch (Exception e) {
            rb = ReturnBean.error("添加失败");
        }
        return rb;
    }

    /**
     * 更改用户信息 页面跳转
     *
     * @return
     */
    @GetMapping("system/edituser")
    public String edituser(HttpServletRequest request, int id) {
        Map map = new HashMap();
        map = userService.selectuser(id);
        System.out.println("查询当个用户信息" + map);
        request.setAttribute("map", map);
        return "system/userAction/edituser";
    }

    /**
     * 更改用户信息
     */
    @PostMapping("system/useredit")
    @ResponseBody
    public ReturnBean edituser(User user) {
        ReturnBean rb;
        try {
            userService.updateusre(user);
            rb = ReturnBean.ok();
        } catch (Exception e) {
            rb = ReturnBean.error("更新失败");
        }
        return rb;
    }
}
