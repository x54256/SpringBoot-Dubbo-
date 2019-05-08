package com.dist.model.biz.entity;

import javax.persistence.*;

@Entity // 告诉JPA这是一个实体类（对应数据表），不是普通的javabean
@Table(name = "tb_user", uniqueConstraints = @UniqueConstraint(columnNames = {"LASTNAME", "EMAIL"}))   // 不写这个注解，默认为这个类的小写作为名字
public class User {
 
    @Id // 标识这是主键
//    @GeneratedValue(strategy = GenerationType.AUTO) // 根据数据库自动选则主键自增类型
    private Integer id;

    private String lastName;
    private String email;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
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
}