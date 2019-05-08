package com.dist.model.rbac.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * rbac-功能操作表
 *
 * @author yujx
 * @date 2019/04/19 18:30
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"permissions"})
@Entity
@Table(name = "rbac_operate")
public class Operate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 是否是功能权限
    @org.hibernate.annotations.Type(type="yes_no")
    private Boolean functionalAuth;

    // 操作名
    private String operateName;

    // 资源类型 @see StatusEnum.ResourceTypeEnum
    private Integer resourceType;


    @OneToMany(targetEntity = Permission.class,
            cascade = {CascadeType.ALL},    // 父亲的一方删除级联删除所有儿子
            mappedBy = "operate",    // 谁来维护关联关系（一（父亲）放弃维护外键关系）
            fetch = FetchType.LAZY  // 查父亲不找儿子
    )
    private Set<Permission> permissions;

}
