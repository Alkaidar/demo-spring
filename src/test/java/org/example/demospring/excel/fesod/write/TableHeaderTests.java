package org.example.demospring.excel.fesod.write;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.enums.HeaderMergeStrategy;
import org.example.demospring.excel.fesod.pojo.ComplexHeadData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class TableHeaderTests {

    @Test
    void contextLoads() {
    }

    private List<ComplexHeadData> data() {
        List<ComplexHeadData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            ComplexHeadData data = new ComplexHeadData();
            data.setString("String" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void complexHeadWrite() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, ComplexHeadData.class)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void dynamicHeadWrite() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";

        List<List<String>> head = Arrays.asList(
                Collections.singletonList("动态字符串标题"),
                Collections.singletonList("动态日期标题"),
                Collections.singletonList("动态数字标题")
        );

        FesodSheet.write(fileName, ComplexHeadData.class)
                .head(head)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void dynamicHeadWrite2() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";

        List<List<String>> head = Arrays.asList(
                Arrays.asList("demo", "动态字符串标题"),
                Arrays.asList("demo", "动态日期标题"),
                Collections.singletonList("动态数字标题")
        );

        FesodSheet.write(fileName, ComplexHeadData.class)
                .head(head)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void dynamicHeadWriteWithStrategy() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";

        List<List<String>> head = Arrays.asList(
                Arrays.asList("demo", "动态字符串标题"),
                Arrays.asList("demo", "动态日期标题"),
                Collections.singletonList("动态数字标题")
        );

        FesodSheet.write(fileName, ComplexHeadData.class)
                .head(head)
                .headerMergeStrategy(HeaderMergeStrategy.NONE)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void dynamicHeadWriteWithStrategy2() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";

        List<List<String>> head = Arrays.asList(
                Arrays.asList("demo", "demo"),
                Arrays.asList("demo", "动态日期标题"),
                Collections.singletonList("动态数字标题")
        );

        FesodSheet.write(fileName, ComplexHeadData.class)
                .head(head)
                .headerMergeStrategy(HeaderMergeStrategy.AUTO)
                .sheet()
                .doWrite(data());
    }

    @Test
    public void dynamicHeadWriteWithStrategy3() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";

        List<List<String>> head = Arrays.asList(
                Arrays.asList("demo", "demo"),
                Arrays.asList("demo", "动态日期标题"),
                Collections.singletonList("动态数字标题")
        );

        FesodSheet.write(fileName, ComplexHeadData.class)
                .head(head)
                .headerMergeStrategy(HeaderMergeStrategy.VERTICAL_ONLY)
                .sheet()
                .doWrite(data());
    }

}
