package com.dist.rbac.dmn;

import com.dist.model.rbac.entity.Permission;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/23 17:06
 */
public interface IPermissionDmn {
    Permission save(Permission permission);

    List<Permission> findAllPermissionByResourceType(Integer code);
}
