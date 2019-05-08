package com.dist.api.service.rbac;

import com.dist.model.rbac.dto.OperateDTO;

/**
 * @author yujx
 * @date 2019/04/23 14:08
 */
public interface IOperateService {

    /**
     * 保存资源的操作
     *
     * @param operateDTO
     * @return
     */
    OperateDTO saveOperate(OperateDTO operateDTO);
}
