package com.dist.web.filter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dist.api.service.biz.HelloService;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.web.controller.HelloController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author yujx
 * @date 2019/04/19 10:41
 */
@Component
@WebFilter("/wopi/**")
public class TestFilter implements Filter {

    @Autowired
    private IGenerator dozer;

    @Autowired
    private HelloController helloController;

    @Reference
    private HelloService helloService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("doFilter" + dozer);
        System.out.println(helloController);
        System.out.println(helloService);
    }

    @Override
    public void destroy() {

    }

    public TestFilter() {
        System.out.println("构造方法");
    }

    @PostConstruct
    public void func() {
        System.out.println("初始化完成");
    }
}
