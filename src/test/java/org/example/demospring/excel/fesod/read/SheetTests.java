package org.example.demospring.excel.fesod.read;

import org.apache.fesod.sheet.ExcelReader;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.read.metadata.ReadSheet;
import org.example.demospring.excel.fesod.DemoDataListener;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName SheetTests
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 14:19
 */
@SpringBootTest
public class SheetTests {
    /**
     * @Description 读取多个 Sheet
     * 可以读取电子表格文件中的多个 Sheet，且同一个 Sheet 不可重复读取。
     * @Author wangzhipeng
     * @Date 2026/6/8 14:22
     */
    @Test
    public void readAllSheet() {
        String fileName = "repeatedWrite1780899554359.xlsx";

        FesodSheet.read(fileName, DemoData.class, new DemoDataListener()).doReadAll();
    }

    @Test
    public void readSingleSheet() {
        String fileName = "repeatedWrite1780899554359.xlsx";

        try (ExcelReader reader = FesodSheet.read(fileName).build()) {
            ReadSheet sheet1 = FesodSheet.readSheet(0)
                    .head(DemoData.class)
                    .registerReadListener(new DemoDataListener())
                    .build();
            ReadSheet sheet3 = FesodSheet.readSheet("Sheet2")
                    .head(DemoData.class)
                    .registerReadListener(new DemoDataListener())
                    .build();

            reader.read(sheet1, sheet3);
        }
    }
}
