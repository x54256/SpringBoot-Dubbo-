package com.dist.rbac.dao;

import com.dist.model.rbac.entity.ResourceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 14:48
 */
public interface ResourceFileRepository extends JpaRepository<ResourceFile, Long>, JpaSpecificationExecutor<ResourceFile> {


    @Query("select r from ResourceFile r, Permission p where r.id = p.resourceId")
    List<ResourceFile> findHasPermission();
}
