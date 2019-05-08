package com.dist.model.rbac.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujx
 * @date 2019/04/24 14:29
 */
public class ResourceFilePermissionDTO implements Serializable {


    private String fileName;

    private List<PermissionDTO> permissionDTOS;

}
