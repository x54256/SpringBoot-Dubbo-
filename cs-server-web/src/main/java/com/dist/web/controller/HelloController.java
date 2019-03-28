package com.dist.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dist.api.service.biz.HelloService;
import com.dist.base.common.BaseController;
import com.dist.base.response.Result;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.biz.dto.UserDTO;
import com.dist.model.biz.vo.UserVO;
import com.dist.web.client.ResourceClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yujx
 * @date 2019/02/13 20:20
 */
@RestController
@RequestMapping("/rest/private/test")
@Api(tags = "CS-HelloController", description = "测试专用")
public class HelloController extends BaseController {

    @Reference(version = "1.0.0")
    private HelloService helloService;

    @Autowired
    private ResourceClient resourceClient;

    @Autowired
    private IGenerator dozer;

    @GetMapping("/v1/hello")
    public List<UserVO> hello() {

        List<UserDTO> userDTOS = helloService.findAll();

        return dozer.convert(userDTOS, UserVO.class);
    }


    @GetMapping("/v1/filePreview/{resourceFileId}")
    @ApiOperation(value = "根据文件id获取预览文件url", httpMethod = "GET")
    public Result filePreview(
            @ApiParam(value = "资源文件id", defaultValue = "5c24584461d21586acf6a23c") @PathVariable(value = "resourceFileId") String resourceFileId
    ) {
        return resourceClient.filePreviewByMongoId(resourceFileId, "xxx", "jpg");
    }

}
