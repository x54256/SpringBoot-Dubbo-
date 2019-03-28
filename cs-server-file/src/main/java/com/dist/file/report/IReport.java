package com.dist.file.report;

import java.util.Map;

/**
 * 报告导出
 *
 * @author yinxp@dist.com.cn
 * @date 2018/11/7
 */
public interface IReport {

    Integer getModule();

    /**
     * 生成报告
     *
     * @param map         模板参数
     * @param saveFileDir 模板保存路径
     * @return 模板的预览地址
     */
    String generate(Map<String, Object> map, String saveFileDir);

    /**
     * 数据预处理，拼装成模板所需的数据格式
     *
     * @param params
     * @return
     */
    Map<String, Object> pretreatment(Map<String, Object> params);
}
