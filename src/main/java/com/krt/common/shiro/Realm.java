package com.krt.common.shiro;

import com.krt.common.util.ShiroUtil;
import com.krt.system.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.boot.web.servlet.server.Session;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 执行自定义realm
 */

public class Realm extends AuthorizingRealm {


    @Resource
    private LoginService loginService;
    /**
     *执行授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 执行认证
     * @param org
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken org) throws AuthenticationException {


        UsernamePasswordToken token = (UsernamePasswordToken) org;
        Map currentUser=new HashMap();
        currentUser=loginService.selectCurrentUser(token.getUsername());


        if (null==currentUser){
            System.out.println("账号或密码不正确");
            throw new  UnknownAccountException("账号或密码不正确");
        }
        String password =(String) currentUser.get("password");
        String username=(String) currentUser.get("username");
        if (!token.getUsername().equals(username)) {
            System.out.println("账号或密码不正确");
            throw  new IncorrectCredentialsException("账号或密码不正确");
        }
        if (null != token) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username + "", password + "", "");
            ShiroUtil.setSessionAttribute( "currentUser",currentUser);
            System.out.println("==============欢迎=========="+currentUser.get("name"));
            return authcInfo;
        }
        System.out.println("账号或密码不正确");
        throw  new UnknownAccountException("账号或密码不正确");
    }

}
