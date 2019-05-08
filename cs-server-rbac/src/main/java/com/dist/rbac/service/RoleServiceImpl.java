package com.dist.rbac.service;

import com.dist.api.service.rbac.IRoleService;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.RoleDTO;
import com.dist.model.rbac.entity.Role;
import com.dist.rbac.dmn.IRoleDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 16:09
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IGenerator dozer;

    @Autowired
    private IRoleDmn roleDmn;

    /**
     * 保存角色信息
     *
     * @param roleDTO
     * @return
     */
    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = roleDmn.save(dozer.convert(roleDTO, Role.class), roleDTO.getPid());

        return dozer.convert(role, RoleDTO.class);
    }

    /**
     * 绑定权限给角色
     *
     * @param roleId        角色id
     * @param permissionIds 权限ids
     */
    @Override
    public void bindPermission2Role(Long roleId, List<Long> permissionIds) {
        roleDmn.bindPermission2Role(roleId, permissionIds);
    }

}
