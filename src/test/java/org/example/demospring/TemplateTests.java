package org.example.demospring;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.pojo.TemplateData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.example.demospring.excel.fesod.utils.Util.getTemplateInputStream;

@SpringBootTest
class TemplateTests {

    private List<TemplateData> data() {
        List<TemplateData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            TemplateData data = new TemplateData();
            // Random random = new Random();

            // double chinese = ThreadLocalRandom.current().nextDouble(60.0, 100.0);
            // double math = ThreadLocalRandom.current().nextDouble(60.0, 100.0);
            // double english = ThreadLocalRandom.current().nextDouble(60.0, 100.0);

            double chinese = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(60.0, 100.0))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            double math = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(60.0, 100.0))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            double english = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(60.0, 100.0))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            data.setChinese(chinese);
            data.setMath(math);
            data.setEnglish(english);

            // Excel公式 TODO 如果需要每行写公式，那么就得动态生成，不能写死
            // WriteCellData<String> cellData = new WriteCellData<>();
            // FormulaData formulaData = new FormulaData();
            // formulaData.setFormulaValue("SUM(A1:C1)");
            // cellData.setFormulaData(formulaData);
            // data.setSum(cellData);

            // data.setChinese(60.25);
            // data.setMath(70.25);
            // data.setEnglish(80.25);
            list.add(data);
        }
        return list;
    }


    /**
     * doWrite()是普通写入，会无视模板。doFill()才会使用模板填充，他的docs文档写的不好
     * @Author wangzhipeng
     * @Date 2026/6/5 14:54
     */
    @Test
    public void delimiterDemo() throws Exception {
        String file = "demoTemplate" + System.currentTimeMillis() + ".xlsx";

        String templateFilePath = "template/demoTemplate.xlsx";

        InputStream templateInputStream = getTemplateInputStream(templateFilePath);

        FesodSheet.write(file)
                .withTemplate(templateInputStream)
                .sheet()
                .doFill(data());
    }


}
