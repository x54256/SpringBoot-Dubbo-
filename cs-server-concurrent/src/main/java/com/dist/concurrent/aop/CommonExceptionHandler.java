package com.dist.concurrent.aop;

import com.dist.base.exception.*;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 提供统一异常处理
 *
 * @author yinxp@dist.com.cn
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 对象为空
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ObjectIsNullException.class)
    @ResponseBody
    public Result exceptionHandler(ObjectIsNullException e) {
        log.error("ObjectIsNullException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }

    /**
     * 字段为空
     *
     * @param e
     * @return
     */
    @ExceptionHandler(FieldIsNullException.class)
    @ResponseBody
    public Result exceptionHandler(FieldIsNullException e) {
        log.error("FieldIsNullException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }

    /**
     * 记录已存在
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ExistedRecordedException.class)
    @ResponseBody
    public Result exceptionHandler(ExistedRecordedException e) {
        log.error("ExistedRecordedException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }

    /**
     * 记录不存在
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnRecordedException.class)
    @ResponseBody
    public Result exceptionHandler(UnRecordedException e) {
        log.error("UnRecordedException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }

    /**
     * 字段太长
     *
     * @param e
     * @return
     */
    @ExceptionHandler(FieldIsTooLongException.class)
    @ResponseBody
    public Result exceptionHandler(FieldIsTooLongException e) {
        log.error("FieldIsTooLongException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }

    /**
     * 参数非法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalParameterException.class)
    @ResponseBody
    public Result exceptionHandler(IllegalParameterException e) {
        log.error("IllegalParameterException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }

    /**
     * 非法请求
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalRequestException.class)
    @ResponseBody
    public Result exceptionHandler(IllegalRequestException e) {
        log.error("IllegalRequestException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }

    /**
     * 验证失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CheckFailException.class)
    @ResponseBody
    public Result exceptionHandler(CheckFailException e) {
        log.error("CheckFailException detail：" + e.getMessage(), e);
        return ResultUtil.fail(e.getMessage(), exceptionDetail(e));
    }


    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result exceptionHandler(RuntimeException e) {
        log.error("RuntimeException detail：", e);
        return ResultUtil.error("系统内部错误,请求失败", exceptionDetail(e));
    }


    /**
     * 系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        log.error("Exception detail：", e);
        return ResultUtil.error("系统内部错误,请求失败", exceptionDetail(e));
    }

    /**
     * 判断异常类型并设置对应提示信息
     *
     * @param e
     * @return
     */
    private String exceptionDetail(Exception e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        StringBuilder detail = new StringBuilder();
        String exclass = stackTraceElement.getClassName();
        String method = stackTraceElement.getMethodName();
        detail.append("类[");
        detail.append(exclass);
        detail.append("]调用[");
        detail.append(method);
        detail.append("]方法时在第[");
        detail.append(stackTraceElement.getLineNumber());
        detail.append("]行代码处发生[");
        detail.append(e.getClass().getName());
        detail.append("]异常，");

        // 自选是否输出堆栈信息给前端
//        detail.append("\n");
//        detail.append("异常堆栈信息：");
//        for (StackTraceElement stack : e.getStackTrace()){
//            detail.append("\t").append(stack);
//            detail.append("\n");
//        }

        detail.append("异常信息为：[");
        detail.append(e.getMessage());
        detail.append("]");

        return detail.toString();
    }
}
