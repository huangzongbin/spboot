package com.krt.system.service.mpi;

import com.krt.system.mapper.LoginMapper;
import com.krt.system.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author : ljy
 * @create: 2019-${MOUTH}-20 16:58
 */
@Service
public class LoginServicempi implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public Map selectCurrentUser(String username) {
      return loginMapper.selectCurrentUser(username);

    }
}
