import lombok.Data;

import javax.persistence.Id;

/**
 * @author yujx
 * @date 2019/04/25 15:20
 */
@Data
public class Permission {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限描述
     */
    private String description;


//    new LinkedList<Integer>(10);
//    /**
//     * 权限的父权限id
//     */
//    private String pid;
//
//    /**
//     * 可见状态
//     */
//    private String enVisible;


}
