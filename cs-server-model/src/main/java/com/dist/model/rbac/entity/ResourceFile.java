package com.dist.model.rbac.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * rbac-资源文件表
 *
 * @author yujx
 * @date 2019/04/23 10:02
 */
@Data
@Entity
@Table(name = "rbac_resource_file")
public class ResourceFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 资源文件名称
    private String name;

    // 文件后缀
    private String suffix;

    // 远程存储的文件路径
    private String path;

    // 【伪代码】域的概念，每个资源具有自己的唯一的域
    private Long domainId;
}
