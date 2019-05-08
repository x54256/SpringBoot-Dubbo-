package com.dist.model.biz.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 临时性项目基本信息
 * @author yinxp@dist.com.cn
 * @date 2019/4/4
 */
@Document(collection = "project_doc")
public class ProjectDoc implements Serializable{

    @Id
    private String guid;

    // 项目代码
    private String projectCode;

    // 项目名称
    private String projectName;

    // 区域代码
    private String regionCode;

    // 行政区名称
    private String regionName;

    // 成果类型:国土空间规划、专项规划、详细规划
    private String planType;

    // 委托单位
    private String organizationalUnit;

    // 编制单位
    private String compilationUnit;

    // 成果版本:大纲阶段、成果阶段、报批阶段
    private String version;

    // 规划起始年
    private String startYear;

    // 规划目标年
    private String targetYear;

    // 规划期限
    private String planTerm;

    // 规划人口
    private String population;

    // 规划面积
    private String planArea;

    // 规划范围
    private String planRange;

    // 批复部门
    private String approvalDepartment;

    // 批复时间
    private String approvalDate;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public String getCompilationUnit() {
        return compilationUnit;
    }

    public void setCompilationUnit(String compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(String targetYear) {
        this.targetYear = targetYear;
    }

    public String getPlanTerm() {
        return planTerm;
    }

    public void setPlanTerm(String planTerm) {
        this.planTerm = planTerm;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getPlanArea() {
        return planArea;
    }

    public void setPlanArea(String planArea) {
        this.planArea = planArea;
    }

    public String getPlanRange() {
        return planRange;
    }

    public void setPlanRange(String planRange) {
        this.planRange = planRange;
    }

    public String getApprovalDepartment() {
        return approvalDepartment;
    }

    public void setApprovalDepartment(String approvalDepartment) {
        this.approvalDepartment = approvalDepartment;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }
}
