package com.dist.rbac.constant;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yujx
 * @date 2019/04/19 19:56
 */
public class StatusEnum {

    /**
     * 资源类型枚举
     */
    public enum ResourceTypeEnum {

        /**
         * 对应资源文件表
         */
        RESOURCE_FILE(0, "资源文件"),

        /**
         * 对应专题表
         */
        TOPIC_RESOURCE(1, "专题资源");

        private Integer code;
        private String desc;

        ResourceTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer code() {
            return this.code;
        }

        public String desc() {
            return this.desc;
        }

        /**
         * 检查code是否是枚举中的
         *
         * @param code
         * @return
         */
        public static boolean checkCode(Integer code) {
            for (ResourceTypeEnum resourceTypeEnum : ResourceTypeEnum.values()) {
                if (resourceTypeEnum.code().equals(code)) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * 请求方法枚举
     */
    public enum RequestMethodEnum {

        GET(1, RequestMethod.GET),
        POST(2, RequestMethod.POST),
        PUT(3, RequestMethod.PUT),
        DELETE(4, RequestMethod.DELETE);

        private Integer code;
        private RequestMethod method;

        public Integer code() {
            return code;
        }

        public RequestMethod method() {
            return method;
        }

        RequestMethodEnum(Integer code, RequestMethod method) {
            this.code = code;
            this.method = method;
        }
    }


}
