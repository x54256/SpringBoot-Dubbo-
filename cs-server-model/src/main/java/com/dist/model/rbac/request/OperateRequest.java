package com.dist.model.rbac.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * 保存资源的操作
 *
 * @author yujx
 * @date 2019/04/19 18:30
 */
@Data
@Validated
@ApiModel(value = "保存对资源的操作")
public class OperateRequest {

    @NotNull
    @ApiModelProperty(value = "操作名", required = true)
    private String operateName;

    @NotNull
    @ApiModelProperty(value = "拦截的url前缀", required = true)
    private String interceptUrl;

    // @See StatusEnum.RequestMethodEnum
    @NotNull
    @ApiModelProperty(value = "请求方法", required = true)
    private Integer requestMethod;

    // @see StatusEnum.ResourceTypeEnum
    @NotNull
    @ApiModelProperty(value = "资源类型，与前端约定好", required = true)
    private Integer resourceType;

    @NotNull
    @ApiModelProperty(value = "是否是只能作用于整个资源类型的操作", required = true)
    private Boolean onlyAllOperate;
}
