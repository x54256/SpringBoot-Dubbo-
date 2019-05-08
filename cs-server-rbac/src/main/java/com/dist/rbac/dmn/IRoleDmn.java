package com.dist.rbac.dmn;

import com.dist.model.rbac.entity.Role;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 16:12
 */
public interface IRoleDmn {
    Role save(Role role, Long pid);

    /**
     * 绑定权限给角色
     *
     * @param roleId        角色id
     * @param permissionIds 权限ids
     */
    void bindPermission2Role(Long roleId, List<Long> permissionIds);
}
