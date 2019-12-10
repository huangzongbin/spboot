package com.krt.common.config;

import com.krt.common.shiro.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建 ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager  securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 添加shiro内置的过滤器
         */
        Map<String,String> filtemap=new LinkedHashMap<String,String>();
        filtemap.put("/logout", "logout");
        filtemap.put("/tologin", "anon");
        filtemap.put("/Statics/**","anon");
        //filtemap.put("/index","authc");
        filtemap.put("/login","anon");
        filtemap.put("/**","authc");
        //filtemap.put("/add","perms[user:add]");
        shiroFilterFactoryBean.setLoginUrl("tologin");
        shiroFilterFactoryBean.setUnauthorizedUrl("tologin");  //这是一个未授权调转页面
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtemap);

        return shiroFilterFactoryBean;
    }


    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") Realm realm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 创建自定义ream
     */
    @Bean(name = "realm")
    public Realm getRealm(){
        return new Realm();
    }
}
