package com.example.auto_generate.generate.impl;

import com.example.auto_generate.beetl.TempManager;
import com.example.auto_generate.utils.PageModelEnum;
import com.example.auto_generate.utils.TempData;
import generate.BasicGenerator;
import org.beetl.core.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.YmlUtil;

import java.io.IOException;

/**
 * @Description:    ��ҳ���������
 * @Author:         syh
 * @CreateDate:     2021/10/8 13:03
 * @Version:        1.0
 */
@Component
public class MainGenerator implements BasicGenerator {
    @Value("${beetl.tempDir}")
    private String tempDirPath;
    @Value("${beetl.fileDir}")
    private String fileDirPath;
    @Value("${packName}")
    private String packName;
    @Value("${database.tableInfo.majorTable}")
    private String majorTable;
    @Value("${database.tableInfo.itemTable}")
    private String itemTable;

    @Autowired
    private TempManager tempManager;

    public void doGenerate() throws IOException {
        TempData tempData = this.getTempData(packName,majorTable,itemTable);
        String pageModelTempPath = String.format(tempPath,tempDirPath, PageModelEnum.MAIN.getPageModelTempName());
        String generatePageModelPath = String.format(filePath,fileDirPath,tempData.getPackName(), PageModelEnum.MAIN.name(),pageModelSuffix);

        Template temp = tempManager.getTemp(PageModelEnum.MAIN.getPageModelTempName());
        temp.binding(tempData.toMap());

        createFile(temp,generatePageModelPath);
    }
}
