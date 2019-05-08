package com.dist.model.rbac.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yujx
 * @date 2019/04/22 15:02
 */
@Data
@ApiModel(value = "添加用户组")
public class UserGroupRequest {

    @ApiModelProperty(value = "用户组名", required = true)
    private String userGroupName;

    @ApiModelProperty(value = "父用户组id")
    private Long pid;

}
