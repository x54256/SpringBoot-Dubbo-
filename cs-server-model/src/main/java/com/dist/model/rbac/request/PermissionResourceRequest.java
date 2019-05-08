package com.dist.model.rbac.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author yujx
 * @date 2019/04/23 10:10
 */
@Data
@Validated
@ApiModel(value = "为资源添加权限，资源文件+操作")
public class PermissionResourceRequest {

    @ApiModelProperty(value = "资源文件id，仅在对所有文件的控制时可以为空")
    private Long resourceId;

    @NotNull
    @ApiModelProperty(value = "权限名", required = true)
    private String permissionName;

    @NotNull
    @ApiModelProperty(value = "操作id", required = true)
    private Long operateId;

}
