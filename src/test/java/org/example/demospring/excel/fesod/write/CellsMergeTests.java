package org.example.demospring.excel.fesod.write;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.write.merge.LoopMergeStrategy;
import org.example.demospring.excel.fesod.CustomMergeStrategy;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.example.demospring.excel.fesod.pojo.DemoMergeData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class CellsMergeTests {

    private List<DemoMergeData> data() {
        List<DemoMergeData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoMergeData data = new DemoMergeData();
            data.setString("String" + i);
            data.setDate(new Date());
            data.setDoubleData(34.56789);
            list.add(data);
        }
        return list;
    }


    @Test
    public void widthAndHeightWrite() {
        String fileName = "annotationMergeWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoMergeData.class)
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
