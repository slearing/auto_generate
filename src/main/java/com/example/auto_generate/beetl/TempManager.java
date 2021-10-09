package com.example.auto_generate.beetl;

import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.YmlUtil;

import java.util.Properties;



/**
 * @Description:    用于创建模板
 * @Author:         syh
 * @CreateDate:     2021/10/9 14:06
 * @Version:        1.0
 */

@Component
public class TempManager {
    @Autowired
    private  BeetlGroupUtilConfiguration configuration;

    /**
     * 获取模板
     * @param tempName
     * @return
     */
    public Template getTemp(String tempName){
        return configuration.getGroupTemplate().getTemplate(tempName);
    }


}
