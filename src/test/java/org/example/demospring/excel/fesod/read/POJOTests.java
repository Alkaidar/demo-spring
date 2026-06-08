package org.example.demospring.excel.fesod.read;

import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.CellDataReadDemoDataListener;
import org.example.demospring.excel.fesod.IndexOrNameDataListener;
import org.example.demospring.excel.fesod.pojo.CellDataReadDemoData;
import org.example.demospring.excel.fesod.pojo.IndexOrNameData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName POJOTests
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 14:45
 */
@SpringBootTest
public class POJOTests {
    @Test
    public void indexOrNameRead() {
        String fileName = "repeatedWrite1780899554359.xlsx";

        FesodSheet.read(fileName, IndexOrNameData.class, new IndexOrNameDataListener())
                .sheet()
                .doRead();
    }

    @Test
    public void cellDataRead() {
        String fileName = "repeatedWrite1780899554359.xlsx";

        FesodSheet.read(fileName, CellDataReadDemoData.class, new CellDataReadDemoDataListener())
                .sheet()
                .doRead();
    }
}
