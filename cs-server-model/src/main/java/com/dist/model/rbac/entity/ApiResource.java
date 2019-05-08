package com.dist.model.rbac.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yujx
 * @date 2019/04/25 10:30
 */
@Data
@Entity
@Table(name = "rbac_api_resource")
public class ApiResource {

    @Id
    private Long id;

    // 拦截url前缀
    private String interceptUrl;

    // 请求方法 @See StatusEnum.RequestMethodEnum
    private Integer requestMethod;

    // 权限等级，1为通用接口权限，2为需校验接口权限
    private Integer apiLevel;
}
