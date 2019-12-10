package com.krt.common.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 黄宗滨
 * @Description
 * @Date  2019/5/25
 **/
@Component
public class FreemarkerConfig implements InitializingBean {

    @Autowired
    private Configuration configuration;


    @Override
    public void afterPropertiesSet() throws Exception {
        // shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());

    }

}
