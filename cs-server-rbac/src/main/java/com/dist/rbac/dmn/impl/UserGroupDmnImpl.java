package com.dist.rbac.dmn.impl;

import com.dist.base.exception.ObjectIsNullException;
import com.dist.model.rbac.entity.UserGroup;
import com.dist.rbac.dao.UserGroupRepository;
import com.dist.rbac.dmn.IUserGroupDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author yujx
 * @date 2019/04/22 15:16
 */
@Component
public class UserGroupDmnImpl implements IUserGroupDmn {

    @Autowired
    private UserGroupRepository userGroupRepository;

    /**
     * 根据id查询用户组信息
     *
     * @param id
     * @return
     */
    @Override
    public UserGroup getUserGroupById(Long id) {
        return userGroupRepository.findById(id).orElse(null);
    }

    /**
     * 保存用户组信息
     *
     * @note dmn是屏蔽主外键关系的，所以应该像下面这样操作
     * @param userGroup
     * @param pid 父id
     * @return
     */
    @Override
    public UserGroup save(UserGroup userGroup, Long pid) {

        // 如果父id不为空，则查询
        if (pid != null) {
            Optional<UserGroup> optional = userGroupRepository.findById(pid);
            if (!optional.isPresent()) {
                throw new ObjectIsNullException("查询到的父用户组为空，请检查传入参数！");
            }

            userGroup.setParentUserGroup(optional.get());
        }

        return userGroupRepository.save(userGroup);
    }

    /**
     * 查询所有用户组
     *
     * @return
     */
    @Override
    public List<UserGroup> listAll() {
        return userGroupRepository.findAll();
    }
}
