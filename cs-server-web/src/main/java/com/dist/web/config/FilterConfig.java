package com.dist.web.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author yujx
 * @date 2019/04/19 10:43
 */
@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean officeOnlineFilterRegistrationBean() {
//        FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>();
//        TestFilter filter = new TestFilter();
//        // 不好使
//        // registrationBean.setFilter(new DelegatingFilterProxy(filter));
//        registrationBean.setFilter(filter);
//
//        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/wopi/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//
//        // 数字越小，优先级越高
//        registrationBean.setOrder(Integer.MAX_VALUE - 2);
//
//        return registrationBean;
//    }
}
