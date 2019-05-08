package com.dist.model.rbac.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * rbac-权限表
 *
 * @note 权限表与资源表之间的关系手动维护
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"operate"})
@Entity
@Table(name = "rbac_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 权限名
    private String permissionName;

    // 与角色表的多对多关系；对中间表级联【保存、更新、删除】
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles;

    // 资源文件id【手动维护与多个表之间的关系】
    private Long resourceId;

    // 资源类型 @see StatusEnum.ResourceTypeEnum
    private Integer resourceType;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Operate.class) // 查儿子不找父亲
    @JoinColumn(name = "operateId")
    private Operate operate;


    /* 【伪代码】，父权限id ==》 用于资源具有层级的这种情况

        1. api 下的 资源文件
        2. 专题池中 目录 下的 专题
        3. 系统下的容器
     */
    private Long pid;



    // 01010 不能超过八位，否则会造成 【与或非】操作会有多个匹配项
    private Integer powerCode;

    /*

    需要A的查看和删除权限，需要B的查看操作

    1、如果采用powerCode的话（速度快，但也不会太快，建议操作小于8而且固定的时候使用）

    permission表

    id  权限名         powerCode    resourceId
    1   资源A删除操作     1           1

    2   资源A查看操作     2           1

    3   资源B删除操作     1           2

    4   资源B查看操作     2           2



    role_permission表

    id  powerSum    resourceId  roleId

    1   3           1           1

    2   2           2           1


    2、不使用的话（项目维护成本低，便于理解）

    id  permission_id   roleId

    1      1            1

    2      2            1

    3      4            1

     */

}
