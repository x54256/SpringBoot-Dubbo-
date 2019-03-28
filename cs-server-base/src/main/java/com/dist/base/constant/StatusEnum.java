package com.dist.base.constant;


/**
 * 状态枚举
 *
 * @author yinxp@dist.com.cn
 */
public abstract class StatusEnum {

    // 是否有效
    public enum EffectiveEnum implements IBaseEnum {
        INVALID(0, "无效"),
        EFFECTIVE(1, "有效");

        private Integer code;
        private String desc;

        EffectiveEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer code() {
            return this.code;
        }

        public String desc() {
            return this.desc;
        }
    }


    // 方案功能模块
    public enum ProjectModuleEnum implements IBaseEnum {
        RESULTS_REVIEW(1001, "成果审查"),
        BUILDING_REVIEW(1002, "建筑方案审查"),
        REGULATORY_ADJUSTMENT(1003, "控规调整"),
        KEY_POINTS_OF_PLANNING(1004, "核提规划要点");

        private Integer code;
        private String desc;

        ProjectModuleEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer code() {
            return this.code;
        }

        public String desc() {
            return this.desc;
        }
    }


    // 方案审查功能模块
    public enum ReviewModuleEnum implements IBaseEnum {

        // 建筑方案审查
        BUILDING_PROGRAM_TEXT_REVIEW(1012, "方案文本审查"),
        BUILDING_CITY_DESIGN_REVIEW(1022, "城市设计要求自动审查"),
        BUILDING_URBAN_DESIGN_REQUIRES_MANUAL_REVIEW(1032, "城市设计要求人工审查"),

        // 成果审查
        CITY_URBAN_DESIGN_REQUIRES_AUTOMATIC_REVIEW(1011, "城市设计要求自动审查"),
        CITY_Urban_Design_Requires_Manual_Review(1021, "城市设计要求人工审查"),

        // 控规调整
        Public_Service_Facility_Analysis(1013, "公服设施分析"),
        Municipal_Facility_Design(1023, "市政设施设计"),
        TRAFFIC_FACILITY_ANALYSIS(1033, "交通设施分析"),

        PROGRAM_OVERVIEW(1043, "方案概况"),
        BASIC_CONDITIONS(1053, "基本条件"),
        PLANNING_REQUIREMENTS(1063, "规划要求"),

        MULTISCALE_ANALYSIS(1073, "多规分析"),
        URBAN_DESIGN(1083, "城市设计"),
        Comparative_Analysis(1093, "对比分析");


        private Integer code;
        private String desc;

        ReviewModuleEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer code() {
            return this.code;
        }

        public String desc() {
            return this.desc;
        }

    }

}
