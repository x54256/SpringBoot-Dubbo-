package com.dist.rbac.dmn.impl;

import com.dist.model.rbac.entity.ResourceFile;
import com.dist.rbac.dao.ResourceFileRepository;
import com.dist.rbac.dmn.IResourceFileDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/23 10:51
 */
@Component
public class ResourceFileDmnImpl implements IResourceFileDmn {

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    @Override
    public ResourceFile save(ResourceFile resourceFile) {
        return resourceFileRepository.save(resourceFile);
    }

    @Override
    public ResourceFile findById(Long resourceId) {
        return resourceFileRepository.findById(resourceId).orElse(null);
    }

    /**
     * 寻找已经分配操作的资源文件
     *
     * @return
     */
    @Override
    public List<ResourceFile> findHasPermission() {
        return resourceFileRepository.findHasPermission();
    }
}
