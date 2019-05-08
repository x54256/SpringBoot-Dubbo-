package com.dist.rbac.dao;

import com.dist.model.rbac.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author yujx
 * @date 2019/04/22 14:48
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    List<Permission> findAllByResourceTypeOrderByResourceId(Integer resourceType);
}
