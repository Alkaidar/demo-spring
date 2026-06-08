package org.example.demospring.excel.fesod;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.fesod.sheet.context.AnalysisContext;
import org.apache.fesod.sheet.event.AnalysisEventListener;
import org.apache.fesod.sheet.metadata.data.ReadCellData;
import org.example.demospring.excel.fesod.pojo.DemoData;

import java.util.Map;

/**
 * @ClassName DemoHeadDataListener
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 14:33
 */
@Slf4j
public class DemoHeadDataListener extends AnalysisEventListener<DemoData> {
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        log.info("解析到表头数据：{}", JSON.toJSONString(headMap));
    }

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
