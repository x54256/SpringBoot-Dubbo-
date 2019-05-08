package com.dist.rbac.controller;

import com.dist.api.service.rbac.IRoleService;
import com.dist.base.exception.IllegalParameterException;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import com.dist.base.utils.ObjectUtil;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.RoleDTO;
import com.dist.model.rbac.request.RoleBindPermissionRequest;
import com.dist.model.rbac.request.RoleRequest;
import com.dist.model.rbac.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 负责角色的创建和角色与权限的管联
 *
 * @author yujx
 * @date 2019/04/22 15:49
 */
@RestController
@RequestMapping("/rest/private/role")
@Api(tags = {"RBAC-RoleController"}, description = "角色管理接口")
public class RoleController {

    @Autowired
    private IGenerator dozer;

    @Autowired
    private IRoleService roleService;

    @PostMapping(value = "/v1/role")
    @ApiOperation(value = "新增角色", httpMethod = "POST")
    public Result saveRole(@RequestBody RoleRequest roleRequest) {
        if (StringUtils.isBlank(roleRequest.getRoleName())) {
            throw new IllegalParameterException("请输入角色名！");
        }
        RoleDTO roleDTO = dozer.convert(roleRequest, RoleDTO.class);
        roleDTO = roleService.saveRole(roleDTO);

        return ResultUtil.success(dozer.convert(roleDTO, RoleVO.class));
    }


    @PostMapping(value = "/v1/role/permission")
    @ApiOperation(value = "给角色（主要的）新增权限（次要的）", httpMethod = "POST")
    public Result bindPermission2Role(@RequestBody RoleBindPermissionRequest roleBindPermissionRequest) {

        if (!ObjectUtil.isNonNull(roleBindPermissionRequest.getRoleId(), roleBindPermissionRequest.getPermissionIds())) {
            throw new IllegalParameterException("输入的参数有误");
        }

        roleService.bindPermission2Role(roleBindPermissionRequest.getRoleId(), roleBindPermissionRequest.getPermissionIds());
        return ResultUtil.success();
    }


}
