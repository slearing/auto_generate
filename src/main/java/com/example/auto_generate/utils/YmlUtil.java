package utils;

import com.esotericsoftware.yamlbeans.YamlReader;

/**
 * @Description:    yaml�ļ������Ĺ�����
 * @Author:         syh
 * @CreateDate:     2021/9/30 17:10
 * @Version:        1.0
 */

public class YmlUtil {
    //���������ļ���Ŀ¼
    private static String CONFIG_DIR  = "E:\\syh\\vue_code\\auto-generate\\src\\main\\resources\\";
    //beetl�����ļ���
    private static String BEETL_CONFIG_FILE = "config/beetl.yml";
    //�����������ļ���
    private static String CONFIG_FILE = "config/config.yml";
    //���Ƽ��޸ĵ����ò������ļ���
    private static String DEFAULT_CONFIG_FILE = "config/defaultConfig.yml";


    //��ȡyaml���ļ��Ķ�ȡ��
    private static YamlReader configReader = null;
    private static YamlReader defaultConfigReader = null;
    static{
        configReader = new YamlReader(CONFIG_DIR+CONFIG_FILE);
        defaultConfigReader = new YamlReader(CONFIG_DIR+DEFAULT_CONFIG_FILE);
    }


//    /**
//     * ��ȡ���������ļ��Ĳ���
//     * @param key
//     * @return
//     */
//    public static Object getConfigValue(String key){
//        return configReader.get(key);
//    }
//
//
//    /**
//     * ��ȡbeetl�����ļ��Ĳ���
//     * @param key
//     * @return
//     */
//    public static Object getDefaultConfigValue(String key){
//        return defaultConfigReader.get(key);
//    }
}
