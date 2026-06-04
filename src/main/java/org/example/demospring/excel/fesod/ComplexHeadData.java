package org.example.demospring.excel.fesod;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.fesod.sheet.annotation.ExcelProperty;

import java.util.Date;

/**
 * @ClassName ComplexHeadData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/4 16:57
 */

@Getter
@Setter
@EqualsAndHashCode
public class ComplexHeadData {
    @ExcelProperty({"主标题", "字符串标题"})
    private String string;
    @ExcelProperty({"主标题", "日期标题"})
    private Date date;
    @ExcelProperty({"主标题", "数字标题"})
    private Double doubleData;
}