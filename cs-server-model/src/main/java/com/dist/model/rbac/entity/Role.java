package com.dist.model.rbac.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * rbac-角色表
 *
 * @author yujx
 * @date 2019/04/19 15:26
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"parentRole", "childRole", "users", "permissions", "userGroups"})
@Entity
@Table(name = "rbac_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 角色名
    private String roleName;

    // ******** 自关联开始 ********

    // 父角色
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class) // 查儿子不找父亲
    @JoinColumn(name = "pid")
    private Role parentRole;

    // 查询的时候使用 select还是join还是子查询
    //@Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Role.class,
            cascade = {CascadeType.ALL},    // 父亲的一方删除级联删除所有儿子
            mappedBy = "parentRole",    // 谁来维护关联关系（一（父亲）放弃维护外键关系）
            fetch = FetchType.LAZY  // 查父亲不找儿子
    )
    private Set<Role> childRole;

    // ******** 自关联结束 ********

    // 与用户表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users;

    // 与权限表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Permission> permissions;

    // 与用户组表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserGroup> userGroups;
}
