package com.dist.biz.domain.impl;

import com.dist.biz.dao.UserRepository;
import com.dist.biz.domain.HelloDmn;
import com.dist.model.biz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yujx
 * @date 2019/02/14 09:38
 */
@Component
public class HelloDmnImpl implements HelloDmn{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
