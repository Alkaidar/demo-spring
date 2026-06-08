package org.example.demospring.excel.fesod.read;

import org.apache.commons.csv.QuoteMode;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.metadata.csv.CsvConstant;
import org.example.demospring.excel.fesod.listener.DemoDataListener;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName CSVTests
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 15:22
 */
@SpringBootTest
public class CSVTests {

    @Test
    public void delimiterDemo() {
        String csvFile = "demoCSV1780903305333.csv";
        List<DemoData> dataList = FesodSheet.read(csvFile, DemoData.class, new DemoDataListener())
                .csv()
                .delimiter(CsvConstant.UNICODE_EMPTY)
                .doReadSync();
    }


    @Test
    public void quoteDemo() {
        String csvFile = "demoCSV1780903482267.csv";
        List<DemoData> dataList = FesodSheet.read(csvFile, DemoData.class, new DemoDataListener())
                .csv()
                .quote(CsvConstant.DOUBLE_QUOTE, QuoteMode.MINIMAL)
                .doReadSync();
    }


    @Test
    public void recordSeparatorDemo() {
        String csvFile = "demoCSV1780903482267.csv";
        List<DemoData> dataList = FesodSheet.read(csvFile, DemoData.class, new DemoDataListener())
                .csv()
                .recordSeparator(CsvConstant.LF)
                .doReadSync();
    }


    @Test
    public void nullStringDemo() {
        String csvFile = "demoCSV1780903482267.csv";
        List<DemoData> dataList = FesodSheet.read(csvFile, DemoData.class, new DemoDataListener())
                .csv()
                .nullString("N/A")
                .doReadSync();
    }


    // TODO 没明白
    @Test
    public void escapeDemo() {
        String csvFile = "demoCSV1780903482267.csv";
        List<DemoData> dataList = FesodSheet.read(csvFile, DemoData.class, new DemoDataListener())
                .csv()
                .escape(CsvConstant.BACKSLASH)
                .doReadSync();
    }
}
