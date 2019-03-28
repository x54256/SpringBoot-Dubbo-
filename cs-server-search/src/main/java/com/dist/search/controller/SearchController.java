package com.dist.search.controller;

import com.dist.base.common.BaseController;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import com.dist.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yujx
 * @date 2019/03/20 10:01
 */
@RestController
@RequestMapping("/rest/private/search")
@Api(tags = "SearchController", description = "搜索服务")
public class SearchController extends BaseController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/v1/content/index/create")
    @ApiOperation(value = "根据文件mongoId，创建文件内容的索引", httpMethod = "GET")
    public Result createFileIndex(
            @ApiParam(value = "Mongo上文件的存储标识符", required = true) @RequestParam(value = "mongoId") String mongoId,
            @ApiParam(value = "审查任务id", required = true) @RequestParam(value = "reviewTaskId") Long reviewTaskId
    ) {
        searchService.createFileIndex(mongoId, reviewTaskId);
        return ResultUtil.success();
    }

}
