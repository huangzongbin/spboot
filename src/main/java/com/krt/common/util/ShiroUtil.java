package com.krt.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Map;

/**
 * @Author 黄宗滨
 * @Description
 * @Date  2019/5/29
 **/
public class ShiroUtil {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }


    /**
     * 获取session用户
     * @return
     */
    public static Map getCurrentUser() {
        return (Map) getSession().getAttribute("currentUser");
    }

    /**
     * 保存session属性
     * @param key
     * @param value
     */
    public static void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 获取session属性
     * @param key
     * @return
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

}
