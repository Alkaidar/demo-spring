package org.example.demospring.excel.fesod.listener;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.fesod.sheet.context.AnalysisContext;
import org.apache.fesod.sheet.read.listener.ReadListener;
import org.example.demospring.excel.fesod.pojo.ConverterData;

/**
 * @ClassName DemoDataListener
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 11:17
 */
@Slf4j
public class ConverterDataListener implements ReadListener<ConverterData> {
    @Override
    public void invoke(ConverterData converterData, AnalysisContext analysisContext) {
        log.info("读取到一条数据: {}", JSON.toJSONString(converterData));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据读取完成！");
    }
}
