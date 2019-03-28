package com.dist.api.service.feign.file;

import com.dist.base.response.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author yujx
 * @date 2019/03/27 16:41
 */
@Path("/rest/public/resource")
public interface ResourceApi {

    /**
     * 根据mongoId获取预览地址
     *
     * @param mongoId    mongo上的文件id
     * @param fileName   文件名
     * @param fileSuffix 文件后缀（不带.）
     * @return 文件预览地址
     */
    @GET
    @Path("/v1/file/preview/{mongoId}")
    // @Consumes(MediaType.APPLICATION_JSON)   // 接收值类型
    @Produces(MediaType.APPLICATION_JSON)   // 返回值类型
    Result filePreviewByMongoId(// @Context HttpServletRequest request, // 获取环境信息（用了BaseController这个注解就没有用了）
                                @DefaultValue("5c24584461d21586acf6a23c") @PathParam(value = "mongoId") String mongoId,
                                @QueryParam("fileName") String fileName,
                                @QueryParam("fileSuffix") String fileSuffix);

    /*
    @BeanParam：自定义参数组合，BeanParam使得REST方法可以使用简洁的参数形式完成复杂的接口设计。
        说明：
            1>@BeanParam注解修饰的类是用来封装请求中的多个参数
            2>通过在@BeanParam注解修饰的类的属性上添加@FormParam、@QueryParam、@PathParam等注解来获取请求参数的值
        举例：
            接口URL：/getUserInfo

            @POST
            @Path("/getUserInfo")
            @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
            @Produces(MediaType.APPLICATION_JSON)
            public String getUserInfo(@BeanParam ReqGetUserInfoDTO req);

            public class ReqGetAccountInfoByIdDTO {
                @FormParam("username")
                private String username;

                @FormParam("password")
                private String password;
            }

    @HeaderParam：用来获取HTTP请求的头信息
    @CookieParam：用来获取HTTP请求的Cookie信息
     */
}
