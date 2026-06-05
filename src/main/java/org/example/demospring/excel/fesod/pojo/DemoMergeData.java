package org.example.demospring.excel.fesod.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.apache.fesod.sheet.annotation.write.style.ContentLoopMerge;

import java.util.Date;

/**
 * @ClassName DemoMergeData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/5 9:53
 */
@Getter
@Setter
@EqualsAndHashCode
public class DemoMergeData {
    @ContentLoopMerge(eachRow = 2)
    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    @ExcelProperty("数字标题")
    private Double doubleData;
}
