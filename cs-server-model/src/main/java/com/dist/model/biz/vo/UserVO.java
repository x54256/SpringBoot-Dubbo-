package com.dist.model.biz.vo;

import java.io.Serializable;

/**
 * @author yujx
 * @date 2019/02/14 09:43
 */
public class UserVO implements Serializable {

    private String lastName;
    private String email;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
