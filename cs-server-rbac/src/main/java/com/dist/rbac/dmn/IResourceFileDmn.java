package com.dist.rbac.dmn;

import com.dist.model.rbac.entity.ResourceFile;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/23 10:50
 */
public interface IResourceFileDmn {
    ResourceFile save(ResourceFile resourceFile);

    ResourceFile findById(Long resourceId);

    /**
     * 寻找已经分配操作的资源文件
     *
     * @return
     */
    List<ResourceFile> findHasPermission();
}
