package com.dist.model.rbac.entity;

/**
 * 资源所有人表，上传资源的人 具有所有的权限
 *
 * @author yujx
 * @date 2019/04/28 15:32
 */
public class Owner {

    private Long id;

    private Long resourceId;

    private Long userId;

    private Integer resourceType;

}
