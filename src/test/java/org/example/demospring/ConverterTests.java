package org.example.demospring;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.CustomStringStringConverter;
import org.example.demospring.excel.fesod.pojo.ConverterData;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ConverterTests {

    private List<ConverterData> data() {
        List<ConverterData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            ConverterData data = new ConverterData();
            data.setString("String" + i);
            data.setDate(new Date());
            data.setDoubleData(34.56789);
            list.add(data);
        }
        return list;
    }

    @Test
    public void converterWrite() {
        String fileName = "converterWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, ConverterData.class)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void globalConverterWrite() {
        String fileName = "globalConverterWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoData.class)
                .registerConverter(new CustomStringStringConverter())
                .sheet()
                .doWrite(data());
    }

}
