package org.example.demospring.excel.fesod.pojo;

import lombok.Data;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.apache.fesod.sheet.annotation.format.DateTimeFormat;
import org.apache.fesod.sheet.annotation.format.NumberFormat;
import org.example.demospring.excel.fesod.CustomStringStringConverter;

import java.util.Date;

/**
 * @ClassName ConverterData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/5 8:50
 */
@Data
public class ConverterData {
    @ExcelProperty(value = "字符串标题", converter = CustomStringStringConverter.class)
    private String string;

    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    @ExcelProperty("日期标题")
    private Date date;

    // 格式化成百分数，并且最多两位小数，四舍五入
    @NumberFormat("#.##%")
    @ExcelProperty("数字标题")
    private Double doubleData;
}
