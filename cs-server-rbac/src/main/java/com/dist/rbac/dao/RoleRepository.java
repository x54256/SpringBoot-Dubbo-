package com.dist.rbac.dao;

import com.dist.model.rbac.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author yujx
 * @date 2019/04/22 14:48
 */
public interface RoleRepository extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role> {
}
