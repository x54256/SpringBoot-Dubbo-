package com.dist.model.rbac.vo;

import lombok.Data;

/**
 * @author yujx
 * @date 2019/04/19 18:30
 */
@Data
public class OperateVO {

    private Long id;

    // 操作名
    private String operateName;

    // 拦截url前缀
    private String interceptUrl;

    // 请求方法 @See StatusEnum.RequestMethodEnum
    private Integer requestMethod;

    // 资源类型 @see StatusEnum.ResourceTypeEnum
    private Integer resourceType;

    // 是否是只能作用于整个资源类型的操作
    private Boolean onlyAllOperate;
}
