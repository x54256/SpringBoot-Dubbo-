package com.dist.base.constant;

import java.util.*;

/**
 * 全局常量
 *
 * @author yujx
 * @date 2019/02/21 19:22
 */
public abstract class GlobalConstant {

    // 需要转换成pdf的文件格式
    public static final List<String> NEED_2_CONVERT_PDF;

    // 可以直接预览的文件格式
    public static final List<String> CAN_PREVIEW_DIRECTLY;


    static {
        NEED_2_CONVERT_PDF = Arrays.asList("doc", "docx", "ppt", "pptx", "xls", "xlsx", "xml");

        CAN_PREVIEW_DIRECTLY = Arrays.asList("png", "jpg", "jpeg", "pdf");
    }

}
