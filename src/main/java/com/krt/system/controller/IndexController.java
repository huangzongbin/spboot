package com.krt.system.controller;

import com.krt.common.util.ShiroUtil;
import com.krt.system.service.LoginService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : huang
 * @create: 2019-${MOUTH}-21 11:56
 */
@Controller
public class IndexController {

    @Resource
    private LoginService loginService;
    /**
     * 登入后加载的欢迎页面
     * @return
     */
    @RequestMapping("welcome")
    public String welcome(HttpServletRequest request){
        Map userMap=ShiroUtil.getCurrentUser();
        Date sysTime=new Date();
        request.setAttribute("sysTime",sysTime);
        request.setAttribute("userMap",userMap);
        return "system/welcome";
    }

    /**
     *测试数据库
     */
    @RequestMapping("testmybat")
    @ResponseBody
    public String testmybat(){
       Map map = new HashMap<>();
       String usename="huang";
        map=loginService.selectCurrentUser(usename);
        System.out.println("=========================="+map);
        return "ok";
    }
}
