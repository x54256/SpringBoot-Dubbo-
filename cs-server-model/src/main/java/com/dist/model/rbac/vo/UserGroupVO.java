package com.dist.model.rbac.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Data
public class UserGroupVO implements Serializable {

    private Long id;

    // 用户组名
    private String userGroupName;

    // 父id
    private Long pid;
}
