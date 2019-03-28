package com.dist.file.report;


import com.dist.base.constant.StatusEnum;
import com.dist.base.utils.DateUtil;
import com.dist.base.utils.IdUtil;
import com.dist.base.utils.report.DocumentHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * word报告
 * @author yinxp@dist.com.cn
 * @date 2018/11/7
 */
@Slf4j
@Service
public class ReportWord1004 implements IReport {

    private static final String templateName = "report1004.ftl";        // word模板名称
    private static final String templatePath = "/templates/";       // 模板所在目录
    private static final String fileSuffix = ".doc";                // 生成文档格式后缀

    private String fileName;    // 生成的报告文件名

    @Autowired
    @Qualifier("documentHandler")
    DocumentHandler documentHandler;


    @Override
    public Integer getModule() {
        return StatusEnum.ProjectModuleEnum.KEY_POINTS_OF_PLANNING.code();
    }

    /**
     *  生成报告
     * @param map   模板参数
     * @param saveFileDir    模板保存路径
     * @return  模板的预览地址
     */
    @Override
    public String generate(Map<String, Object> map,String saveFileDir) {
        Map<String,Object> dataMap = pretreatment(map);
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
            log.error("生成报告失败"+e);
            return "";
        }
        return documentHandler.getFileFullPath();
    }


    /**
     * 数据预处理，拼装成模板所需的数据格式
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> pretreatment(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<>();
        String createTime = DateUtil.nowDateStrByFormat();
        // 生成文件名称
        StringBuilder fileName = new StringBuilder();
        fileName.append("规划设计要求");
        fileName.append("_");
        fileName.append(createTime);
        fileName.append("_");
        fileName.append(IdUtil.uuid6());
        this.setFileName(fileName.toString());
        try {
            boolean hasKG = params.get("KGParam") == null ? false : ((List)params.get("KGParam")).isEmpty()?false:true;
            boolean hasCS = params.get("CSParam") == null ? false : ((List)params.get("CSParam")).isEmpty()?false:true;
            data.put("createTime",createTime);
            data.put("image",(String)params.get("image"));
            data.put("hasKG",hasKG);
            data.put("KGParam",params.get("KGParam"));
            data.put("hasCS",hasCS);
            data.put("CSParam",params.get("CSParam"));
        } catch (Exception e) {
            log.error("pretreatment error："+e);
            return null;
        }

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
