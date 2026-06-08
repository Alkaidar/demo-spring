package org.example.demospring.excel.fesod.write;

import org.apache.commons.csv.QuoteMode;
import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.metadata.csv.CsvConstant;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class CSVTests {

    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("String" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


    @Test
    public void delimiterDemo() {
        String csvFile = "demoCSV" + System.currentTimeMillis() + ".csv";
        FesodSheet.write(csvFile, DemoData.class)
                .csv()
                .delimiter(CsvConstant.UNICODE_EMPTY)
                .doWrite(data());
    }


    @Test
    public void quoteDemo() {
        String csvFile = "demoCSV" + System.currentTimeMillis() + ".csv";

        // TODO 这也没包裹字段啊
        FesodSheet.write(csvFile, DemoData.class)
                .csv()
                .quote(CsvConstant.DOUBLE_QUOTE, QuoteMode.MINIMAL)
                .doWrite(data());
    }


    @Test
    public void recordSeparatorDemo() {
        String csvFile = "demoCSV" + System.currentTimeMillis() + ".csv";
        FesodSheet.write(csvFile, DemoData.class)
                .csv()
                .recordSeparator(CsvConstant.LF)
                .doWrite(data());
    }

}
