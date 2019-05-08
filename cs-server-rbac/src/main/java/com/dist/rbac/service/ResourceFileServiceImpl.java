package com.dist.rbac.service;

import com.dist.api.service.rbac.IResourceFileService;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.ResourceFileDTO;
import com.dist.model.rbac.entity.ResourceFile;
import com.dist.rbac.dmn.IResourceFileDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yujx
 * @date 2019/04/23 10:49
 */
@Service
public class ResourceFileServiceImpl implements IResourceFileService {

    @Autowired
    private IGenerator dozer;

    @Autowired
    private IResourceFileDmn resourceFileDmn;

    /**
     * 保存资源文件
     *
     * @param resourceFileDTO
     */
    @Override
    public void saveResourceFile(ResourceFileDTO resourceFileDTO) {
        ResourceFile resourceFile = dozer.convert(resourceFileDTO, ResourceFile.class);
        resourceFileDmn.save(resourceFile);
    }
}
