package org.example.demospring.excel.fesod.read;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.context.AnalysisContext;
import org.apache.fesod.sheet.read.listener.PageReadListener;
import org.apache.fesod.sheet.read.listener.ReadListener;
import org.example.demospring.excel.fesod.listener.DemoDataListener;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SimpleTests
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 10:57
 */
@Slf4j
public class SimpleTests {

    /**
     * @Description lambda表达式
     * @Author wangzhipeng
     * @Date 2026/6/8 11:13
     */
    @Test
    public void simpleRead() {
        String fileName = "simpleWrite1780887388992.xlsx";

        FesodSheet.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList ->{
            for (DemoData demoData : dataList) {
                // log.info("读取到一条数据：{}", JSONValue.toJSONString(demoData));
                log.info("读取到一条数据：{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }

    /**
     * @Description 匿名内部类
     * @Author wangzhipeng
     * @Date 2026/6/8 11:13
     */
    @Test
    public void simpleRead2() {
        String fileName = "simpleWrite1780887388992.xlsx";

        FesodSheet.read(fileName, DemoData.class, new ReadListener<DemoData>() {
            @Override
            public void invoke(DemoData demoData, AnalysisContext analysisContext) {
                log.info("读取到一条数据：{}", JSON.toJSONString(demoData));
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                log.info("所有数据读取完成！");
            }
        }).sheet().doRead();
    }


    @Test
    public void simpleRead3() {
        String fileName = "simpleWrite1780887388992.xlsx";

        FesodSheet.read(fileName, DemoData.class, new DemoDataListener())
                .sheet()
                .doRead();
    }

    @Test
    public void synchronousReadToObjectList() {
        String fileName = "simpleWrite1780887388992.xlsx";

        List<DemoData> list = FesodSheet.read(fileName)
                .head(DemoData.class)
                .sheet()
                .doReadSync();

        for (DemoData demoData : list) {
            log.info("读取到的数据：{}", demoData);
        }
    }

    @Test
    public void synchronousReadToMapList() {
        String fileName = "simpleWrite1780887388992.xlsx";

        List<Map<Integer, String>> list = FesodSheet.read(fileName)
                .sheet()
                .doReadSync();

        for (Map<Integer, String> map : list) {
            log.info("读取到的数据：{}", map);
        }
    }
}
