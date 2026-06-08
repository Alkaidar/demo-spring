package org.example.demospring.excel.fesod.read;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.fesod.sheet.ExcelReader;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.read.listener.PageReadListener;
import org.apache.fesod.sheet.read.metadata.ReadSheet;
import org.example.demospring.excel.fesod.listener.DemoDataListener;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName RowsTests
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 15:15
 */
@Slf4j
@SpringBootTest
public class RowsTests {
    @Test
    public void allSheetRead() {
        String fileName = "converterWrite1780902331863.xlsx";

        FesodSheet.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).numRows(5).sheet().doRead();
    }


    @Test
    public void singleSheetRead() {
        String fileName = "converterWrite1780902331863.xlsx";

        try (ExcelReader excelReader = FesodSheet.read(fileName, DemoData.class, new DemoDataListener()).build()) {
            ReadSheet readSheet = FesodSheet.readSheet(0).build();
            readSheet.setNumRows(6); // 读取前100行
            excelReader.read(readSheet);
        }
    }
}
