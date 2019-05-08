package com.dist.model.rbac.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * rbac-专题资源表
 *
 * @author yujx
 * @date 2019/04/19 15:16
 */
@Data
@Entity
@Table(name = "rbac_topic_resource")
public class TopicResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 资源文件a的名字
    private String resourceAName;
}
