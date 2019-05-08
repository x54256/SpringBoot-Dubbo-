import lombok.Data;

import javax.persistence.Id;

@Data
public class PermissionApi {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 链接
     */
    private String apiUrl;

    /**
     * 请求类型
     */
    private String apiMethod;

    /**
     * 权限等级，1为通用接口权限，2为需校验接口权限
     */
    private String apiLevel;
}