package org.example.demospring;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.IndexData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class POJOTests {

    private List<IndexData> data() {
        List<IndexData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            IndexData data = new IndexData();
            data.setString("String" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void indexWrite() {
        String fileName = "indexWrite" + System.currentTimeMillis() + ".xlsx";
        FesodSheet.write(fileName, IndexData.class)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void excludeOrIncludeWrite() {
        String fileName = "indexWrite" + System.currentTimeMillis() + ".xlsx";

        Set<String> excludeColumns = new HashSet<>(Collections.singletonList("date"));

        FesodSheet.write(fileName, IndexData.class)
                .excludeColumnFieldNames(excludeColumns)
                .sheet()
                .doWrite(data());
    }



    @Test
    public void noModelWrite() {
        String fileName = "noModelWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName)
                .head(head()) // 动态头
                .sheet("无对象写入")
                .doWrite(dataList());
    }

    private List<List<String>> head() {
        return Arrays.asList(
                Collections.singletonList("字符串标题"),
                Collections.singletonList("数字标题"),
                Collections.singletonList("日期标题"));
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Arrays.asList("字符串" + i, 0.56, new Date()));
        }
        return list;
    }

}
