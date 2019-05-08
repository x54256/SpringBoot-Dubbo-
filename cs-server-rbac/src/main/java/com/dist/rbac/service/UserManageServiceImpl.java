package com.dist.rbac.service;

import com.dist.api.service.rbac.IUserManageService;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.UserDTO;
import com.dist.model.rbac.dto.UserGroupDTO;
import com.dist.model.rbac.entity.User;
import com.dist.model.rbac.entity.UserGroup;
import com.dist.rbac.dmn.IUserDmn;
import com.dist.rbac.dmn.IUserGroupDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 14:43
 */
@Service
public class UserManageServiceImpl implements IUserManageService {

    @Autowired
    private IUserDmn userDmn;

    @Autowired
    private IUserGroupDmn userGroupDmn;

    @Autowired
    private IGenerator dozer;

    /**
     * 保存用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        User user = dozer.convert(userDTO, User.class);
        user = userDmn.save(user);

        return dozer.convert(user, UserDTO.class);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<UserDTO> listAllUser() {
        return dozer.convert(userDmn.listAll(), UserDTO.class);
    }

    /**
     * 保存用户组
     *
     * @param userGroupDTO
     * @return
     */
    @Override
    public UserGroupDTO saveUserGroup(UserGroupDTO userGroupDTO) {

        UserGroup userGroup = dozer.convert(userGroupDTO, UserGroup.class);

        return dozer.convert(userGroupDmn.save(userGroup, userGroupDTO.getPid()), UserGroupDTO.class);
    }

    /**
     * 查询所有用户组
     *
     * @return
     */
    @Override
    public List<UserGroupDTO> listAllUserGroup() {
        return dozer.convert(userGroupDmn.listAll(), UserGroupDTO.class);
    }
}
