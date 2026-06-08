package org.example.demospring.excel.fesod.pojo;

import lombok.Data;
import org.apache.fesod.sheet.annotation.ExcelProperty;

import java.util.Date;

/**
 * @ClassName IndexOrNameData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 14:45
 */

@Data
public class IndexOrNameData {
    @ExcelProperty(index = 2)
    private Double doubleData;

    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;
}
