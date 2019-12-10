package com.krt.system.service;

import java.util.Map;

/**
 * @Author : huang
 * @create: 2019-${MOUTH}-20 16:56
 */
public interface LoginService {
    /**
     * 用于查询所有的用户
     * @return
     */
    public Map selectCurrentUser(String username);
}
