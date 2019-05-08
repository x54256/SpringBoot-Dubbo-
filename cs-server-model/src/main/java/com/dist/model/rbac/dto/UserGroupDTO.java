package com.dist.model.rbac.dto;

import com.dist.model.rbac.entity.Role;
import com.dist.model.rbac.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"parentUserGroup", "childUserGroup", "roles", "users"})
public class UserGroupDTO implements Serializable {

    private Long id;

    // 用户组名
    private String userGroupName;

    // 父id
    private Long pid;

    // 父用户组
    @JsonIgnore
    private UserGroupDTO parentUserGroup;

    // 子用户组
    private List<UserGroupDTO> childUserGroup;

    // 具有的角色
    private List<Role> roles;


    // 用户组下的用户
    private List<User> users;
}
