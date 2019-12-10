package com.krt.system.controller;

import com.krt.common.bean.ReturnBean;
import com.krt.common.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class LoginController {

    /**
     *   没有请求返回页面
     * @return
     */
    @RequestMapping("/")
    public String doLogin(){
        return "system/login";
    }
    /**
     * 开始登入验证
     * @return
     */
    @RequestMapping("/tologin")
    public  String tologin(){

       Map map= ShiroUtil.getCurrentUser();
       if (null!=map){

        return  "redirect:/index";
       }

        return "system/login";
    }


    /**
     * 通过shiro进行认证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ReturnBean Login(String username, String password){

        ReturnBean rb;
        //通过shiro 进行认证
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            rb= ReturnBean.ok();
        }catch (UnknownAccountException e){
            rb=ReturnBean.error("账户或者密码错误");
        }catch (IncorrectCredentialsException e){
            rb=ReturnBean.error("账户或者密码错误");
        }
        catch (Exception e){
            rb= ReturnBean.error("登入出现异常");
        }
        return rb;
    }


    /**
     * 登入成功后的跳转
     * @return
     */
    @RequestMapping("/index")
    public  String index(){
        return "system/index";
    }

    /**
     * 退出登入
     */
    @RequestMapping("/logout")
    public String logout(){
        return "system/login";
    }


}
