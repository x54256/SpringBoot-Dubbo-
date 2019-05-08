package com.dist.rbac.dmn;

import com.dist.model.rbac.entity.Operate; /**
 * @author yujx
 * @date 2019/04/23 14:19
 */
public interface IOperateDmn {
    Operate save(Operate operate);

    Operate findById(Long operateId);
}
