package com.dist.rbac.dmn.impl;

import com.dist.model.rbac.entity.User;
import com.dist.rbac.dao.UserRepository;
import com.dist.rbac.dmn.IUserDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 14:45
 */
@Component
public class UserDmnImpl implements IUserDmn {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
