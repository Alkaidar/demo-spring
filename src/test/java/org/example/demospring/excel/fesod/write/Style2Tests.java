package org.example.demospring.excel.fesod.write;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.pojo.WidthAndHeightData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class Style2Tests {

    private List<WidthAndHeightData> data() {
        List<WidthAndHeightData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            WidthAndHeightData data = new WidthAndHeightData();
            data.setString("String" + i);
            data.setDate(new Date());
            data.setDoubleData(34.56789);
            list.add(data);
        }
        return list;
    }


    @Test
    public void widthAndHeightWrite() {
        String fileName = "widthAndHeightWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, WidthAndHeightData.class)
                .sheet()
                .doWrite(data());
    }

}
