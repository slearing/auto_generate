package com.example.auto_generate.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:    pagemodel的枚举类
 * @Author:         syh
 * @CreateDate:     2021/10/8 12:53
 * @Version:        1.0
 */
@AllArgsConstructor
public enum  PageModelEnum {
    MAIN("Main","MainJsp.tpl","/MainPageModel.tpl");
    private String name;
    private String jspTempName; //jsp模板名称
    private String pageModelTempName; //pagemodel模板名称
//    private String realPageModelName; //真实的pagemodel名称
//    private String realJspName; //真实的jsp名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJspTempName() {
        return jspTempName;
    }

    public void setJspTempName(String jspTempName) {
        this.jspTempName = jspTempName;
    }

    public String getPageModelTempName() {
        return pageModelTempName;
    }

    public void setPageModelTempName(String pageModelTempName) {
        this.pageModelTempName = pageModelTempName;
    }

//    public String getRealPageModelName() {
//        return realPageModelName;
//    }
//
//    public void setRealPageModelName(String realPageModelName) {
//        this.realPageModelName = realPageModelName;
//    }
//
//    public String getRealJspName() {
//        return realJspName;
//    }
//
//    public void setRealJspName(String realJspName) {
//        this.realJspName = realJspName;
//    }
}
