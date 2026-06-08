package org.example.demospring.excel.fesod.pojo;

import lombok.Data;
import org.apache.fesod.sheet.annotation.ExcelIgnore;
import org.apache.fesod.sheet.annotation.ExcelProperty;

import java.util.Date;

/**
 * @ClassName DemoData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/4 16:09
 */

@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelIgnore
    private String ignore; // 忽略的字段
}
