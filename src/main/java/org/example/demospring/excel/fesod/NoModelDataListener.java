package org.example.demospring.excel.fesod;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.fesod.sheet.context.AnalysisContext;
import org.apache.fesod.sheet.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName NoModelDataListener
 * @Description 无 POJO 类监听器
 * Fesod 支持不定义 POJO 类直接读取电子表格文件，通过 Map<Integer, String> 直接读取数据，其中的键为列索引，值为单元格数据。
 * @Author wangzhipeng
 * @Date 2026/6/8 14:08
 */
@Slf4j
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        log.info("读取到一条数据: {}", JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据读取完成！");
    }
}
