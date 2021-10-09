package com.example.auto_generate.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:    ������ݵ�ģ���ļ���Ӧ��������
 * @Author:         syh
 * @CreateDate:     2021/10/8 13:10
 * @Version:        1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TempData {
    private String packName; //����
    private String majorTable; //������
    private String itemTable; //��ϸ����
    private Map<String,Object> data;

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("packName",packName);
        map.put("majorTable",majorTable);
        map.put("itemTable",itemTable);
        map.put("data",data);

        return map;
    }
}
