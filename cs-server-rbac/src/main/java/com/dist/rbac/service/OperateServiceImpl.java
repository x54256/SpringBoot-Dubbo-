package com.dist.rbac.service;

import com.dist.api.service.rbac.IOperateService;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.rbac.dto.OperateDTO;
import com.dist.model.rbac.entity.Operate;
import com.dist.rbac.dmn.IOperateDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yujx
 * @date 2019/04/23 14:18
 */
@Service
public class OperateServiceImpl implements IOperateService {

    @Autowired
    private IGenerator dozer;

    @Autowired
    private IOperateDmn operateDmn;

    /**
     * 保存资源的操作
     *
     * @param operateDTO
     * @return
     */
    @Override
    public OperateDTO saveOperate(OperateDTO operateDTO) {
        Operate operate = dozer.convert(operateDTO, Operate.class);
        operate = operateDmn.save(operate);

        return dozer.convert(operate, OperateDTO.class);
    }
}
