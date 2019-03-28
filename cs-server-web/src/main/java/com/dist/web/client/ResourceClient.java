package com.dist.web.client;

import com.dist.api.service.feign.file.ResourceApi;
import com.dist.base.utils.feign.FeignClient;

/**
 * 文件资源客户端
 *
 * @author yujx
 * @date 2019/03/27 17:06
 */
@FeignClient(value = "${feign.file}")
public interface ResourceClient extends ResourceApi {
}
