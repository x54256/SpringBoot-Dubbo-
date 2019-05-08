package com.dist.rbac.dmn;

import com.dist.model.rbac.entity.UserGroup;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 15:15
 */
public interface IUserGroupDmn {

    /**
     * 根据id查询用户组信息
     *
     * @param id
     * @return
     */
    UserGroup getUserGroupById(Long id);

    /**
     * 保存用户组信息
     *
     * @param userGroup
     * @param pid
     * @return
     */
    UserGroup save(UserGroup userGroup, Long pid);

    /**
     * 查询所有用户组
     *
     * @return
     */
    List<UserGroup> listAll();
}
