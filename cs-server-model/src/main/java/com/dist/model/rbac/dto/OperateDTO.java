package com.dist.model.rbac.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author yujx
 * @date 2019/04/19 18:30
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"permissions"})
public class OperateDTO {

    private Long id;

    // 操作名
    private String operateName;

    // 拦截url前缀
    private String interceptUrl;

    // @See StatusEnum.RequestMethodEnum
    private Integer requestMethod;

    // 资源类型 @see StatusEnum.ResourceTypeEnum
    private Integer resourceType;

    // 拥有这个操作的权限
    private Set<PermissionDTO> permissions;

    // 是否是只能作用于整个资源类型的操作
    private Boolean onlyAllOperate;

}
