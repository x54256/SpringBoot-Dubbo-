package com.dist.model.rbac.dto;

import com.dist.model.rbac.entity.Role;
import com.dist.model.rbac.entity.UserGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"roles", "userGroups"})
public class UserDTO implements Serializable {

    private Long id;

    // 用户名
    private String userName;

    // 密码
    private String passWord;

    // 手机号
    private String mobilePhone;

    // 邮箱
    private String email;

    // 与角色表的多对多关系；对中间表级联【保存、更新、删除】
    private Set<Role> roles;

    // 与用户组表的多对多关系；对中间表级联【保存、更新、删除】
    private Set<UserGroup> userGroups;


}
