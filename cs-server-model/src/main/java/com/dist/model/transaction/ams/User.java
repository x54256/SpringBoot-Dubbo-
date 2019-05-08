package com.dist.model.transaction.ams;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 告诉JPA这是一个实体类（对应数据表），不是普通的javabean
@Table(name = "ams_tbl_user")   // 不写这个注解，默认为这个类的小写作为名字
public class User {
 
    @Id // 标识这是主键
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 根据数据库自动选则主键自增类型
    private Integer id;
 
    @Column(name="lastname",length = 50)    // 标识这个列的列名和属性
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