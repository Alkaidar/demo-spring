package org.example.demospring.excel.fesod.read;

import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.listener.DemoHeadDataListener;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName HeadTests
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 14:35
 */
@SpringBootTest
public class HeadTests {
    @Test
    public void headerRead() {
        String fileName = "repeatedWrite1780899554359.xlsx";

        FesodSheet.read(fileName, DemoData.class, new DemoHeadDataListener())
                .sheet()
                .doRead();
    }

    @Test
    public void complexHeaderRead() {
        String fileName = "repeatedWrite1780899554359.xlsx";

        FesodSheet.read(fileName, DemoData.class, new DemoHeadDataListener())
                .sheet()
                .headRowNumber(2)
                .doRead();
    }


    @Test
    public void headerPojoRead() {
        String fileName = "repeatedWrite1780899554359.xlsx";

        FesodSheet.read(fileName, new DemoHeadDataListener())
                .head(DemoData.class)
                .sheet()
                .doRead();
    }
}
