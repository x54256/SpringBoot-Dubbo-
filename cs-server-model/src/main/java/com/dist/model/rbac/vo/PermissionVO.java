package com.dist.model.rbac.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujx
 * @note 权限表与资源表之间的关系手动维护
 * @date 2019/04/19 15:16
 */
@Data
public class PermissionVO implements Serializable {

    private Long id;

    // 权限名
    private String permissionName;

    // 资源文件id【手动维护与多个表之间的关系】
    private Long resourceId;

    // 资源类型 @see StatusEnum.ResourceTypeEnum
    private Integer resourceType;

}
