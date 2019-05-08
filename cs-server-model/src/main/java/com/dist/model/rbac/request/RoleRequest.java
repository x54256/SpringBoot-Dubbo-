package com.dist.model.rbac.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yujx
 * @date 2019/04/19 15:26
 */
@Data
@ApiModel(value = "添加角色")
public class RoleRequest {

    @ApiModelProperty(value = "角色名", required = true)
    private String roleName;

    @ApiModelProperty(value = "父角色id")
    private Long pid;

}
