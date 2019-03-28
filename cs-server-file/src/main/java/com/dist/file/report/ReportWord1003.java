package com.dist.file.report;

import com.dist.base.constant.StatusEnum;
import com.dist.base.utils.IdUtil;
import com.dist.base.utils.report.DocumentHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * word报告
 *
 * @author yinxp@dist.com.cn
 * @date 2018/11/7
 */
@Slf4j
@Service
public class ReportWord1003 implements IReport {

    private static final String templateName = "report1003.ftl";        // word模板名称
    private static final String templatePath = "/templates/";       // 模板所在目录
    private static final String fileSuffix = ".doc";                // 生成文档格式后缀

    private String fileName;    // 生成的报告文件名

    @Autowired
    @Qualifier("documentHandler")
    DocumentHandler documentHandler;


    @Override
    public Integer getModule() {
        return StatusEnum.ProjectModuleEnum.REGULATORY_ADJUSTMENT.code();
    }

    /**
     * 生成报告
     *
     * @param map           模板参数
     * @param saveFileDir 模板保存路径
     * @return 模板的预览地址
     */
    @Override
    public String generate(Map<String, Object> map, String saveFileDir) {
        Map<String, Object> dataMap = pretreatment(map);
        if (dataMap == null) {
            return null;
        }
        documentHandler.setDataMap(dataMap);
        documentHandler.setTemplatePath(templatePath);
        documentHandler.setTemplateName(templateName);
        documentHandler.setSaveFileDir(saveFileDir);
        documentHandler.setFileName(fileName + fileSuffix);
        try {
            documentHandler.createDoc();
        } catch (Exception e) {
            log.error("生成报告失败" + e);
            return "";
        }
        return documentHandler.getFileFullPath();
    }


    /**
     * 数据预处理，拼装成模板所需的数据格式
     *
     * @return
     */
    @Override
    public Map<String, Object> pretreatment(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<>();

        // 生成文件名称
        String fileName = "控规调整方案论证报告" +
                "_" +
                params.get("Time") +
                "_" +
                IdUtil.uuid6();
        this.setFileName(fileName);

        data.put("name", params.get("Name"));
        data.put("time", params.get("Time"));
        data.put("desc", params.get("Desc"));
        data.put("image", params.get("Image"));
        List<Map<String, String>> blockAttributeList = (List<Map<String, String>>) params.get("BlockAttributeParam");

        List<Map<String ,String>> attributeMapList = new ArrayList<>();
        for (Map<String, String> blockAttribute : blockAttributeList) {

            Map<String, String> map = new HashMap<>();
            map.put("dkbm", blockAttribute.get("DKBM"));
            map.put("yddm", blockAttribute.get("YDDM"));
            map.put("ydxz", blockAttribute.get("YDXZ"));
            map.put("ydmj", blockAttribute.get("YDMJ"));
            map.put("jzgd", blockAttribute.get("JZGD"));
            map.put("jzmd", blockAttribute.get("JZMD"));
            map.put("rjv", blockAttribute.get("RJV"));
            map.put("ldl", blockAttribute.get("LDL"));
            map.put("ptss", blockAttribute.get("PTSS"));
            map.put("crkfx", blockAttribute.get("CRKFX"));
            map.put("tcbw", blockAttribute.get("TCBW"));
            map.put("bz", blockAttribute.get("BZ"));

            attributeMapList.add(map);
        }
        data.put("blockAttributeList", attributeMapList);

        return data;
    }

    /**
     * 设置
     * @param fileName
     */
    private void setFileName(String fileName) {
        if (null ==  fileName) {
            fileName = IdUtil.uuid32();
        }
        this.fileName = fileName;
    }

}
