package com.dist.rbac.dmn.impl;

import com.dist.model.rbac.entity.Permission;
import com.dist.rbac.dao.PermissionRepository;
import com.dist.rbac.dmn.IPermissionDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/23 17:06
 */
@Component
public class PermissionDmnImpl implements IPermissionDmn {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> findAllPermissionByResourceType(Integer resourceType) {
        return permissionRepository.findAllByResourceTypeOrderByResourceId(resourceType);
    }
}
