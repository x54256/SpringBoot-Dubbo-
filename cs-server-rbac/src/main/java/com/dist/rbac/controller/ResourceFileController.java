package com.dist.rbac.controller;

import com.dist.api.service.rbac.IResourceFileService;
import com.dist.base.exception.IllegalParameterException;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import com.dist.base.utils.FileUtil;
import com.dist.model.rbac.dto.ResourceFileDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 管理资源文件【运维和应用端共用】
 *
 * @author yujx
 * @date 2019/04/23 10:07
 */
@RestController
@RequestMapping("/rest/protected/resourceFile")
@Api(tags = {"RBAC-ResourceFileController"}, description = "资源文件管理接口【运维和应用端共用】")
public class ResourceFileController {

    @Value("${local_temp_path}")
    private String localTempPath;

    @Autowired
    private IResourceFileService resourceFileService;

    // TODO: 2019/4/23 我怎么知道它是运维还是应用端（通过用户session）

    /**
     * 如果是应用端，那么上传的人有这个文件的所有权限
     *
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/v1/resourceFile")
    @ApiOperation(value = "上传文件", httpMethod = "POST")
    public Result saveResourceFile(@ApiParam(value = "资源文件") @RequestParam("file") MultipartFile multipartFile) {

        // 校验文件类型
        if (multipartFile.isEmpty()) {
            throw new IllegalParameterException("上传文件类型不合法");
        }

        String originalFilename = multipartFile.getOriginalFilename();
        try (InputStream inputStream = multipartFile.getInputStream()) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 上传到mongo中
        String path = "123";

        // 获取文件名和后缀
        String fileName = FileUtil.getFileName(originalFilename);
        String fileSuffix = FileUtil.getFileSuffix(originalFilename);

        ResourceFileDTO resourceFileDTO = new ResourceFileDTO();
        resourceFileDTO.setName(fileName);
        resourceFileDTO.setSuffix(fileSuffix);
        resourceFileDTO.setPath(path);

        resourceFileService.saveResourceFile(resourceFileDTO);

        return ResultUtil.success("资源文件上传成功！");
    }

}
