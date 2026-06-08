package org.example.demospring.excel.fesod.read;

import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.listener.ConverterDataListener;
import org.example.demospring.excel.fesod.pojo.ConverterData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName ConverterTests
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/8 15:03
 */
@SpringBootTest
public class ConverterTests {

    // TODO 用这个转换器结果就会为空，后续可以研究一下
    @Test
    public void converterRead() {
        String fileName = "converterWrite1780902331863.xlsx";

        FesodSheet.read(fileName, ConverterData.class, new ConverterDataListener())
                // .registerConverter(new CustomReadStringStringConverter())
                .sheet()
                .doRead();
    }
}
