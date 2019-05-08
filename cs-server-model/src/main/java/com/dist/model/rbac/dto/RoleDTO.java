package com.dist.model.rbac.dto;

import com.dist.model.rbac.entity.Permission;
import com.dist.model.rbac.entity.User;
import com.dist.model.rbac.entity.UserGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * @author yujx
 * @date 2019/04/19 15:26
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"parentRole", "childRole", "users", "permissions", "userGroups"})
public class RoleDTO implements Serializable {

    private Long id;

    // 角色名
    private String roleName;

    // 父id
    private Long pid;

    // 权限ids
    private Set<Long> permissionIds;

    // ******** 自关联开始 ********

    // 父角色
    private RoleDTO parentRole;

    // 子角色id
    private Set<RoleDTO> childRole;

    // ******** 自关联结束 ********

    // 与用户表的多对多关系；对中间表级联【保存、更新、删除】
    private Set<User> users;

    // 与权限表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Permission> permissions;

    // 与用户组表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserGroup> userGroups;
}
