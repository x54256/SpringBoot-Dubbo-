package com.dist.rbac.controller;

import com.dist.api.service.rbac.IUserManageService;
import com.dist.base.exception.IllegalParameterException;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.UserDTO;
import com.dist.model.rbac.dto.UserGroupDTO;
import com.dist.model.rbac.request.UserGroupRequest;
import com.dist.model.rbac.request.UserRequest;
import com.dist.model.rbac.vo.UserGroupVO;
import com.dist.model.rbac.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理用户和用户组，为用户用户组添加角色
 *
 * @author yujx
 * @date 2019/04/22 13:39
 */
@RestController
@RequestMapping("/rest/private/userManage")
@Api(tags = {"RBAC-UserManageController"}, description = "用户管理接口")
public class UserManageController {

    @Autowired
    private IUserManageService userManageService;

    @Autowired
    private IGenerator dozer;


    @PostMapping(value = "/v1/user")
    @ApiOperation(value = "新增用户", httpMethod = "POST")
    public Result addUser(@RequestBody UserRequest userRequest) {

        if (!StringUtils.isAllBlank(userRequest.getUserName(), userRequest.getPassWord())) {
            UserDTO userDTO = dozer.convert(userRequest, UserDTO.class);
            userDTO = userManageService.saveUser(userDTO);
            return ResultUtil.success(dozer.convert(userDTO, UserVO.class));
        }

        throw new IllegalParameterException("输入的用户名密码不能为空！");
    }


    @GetMapping(value = "/v1/user/list")
    @ApiOperation(value = "查询所有用户（理论是分页查询），角色和用户组全部都给", httpMethod = "GET")
    public Result listAllUser() {

        return ResultUtil.success(userManageService.listAllUser());
    }


    @PostMapping(value = "/v1/userGroup")
    @ApiOperation(value = "新增用户组", httpMethod = "POST")
    public Result addUserGroup(@RequestBody UserGroupRequest userGroupRequest) {
        if (StringUtils.isBlank(userGroupRequest.getUserGroupName())) {
            throw new IllegalParameterException("输入的用户组名不能为空！");
        }

        UserGroupDTO userGroupDTO = dozer.convert(userGroupRequest, UserGroupDTO.class);
        userGroupDTO = userManageService.saveUserGroup(userGroupDTO);

        return ResultUtil.success(dozer.convert(userGroupDTO, UserGroupVO.class));
    }


    @GetMapping(value = "/v1/userGroup/list")
    @ApiOperation(value = "查询所有用户组", httpMethod = "GET")
    public Result listAllUserGroup() {

        return ResultUtil.success(userManageService.listAllUserGroup());
    }

    // 删除用户组有很多级联操作

}
