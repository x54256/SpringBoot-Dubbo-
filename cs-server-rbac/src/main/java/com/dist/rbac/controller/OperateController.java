package com.dist.rbac.controller;

import com.dist.api.service.rbac.IOperateService;
import com.dist.base.exception.IllegalParameterException;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.OperateDTO;
import com.dist.model.rbac.request.OperateRequest;
import com.dist.model.rbac.vo.OperateVO;
import com.dist.rbac.constant.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理对资源的操作
 *
 * @author yujx
 * @date 2019/04/23 13:55
 */
@RestController
@RequestMapping("/rest/private/operate")
@Api(tags = {"RBAC-OperateController"}, description = "资源操作管理接口")
public class OperateController {

    @Autowired
    private IGenerator dozer;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private IOperateService operateService;

    @PostMapping(value = "/v1/operate/resource")
    @ApiOperation(value = "为资源添加操作", httpMethod = "POST")
    public Result saveOperate(@RequestBody OperateRequest operateRequest) {

//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
//        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
//            PatternsRequestCondition prc = rmi.getPatternsCondition();
//            Set<RequestMethod> methods = rmi.getMethodsCondition().getMethods();
//
//            for (RequestMethod method : methods) {
//            }
//
//            Set<String> patterns = prc.getPatterns();
//
//            patterns.forEach(System.out::println);
//        }

        // 验证枚举值
        if (!StatusEnum.ResourceTypeEnum.checkCode(operateRequest.getResourceType())) {
            throw new IllegalParameterException("传入的参数有误！");
        }

        // 验证uri
        // 获取所有@RequestMapping中的uri
        List<String> uriList = requestMappingHandlerMapping.getHandlerMethods().keySet().stream()
                .map(RequestMappingInfo::getPatternsCondition)
                .map(AbstractRequestCondition::toString)
                .map(x -> x.substring(1, x.length() - 1))
                .collect(Collectors.toList());


        // 判断传入的uri是否在Mapping中
        if (!uriList.contains(operateRequest.getInterceptUrl())) {
            throw new IllegalParameterException("输入的uri不正确！");
        }

        OperateDTO operateDTO = dozer.convert(operateRequest, OperateDTO.class);
        operateDTO = operateService.saveOperate(operateDTO);

        return ResultUtil.success(dozer.convert(operateDTO, OperateVO.class));
    }

}
