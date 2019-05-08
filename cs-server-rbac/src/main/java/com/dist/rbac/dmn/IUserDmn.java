package com.dist.rbac.dmn;

import com.dist.model.rbac.entity.User;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 14:45
 */
public interface IUserDmn {
    User save(User user);

    List<User> listAll();
}
