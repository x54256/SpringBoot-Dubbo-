package com.dist.rbac.controller;

import com.dist.api.service.rbac.IPermissionService;
import com.dist.base.common.BaseController;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.PermissionDTO;
import com.dist.model.rbac.request.PermissionResourceRequest;
import com.dist.model.rbac.vo.PermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 负责权限的管理
 *
 * @author yujx
 * @date 2019/04/23 09:18
 */
@RestController
@RequestMapping("/rest/private/permission")
@Api(tags = {"RBAC-PermissionController"}, description = "权限管理接口")
public class PermissionController extends BaseController {

    @Autowired
    private IGenerator dozer;

    @Autowired
    private IPermissionService permissionService;

    @PostMapping(value = "/v1/permission/resourceFile")
    @ApiOperation(value = "为资源文件设置权限", httpMethod = "POST")
    public Result savePermission(@RequestBody PermissionResourceRequest permissionResourceRequest) {

        PermissionDTO permissionDTO = dozer.convert(permissionResourceRequest, PermissionDTO.class);

        permissionDTO = permissionService.savePermissionWithResourceFile(permissionDTO);

        return ResultUtil.success(dozer.convert(permissionDTO, PermissionVO.class));
    }


    // TODO: 2019/4/24 根据文件的层次结构返回给前端，保存一个List存放权限
    @GetMapping(value = "/v1/permission/resourceFile")
    @ApiOperation(value = "查询资源具有的所有权限", httpMethod = "GET")
    public Result getResourceFilePermission() {

        List<PermissionDTO> permissionDTOS = permissionService.getResourceFilePermission();

        return ResultUtil.success(dozer.convert(permissionDTOS, PermissionVO.class));
    }

}
