package com.example.auto_generate.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:    pagemodel��ö����
 * @Author:         syh
 * @CreateDate:     2021/10/8 12:53
 * @Version:        1.0
 */
@AllArgsConstructor
public enum  PageModelEnum {
    MAIN("Main","MainJsp.tpl","/MainPageModel.tpl");
    private String name;
    private String jspTempName; //jspģ������
    private String pageModelTempName; //pagemodelģ������
//    private String realPageModelName; //��ʵ��pagemodel����
//    private String realJspName; //��ʵ��jsp����

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
