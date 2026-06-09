package org.example.demospring.excel.fesod.write;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.ExcelWriter;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.write.merge.LoopMergeStrategy;
import org.apache.fesod.sheet.write.metadata.WriteSheet;
import org.apache.fesod.sheet.write.metadata.WriteTable;
import org.apache.fesod.sheet.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.example.demospring.excel.fesod.CustomCellWriteWidthStyleStrategy;
import org.example.demospring.excel.fesod.CustomMergeStrategy;
import org.example.demospring.excel.fesod.CustomStringStringConverter;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoSpringApplicationTests {

    @Test
    void contextLoads() {
    }

    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("String1111111111111111111111111" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


    @Test
    public void simpleWrite() {
        String fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoData.class)
                .sheet("Sheet1")
                .doWrite(data());
    }

    /**
     * @Description 自适应列宽
     * @Author wangzhipeng
     * @Date 2026/6/9 11:00
     */
    @Test
    public void simpleWrite2() {
        String fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoData.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("Sheet1")
                .doWrite(data());
    }

    /**
     * @Description 自适应列宽 自定义的更美观一些
     * 不过这两种方式对date好像都不生效，对字符串是完全没问题的
     * @Author wangzhipeng
     * @Date 2026/6/9 11:00
     */
    @Test
    public void simpleWrite3() {
        String fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoData.class)
                .registerWriteHandler(new CustomCellWriteWidthStyleStrategy())
                .sheet("Sheet1")
                .doWrite(data());
    }

    @Test
    public void simpleWriteExcelWriter() {
        String fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";

        try (ExcelWriter excelWriter = FesodSheet.write(fileName, DemoData.class).build()) {
            WriteSheet writeSheet = FesodSheet.writerSheet("Sheet1").build();
            excelWriter.write(data(), writeSheet);
        }
    }

    @Test
    public void writeSingleSheet() {
        String fileName = "repeatedWrite" + System.currentTimeMillis() + ".xlsx";

        try (ExcelWriter excelWriter = FesodSheet.write(fileName, DemoData.class).build()) {
            WriteSheet writeSheet = FesodSheet.writerSheet("Sheet1").build();
            for (int i = 0; i < 5; i++) {
                excelWriter.write(data(), writeSheet);
            }
        }
    }

    @Test
    public void writeMultiSheet() {
        String fileName = "repeatedWrite" + System.currentTimeMillis() + ".xlsx";

        try (ExcelWriter excelWriter = FesodSheet.write(fileName, DemoData.class).build()) {
            for (int i = 0; i < 5; i++) {
                WriteSheet writeSheet = FesodSheet.writerSheet("Sheet" + i).build();
                excelWriter.write(data(), writeSheet);
            }
        }
    }

    // TODO 这个有bug，他会在第一行多生成一个标题行
    @Test
    public void tableWrite() {
        String fileName = "repeatedWrite" + System.currentTimeMillis() + ".xlsx";

        try (ExcelWriter excelWriter = FesodSheet.write(fileName, DemoData.class).build()) {
            WriteSheet writeSheet = FesodSheet.writerSheet("Table示例").build();
            WriteTable table0 = FesodSheet.writerTable(0).build();
            WriteTable table1 = FesodSheet.writerTable(1).build();
            excelWriter.write(data(), writeSheet, table0);
            excelWriter.write(data(), writeSheet, table1);
        }
    }

    // TODO 没有生效，不知道是不是Fesod的bug
    @Test
    public void globalConverterWrite() {
        String fileName = "globalConverterWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoData.class)
                .registerConverter(new CustomStringStringConverter())
                .sheet()
                .doWrite(data());
    }

    @Test
    public void strategyMergeWrite() {
        String fileName = "strategyMergeWrite" + System.currentTimeMillis() + ".xlsx";

        // 合并第 0 列中每 2 行
        LoopMergeStrategy strategy = new LoopMergeStrategy(2, 0);
        FesodSheet.write(fileName, DemoData.class)
                .registerWriteHandler(strategy)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void customMergeWrite() {
        String fileName = "customMergeWrite" + System.currentTimeMillis() + ".xlsx";
        FesodSheet.write(fileName, DemoData.class)
                .registerWriteHandler(new CustomMergeStrategy())
                .sheet()
                .doWrite(data());
    }

}
