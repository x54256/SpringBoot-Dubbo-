package com.dist.api.service.rbac;

import com.dist.model.rbac.dto.RoleDTO;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 16:09
 */
public interface IRoleService {

    /**
     * 保存角色信息
     *
     * @param roleDTO
     * @return
     */
    RoleDTO saveRole(RoleDTO roleDTO);

    /**
     * 绑定权限给角色
     *
     * @param roleId 角色id
     * @param permissionIds 权限ids
     */
    void bindPermission2Role(Long roleId, List<Long> permissionIds);
}
