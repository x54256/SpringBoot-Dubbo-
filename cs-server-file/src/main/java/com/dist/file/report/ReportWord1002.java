package com.dist.file.report;


import com.dist.base.constant.StatusEnum;
import com.dist.base.utils.IdUtil;
import com.dist.base.utils.report.DocumentHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class ReportWord1002 implements IReport {

    private static final String templateName = "report1002.ftl";        // word模板名称
    private static final String templatePath = "/templates/";       // 模板所在目录
    private static final String fileSuffix = ".doc";                // 生成文档格式后缀

    private String fileName;    // 生成的报告文件名

    @Autowired
    @Qualifier("documentHandler")
    DocumentHandler documentHandler;


    @Override
    public Integer getModule() {
        return StatusEnum.ProjectModuleEnum.BUILDING_REVIEW.code();
    }

    /**
     * 生成报告
     *
     * @param map         模板参数
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
        String fileName = "建设方案审查报告" +
                "_" +
                params.get("Time") +
                "_" +
                IdUtil.uuid6();
        this.setFileName(fileName);


        data.put("name", params.get("Name"));
        data.put("time", params.get("Time"));
        data.put("image", params.get("Image"));
        data.put("reviewOpinions", params.get("ReviewOpinion"));

        List<Map<String, Object>> reviewResults = (List<Map<String, Object>>) params.get("ReviewResults");

        List<String> reviewResultIdList = new ArrayList<>();

        for (Map<String, Object> reviewResult : reviewResults) {
            reviewResultIdList.add(reviewResult.get("ReviewResultID").toString());
        }

        String reviewResultIds = StringUtils.join(reviewResultIdList, "，");

        data.put("reviewResultIds", reviewResultIds);

        List<Map<String, Object>> list = new ArrayList<>();

        for (Map<String, Object> reviewResult : reviewResults) {
            List<Map<String, Object>> reviewResultList = (List<Map<String, Object>>) reviewResult.get("ReviewResult");

            for (Map<String, Object> reviewResult1 : reviewResultList) {
                Map<String, Object> map = new HashMap<>();
                map.put("indicatorId", reviewResult1.get("IndicatorID"));
                map.put("indicatorName", reviewResult1.get("IndicatorName"));
                map.put("indicatorRequirement", reviewResult1.get("IndicatorRequirement"));

                Integer isOk = (Integer) reviewResult1.get("IsOk");
                String isOk1 = null;
                if (isOk.equals(1)) {
                    isOk1 = "通过";
                } else if (isOk.equals(2)) {
                    isOk1 = "柔性";
                } else if (isOk.equals(3)) {
                    isOk1 = "未审核";
                } else if (isOk.equals(0)) {
                    isOk1 = "不通过";
                }

                map.put("isOk", isOk1);
                String reviewAdvise = (String) reviewResult1.get("ReviewAdvise");
                if (reviewAdvise != null) {
                    reviewAdvise = "审查意见：" + reviewAdvise;
                }
                map.put("reviewAdvise", reviewAdvise);

                list.add(map);
            }
        }

        data.put("reviewResults", list);

        return data;
    }

    /**
     * 设置
     *
     * @param fileName
     */
    private void setFileName(String fileName) {
        if (null == fileName) {
            fileName = IdUtil.uuid32();
        }
        this.fileName = fileName;
    }

}
