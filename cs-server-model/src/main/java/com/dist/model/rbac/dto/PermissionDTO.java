package com.dist.model.rbac.dto;

import com.dist.model.rbac.entity.Operate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @note 权限表与资源表之间的关系手动维护
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"roles"})
public class PermissionDTO implements Serializable {

    private Long id;

    // 权限名
    private String permissionName;

    // 与角色表的多对多关系；对中间表级联【保存、更新、删除】
    private Set<RoleDTO> roles;

    // 资源文件id【手动维护与多个表之间的关系】
    private Long resourceId;

    // 资源类型 @see StatusEnum.ResourceTypeEnum
    private Integer resourceType;

    private Operate operate;

    private Long operateId;
}
