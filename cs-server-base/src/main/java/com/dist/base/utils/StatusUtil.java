package com.dist.base.utils;


import com.dist.base.constant.IBaseEnum;
import com.dist.base.constant.StatusEnum;

/**
 * 状态枚举校验工具
 *
 * @author yinxp@dist.com.cn
 * @date 2018/12/28
 */
public abstract class StatusUtil {

    /**
     * 校验module参数是否合法
     *
     * @param module
     * @return
     */
    public static boolean legalModule(Integer module) {
        if (null == module) {
            return false;
        }
        boolean flag = false;
        for (IBaseEnum moduleEnum : StatusEnum.ProjectModuleEnum.values()) {
            if (moduleEnum.code().equals(module)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

}
