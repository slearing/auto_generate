package generate;

import com.example.auto_generate.utils.TempData;
import org.beetl.core.Template;
import utils.YmlUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

/**
 * @Description:    ������������
 * @Author:         syh
 * @CreateDate:     2021/10/8 13:02
 * @Version:        1.0
 */

public interface BasicGenerator {
    String jspSuffix=".jsp";
    String pageModelSuffix="PageModel.java";
    String tempPath = "%s"+ File.separator+"%s";
    String filePath = "%s"+ File.separator+"%s_%s%s";

    void doGenerate() throws IOException;

    default TempData getTempData(String pack, String majorTable, String itemTable){
        TempData template = new TempData();
        template.setPackName(pack);
        template.setItemTable(itemTable);
        template.setMajorTable(majorTable);

        template.setData(new HashMap<>());

        return template;
    }


    /**
     * 模板写到文件中
     * @param t
     * @param filePath
     * @throws IOException
     */
    default void createFile(Template t, String filePath) throws IOException {

        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        file.createNewFile();
        Writer writer = new FileWriter(file);
        t.renderTo(writer);
    }
}
