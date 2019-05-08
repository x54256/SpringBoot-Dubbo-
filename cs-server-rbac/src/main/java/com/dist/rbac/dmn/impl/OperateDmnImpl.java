package com.dist.rbac.dmn.impl;

import com.dist.model.rbac.entity.Operate;
import com.dist.rbac.dao.OperateRepository;
import com.dist.rbac.dmn.IOperateDmn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yujx
 * @date 2019/04/23 14:20
 */
@Component
public class OperateDmnImpl implements IOperateDmn {

    @Autowired
    private OperateRepository operateRepository;

    @Override
    public Operate save(Operate operate) {
        return operateRepository.save(operate);
    }

    @Override
    public Operate findById(Long operateId) {
        return operateRepository.findById(operateId).orElse(null);
    }
}
