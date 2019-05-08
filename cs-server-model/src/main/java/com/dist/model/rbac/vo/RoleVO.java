package com.dist.model.rbac.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujx
 * @date 2019/04/19 15:26
 */
@Data
public class RoleVO implements Serializable {

    private Long id;

    // 角色名
    private String roleName;

    // 父id
    private Long pid;

}
