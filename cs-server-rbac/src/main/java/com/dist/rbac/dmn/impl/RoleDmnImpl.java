package com.dist.rbac.dmn.impl;

import com.dist.base.exception.ObjectIsNullException;
import com.dist.base.utils.ArrayUtil;
import com.dist.model.rbac.entity.Permission;
import com.dist.model.rbac.entity.Role;
import com.dist.rbac.dao.PermissionRepository;
import com.dist.rbac.dao.RoleRepository;
import com.dist.rbac.dmn.IRoleDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author yujx
 * @date 2019/04/22 16:12
 */
@Component
public class RoleDmnImpl implements IRoleDmn {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Role save(Role role, Long pid) {

        if (Objects.nonNull(pid)) {
            Optional<Role> optional = roleRepository.findById(pid);
            optional.ifPresent(role::setParentRole);
        }

        return roleRepository.save(role);
    }

    /**
     * 绑定权限给角色
     *
     * @param roleId        角色id
     * @param permissionIds 权限ids
     */
    @Override
    public void bindPermission2Role(Long roleId, List<Long> permissionIds) {

        // 1、根据id查询出角色
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (!optionalRole.isPresent()) {
            throw new ObjectIsNullException("输入的角色id有误！");
        }
        Role role = optionalRole.get();

        // 2、根据权限id查出所有的权限，添加到角色上
        Set<Permission> permissions = role.getPermissions();
        if (!ArrayUtil.isNonNullAndNonEmpty(permissions)) {
            permissions = new LinkedHashSet<>(permissionIds.size());
        }

        for (Long permissionId : permissionIds) {
            Optional<Permission> optionalPermission = permissionRepository.findById(permissionId);
            if (optionalPermission.isPresent()) {
                permissions.add(optionalPermission.get());
            } else {
                throw new ObjectIsNullException("输入的权限id有误！");
            }
        }

        role.setPermissions(permissions);

        // 3、保存角色
        roleRepository.save(role);
    }
}
