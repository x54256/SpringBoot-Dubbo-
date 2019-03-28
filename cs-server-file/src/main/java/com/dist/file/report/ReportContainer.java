package com.dist.file.report;

import com.dist.base.utils.ApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author yujx
 * @date 2019/03/19 13:59
 */
@Component
public class ReportContainer extends ApplicationContextUtil {

    private Map<Integer, IReport> reportMap;

    @PostConstruct
    public void init() {

        List<IReport> reportList = this.registryObj2ApplicationContext(IReport.class);

        this.reportMap = constructReportMap(reportList);
    }

    /**
     * 构造报告的map
     *
     * @param reportList
     * @return
     */
    private Map<Integer, IReport> constructReportMap(List<IReport> reportList) {

        Map<Integer, IReport> reportMap = new HashMap<>();

        for (IReport iReport : reportList) {
            reportMap.put(iReport.getModule(), iReport);
        }

        return reportMap;
    }


    private <T> List<T> registryObj2ApplicationContext(Class<T> tClass) {

        // 获得当前包名
        String packageName = this.getClass().getPackage().getName();

        // 找到它的所有实现类
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends T>> implClasses = reflections.getSubTypesOf(tClass);

        List<T> tList = new ArrayList<>();

        // 注册到spring中
        for (Class<? extends T> clazz : implClasses) {

            // 获取实现类类名，将首字母小写
            String simpleName = clazz.getSimpleName();
            String beanName = StringUtils.uncapitalize(simpleName);

            // 注入Spring容器
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);

            // 从容器中把它取出
            T bean = applicationContext.getBean(beanName, tClass);
            tList.add(bean);
        }

        return tList;
    }


    public Map<Integer, IReport> getReportMap() {
        return reportMap;
    }

}
