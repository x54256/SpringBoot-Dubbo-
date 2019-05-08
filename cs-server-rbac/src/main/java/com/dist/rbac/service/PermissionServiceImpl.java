//package com.dist.rbac.service;
//
//import com.dist.api.service.rbac.IPermissionService;
//import com.dist.base.exception.IllegalParameterException;
//import com.dist.base.exception.ObjectIsNullException;
//import com.dist.base.utils.ObjectUtil;
//import com.dist.base.utils.dozer.IGenerator;
//import com.dist.model.rbac.dto.PermissionDTO;
//import com.dist.model.rbac.entity.Operate;
//import com.dist.model.rbac.entity.Permission;
//import com.dist.model.rbac.entity.ResourceFile;
//import com.dist.rbac.constant.StatusEnum;
//import com.dist.rbac.dmn.IOperateDmn;
//import com.dist.rbac.dmn.IPermissionDmn;
//import com.dist.rbac.dmn.IResourceFileDmn;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author yujx
// * @date 2019/04/23 17:00
// */
//@Service
//public class PermissionServiceImpl implements IPermissionService {
//
//    @Autowired
//    private IGenerator dozer;
//
//    @Autowired
//    private IPermissionDmn permissionDmn;
//
//    @Autowired
//    private IResourceFileDmn resourceFileDmn;
//
//    @Autowired
//    private IOperateDmn operateDmn;
//
//    @Override
//    public PermissionDTO savePermissionWithResourceFile(PermissionDTO permissionDTO) {
//
//        Permission permission = dozer.convert(permissionDTO, Permission.class);
//
//        // 获取资源文件id，如果为空，则查询操作，判断是否是只能作用于整个资源类型的操作
//        Long resourceId = permissionDTO.getResourceId();
//
//        Long operateId = permissionDTO.getOperateId();
//        Operate operate = operateDmn.findById(operateId);
//
//        if (!ObjectUtil.isNonNull(operate)) {
//            throw new ObjectIsNullException("输入的操作id有误！");
//        }
//
//        // 只有当操作作用于整体时，资源id可以为空
//        if (resourceId == null) {
//            if (!operate.getOnlyAllOperate()) {
//                throw new IllegalParameterException("输入的操作id不能用于整体操作，请输入资源文件id！");
//            }
//        } else {
//
//            if (operate.getOnlyAllOperate()) {
//                throw new IllegalParameterException("选择的操作只可作用于整体！");
//            }
//
//            // 验证该资源是否存在
//            ResourceFile resourceFile = resourceFileDmn.findById(resourceId);
//            if (!ObjectUtil.isNonNull(resourceFile)) {
//                throw new ObjectIsNullException("输入的资源文件id有误！");
//            }
//        }
//
//        permission.setOperate(operate);
//        permission.setResourceType(StatusEnum.ResourceTypeEnum.RESOURCE_FILE.code());
//
//        permission = permissionDmn.save(permission);
//
//        return dozer.convert(permission, PermissionDTO.class);
//    }
//
//    /**
//     * 查询所有与资源文件表有关的权限
//     *
//     * @return
//     */
//    @Override
//    public List<PermissionDTO> getResourceFilePermission() {
//
//        // 查询出所有的资源文件的权限
//        List<Permission> permissions = permissionDmn.findAllPermissionByResourceType(StatusEnum.ResourceTypeEnum.RESOURCE_FILE.code());
//        return dozer.convert(permissions, PermissionDTO.class);
//    }
//}
