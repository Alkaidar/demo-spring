package org.example.demospring;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.pojo.FillData;
import org.example.demospring.excel.fesod.pojo.TemplateData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class FillTests {

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


    @Test
    public void simpleFill() throws Exception {

        String templateFilePath = "template/simpleTemplate.xlsx";


        FillData fillData = new FillData();
        fillData.setName("张三");
        fillData.setNumber(5.2);
        FesodSheet.write("simpleFill.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .sheet()
                .doFill(fillData);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("number", 5.2);
        FesodSheet.write("simpleFillMap.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .sheet()
                .doFill(map);
    }

    private static InputStream getTemplateInputStream(String templateFilePath) throws Exception {
        ClassPathResource resource = new ClassPathResource(templateFilePath);

        if (!resource.exists()) {
            throw new RuntimeException("模板文件不存在");
        }

        return resource.getInputStream();
    }

}
