package com.dist.model.rbac.dto;

import lombok.Data;

/**
 * 资源文件信息dto
 *
 * @author yujx
 * @date 2019/04/23 10:02
 */
@Data
public class ResourceFileDTO {

    // 资源文件名称
    private String name;

    // 文件后缀
    private String suffix;

    // 远程存储的文件路径
    private String path;
}
