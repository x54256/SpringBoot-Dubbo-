package com.dist.model.rbac.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Data
public class UserVO implements Serializable {

    private Long id;

    // 用户名
    private String userName;

    // 密码
    private String passWord;

    // 手机号
    private String mobilePhone;

    // 邮箱
    private String email;

}
