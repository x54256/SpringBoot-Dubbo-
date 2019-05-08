package com.dist.model.rbac.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * rbac-用户表
 *
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"roles", "userGroups"})
@Entity
@Table(name = "rbac_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles;

//    用户与用户组一对多版
//    // 多的一方【儿子】；类名为userGroup；懒加载
//    @JoinColumn(name = "userGroupId")
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserGroup.class) // 查儿子不找父亲
//    private UserGroup userGroup;


    // 与用户组表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserGroup> userGroups;


}
