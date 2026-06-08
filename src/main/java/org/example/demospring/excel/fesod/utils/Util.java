package org.example.demospring.excel.fesod.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

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

}
