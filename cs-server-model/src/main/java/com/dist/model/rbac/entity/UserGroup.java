package com.dist.model.rbac.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * rbac-用户组
 *
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"parentUserGroup", "childUserGroup", "roles", "users"})
@Entity
@Table(name = "rbac_user_group")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户组名
    private String userGroupName;

    /*
        为啥一个辅助系统要排序？？排序了给谁看？
     */
    // 同级中的顺序【如果传入为空，则先查询同级中最大的之后+1，不考虑线程安全问题】
    //private Integer order;

    // ******** 自关联开始 ********

    // 父用户组
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserGroup.class) // 查儿子不找父亲
    @JoinColumn(name = "pid")
    private UserGroup parentUserGroup;

    // 查询的时候使用 select还是join还是子查询
    //@Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = UserGroup.class,
            cascade = {CascadeType.ALL},    // 父亲的一方删除级联删除所有儿子
            mappedBy = "parentUserGroup",    // 谁来维护关联关系（一（父亲）放弃维护外键关系）
            fetch = FetchType.LAZY  // 查父亲不找儿子
    )
    private Set<UserGroup> childUserGroup;

    // ******** 自关联结束 ********

    // 与角色表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;


//    用户与用户组一对多版
//    // 一的一方（父亲）；查父亲不找儿子；父亲的一方删除级联删除所有儿子；放弃维护外键关系
//    @OneToMany(targetEntity = User.class,
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "userGroup"
//    )
//    private Set<User> users;

    // 与用户表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> users;
}
