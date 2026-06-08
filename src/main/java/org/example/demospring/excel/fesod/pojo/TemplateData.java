package org.example.demospring.excel.fesod.pojo;

import lombok.Data;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.apache.fesod.sheet.metadata.data.WriteCellData;

/**
 * @ClassName TemplateData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/5 14:27
 */
@Data
public class TemplateData {
    @ExcelProperty("name")
    private String name;
    @ExcelProperty("chinese")
    private Double chinese;
    @ExcelProperty("math")
    private Double math;
    @ExcelProperty("english")
    private Double english;

    @ExcelProperty("number")
    private Double number;

    private WriteCellData<String> sum;
}
