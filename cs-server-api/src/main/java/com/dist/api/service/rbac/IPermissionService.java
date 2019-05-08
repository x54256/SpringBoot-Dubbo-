package com.dist.api.service.rbac;

import com.dist.model.rbac.dto.PermissionDTO;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/23 16:52
 */
public interface IPermissionService {
    PermissionDTO savePermissionWithResourceFile(PermissionDTO permissionDTO);

    /**
     * 查询所有与资源文件表有关的权限
     *
     * @return
     */
    List<PermissionDTO> getResourceFilePermission();
}
