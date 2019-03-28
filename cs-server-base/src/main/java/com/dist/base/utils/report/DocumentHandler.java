package com.dist.base.utils.report;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文档处理
 *
 * @author yinxp@dist.com.cn
 * @date 2018/11/7
 */
public class DocumentHandler {

    private Configuration configuration = null;
    private String encoding = "utf-8";
    private String templatePath;        // 模板存放路径
    private String templateName;        // 模板名称
    private Map<String, Object> dataMap;        // 模板参数
    private String saveFileDir = "";            // 报告生成之后保存的位置
    private String fileName;                    // 报告生成之后的文件名
    private String fileFullPath;        // 文件的全路径

    public DocumentHandler() {
        configuration = new Configuration();
    }

    /**
     * 生成文档
     *
     * @param saveFileDir
     * @param fileName
     * @param encoding
     * @param templatePath
     * @param templateName
     * @param dataMap
     */
    public void createDoc(String saveFileDir, String fileName, String encoding, String templatePath, String templateName, Map<String, Object> dataMap) {

        initParas(saveFileDir, fileName, encoding, templatePath, templateName, dataMap);
        this.createDoc();

    }

    /**
     * 生成文档
     */
    public void createDoc() {

        configuration.setDefaultEncoding(this.encoding);
        //设置模本装置方法和路径FreeMarker支持多种模板装载方法。可以从servlet，classpath，数据库装载
        configuration.setClassForTemplateLoading(this.getClass(), this.templatePath);

        //要填入模本的数据文件
        if (null == dataMap) {
            System.out.println(("警告，模板数据源为空。"));
            dataMap = new HashMap<String, Object>();
        }

        try {
            // 装载的模板名称
            Template t = configuration.getTemplate(this.templateName);

            //输出文档路径
            File outDir = new File(this.saveFileDir);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            File outFile = new File(outDir + "/" + this.fileName);

            Writer out = null;

            // 此编码必须得跟模板的编码是一致的，否则打开生成的word，提示xml非法字符
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), this.encoding));

            t.process(dataMap, out);
            if (outFile.exists()) {
                this.fileFullPath = outFile.getAbsolutePath();
            } else {
                this.fileFullPath = "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置配置
     *
     * @param saveFileDir
     * @param fileName
     * @param encoding
     * @param templatePath
     * @param templateName
     * @param dataMap
     */
    private void initParas(String saveFileDir, String fileName,
                           String encoding, String templatePath, String templateName,
                           Map<String, Object> dataMap) {

        this.saveFileDir = saveFileDir;
        this.fileName = fileName;
        this.encoding = encoding;
        this.templatePath = templatePath;
        this.templateName = templateName;
        this.dataMap = dataMap;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public String getSaveFileDir() {
        return saveFileDir;
    }

    public void setSaveFileDir(String saveFileDir) {
        this.saveFileDir = saveFileDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }
}
