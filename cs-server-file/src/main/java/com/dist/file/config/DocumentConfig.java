package com.dist.file.config;

import com.dist.base.utils.report.DocumentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yujx
 * @date 2019/03/19 13:33
 */
@Configuration
public class DocumentConfig {

    @Bean("documentHandler")
    public DocumentHandler documentConfig() {
        return new DocumentHandler();
    }
}
