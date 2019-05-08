package com.dist.model.rbac.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 17:24
 */
@Data
@ApiModel(value = "为角色添加权限")
public class RoleBindPermissionRequest {

    @ApiModelProperty(value = "角色id", required = true)
    private Long roleId;

    @ApiModelProperty(value = "权限ids", required = true)
    private List<Long> permissionIds;
}
