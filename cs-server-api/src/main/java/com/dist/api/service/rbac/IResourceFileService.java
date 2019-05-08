package com.dist.api.service.rbac;

import com.dist.model.rbac.dto.ResourceFileDTO;

/**
 * @author yujx
 * @date 2019/04/23 10:49
 */
public interface IResourceFileService {

    /**
     * 保存资源文件
     *
     * @param resourceFileDTO
     */
    void saveResourceFile(ResourceFileDTO resourceFileDTO);
}
