package com.krt.system.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author : huang
 * @create: 2019-${MOUTH}-21 16:44
 */
@Repository
public interface LoginMapper {

    Map selectCurrentUser(String username);
}
