package com.example.auto_generate.beetl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author: brbai
 * @create: 2019-10-16 17:12:45
 * @description:
 */
@Configuration
@Slf4j
@Data
public class BeetlConfiguration {
    @Value("${beetl.tempDir}")
    private String tempDir;


    @Bean(name = "beetlConfig")
    public BeetlGroupUtilConfiguration beetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration configuration = new BeetlGroupUtilConfiguration();
        Properties properties = new Properties();
        properties.setProperty("DELIMITER_PLACEHOLDER_START", "#{");
        properties.setProperty("DELIMITER_PLACEHOLDER_END", "}");
        properties.setProperty("DELIMITER_STATEMENT_START", "<?");
        properties.setProperty("DELIMITER_STATEMENT_END", "?>");
        configuration.setConfigProperties(properties);
        FileResourceLoader resourceLoader = new FileResourceLoader(tempDir);

        configuration.setResourceLoader(resourceLoader);
        configuration.init();

        return configuration;
    }




}