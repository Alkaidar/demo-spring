package org.example.demospring.excel.fesod.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.apache.fesod.sheet.annotation.write.style.ColumnWidth;
import org.apache.fesod.sheet.annotation.write.style.ContentRowHeight;
import org.apache.fesod.sheet.annotation.write.style.HeadRowHeight;

import java.util.Date;

/**
 * @ClassName WidthAndHeightData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/5 9:51
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(20)
@HeadRowHeight(30)
@ColumnWidth(25) // 默认列宽
public class WidthAndHeightData {
    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    @ColumnWidth(50) // 单独设置列宽
    @ExcelProperty("数字标题")
    private Double doubleData;
}