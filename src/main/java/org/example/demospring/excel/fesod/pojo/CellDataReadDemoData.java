package org.example.demospring.excel.fesod.pojo;

import lombok.Data;
import org.apache.fesod.sheet.metadata.data.CellData;

import java.util.Date;

/**
 * @ClassName CellDataReadDemoData
 * @Description 使用 CellData 类型接收单元格数据以支持公式和多种单元格格式。
 * @Author wangzhipeng
 * @Date 2026/6/8 14:49
 */

@Data
public class CellDataReadDemoData {
    private CellData<String> string;
    private CellData<Date> date;
    private CellData<Double> doubleData;
    private CellData<String> formulaValue;
}
