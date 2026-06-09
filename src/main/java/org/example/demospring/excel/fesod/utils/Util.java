package org.example.demospring.excel.fesod.utils;

import org.apache.fesod.sheet.FesodSheet;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName Util
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 9:36
 */
public class Util {

    public static InputStream getTemplateInputStream(String templateFilePath) throws Exception {
        ClassPathResource resource = new ClassPathResource(templateFilePath);

        if (!resource.exists()) {
            throw new RuntimeException("模板文件不存在");
        }

        return resource.getInputStream();
    }

    /**
     * 读取 template 目录下的模板文件
     * @param templateName 模板文件名（如：学生成绩模板.xlsx）
     * @return 模板输入流
     * @throws RuntimeException 模板不存在时抛出
     */
    public InputStream getTemplate(String templateName) {
        String path = "template/" + templateName;
        ClassPathResource resource = new ClassPathResource(path);

        if (!resource.exists()) {
            throw new RuntimeException("模板文件不存在：" + path);
        }

        try {
            return resource.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("读取模板文件失败：" + path, e);
        }
    }

    /**
     * 读取 Excel 模板并写入数据
     * @param templateName 模板文件名
     * @param outputPath 输出文件路径
     * @param dataList 数据列表
     * @param clazz 数据实体类
     */
    public <T> void writeExcelWithTemplate(String templateName, String outputPath, List<T> dataList, Class<T> clazz) {
        try (InputStream templateInputStream = getTemplate(templateName)) {
            FesodSheet.write(outputPath, clazz)
                    .withTemplate(templateInputStream)
                    .sheet(0) // 使用模板的第一个工作表
                    .doWrite(dataList);
        } catch (Exception e) {
            throw new RuntimeException("Excel导出失败", e);
        }
    }

}
