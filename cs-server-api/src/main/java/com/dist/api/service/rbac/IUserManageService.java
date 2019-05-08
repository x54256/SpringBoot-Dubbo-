package com.dist.api.service.rbac;

import com.dist.model.rbac.dto.UserDTO;
import com.dist.model.rbac.dto.UserGroupDTO;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 14:43
 */
public interface IUserManageService {

    /**
     * 保存用户
     *
     * @param userDTO
     * @return
     */
    UserDTO saveUser(UserDTO userDTO);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<UserDTO> listAllUser();

    /**
     * 保存用户组
     *
     * @param userGroupDTO
     * @return
     */
    UserGroupDTO saveUserGroup(UserGroupDTO userGroupDTO);

    /**
     * 查询所有用户组
     *
     * @return
     */
    List<UserGroupDTO> listAllUserGroup();
}
