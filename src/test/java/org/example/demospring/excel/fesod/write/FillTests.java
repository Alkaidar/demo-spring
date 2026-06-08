package org.example.demospring.excel.fesod.write;

import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.ExcelWriter;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.enums.WriteDirectionEnum;
import org.apache.fesod.sheet.write.metadata.WriteSheet;
import org.apache.fesod.sheet.write.metadata.fill.FillConfig;
import org.apache.fesod.sheet.write.metadata.fill.FillWrapper;
import org.example.demospring.excel.fesod.pojo.FillData;
import org.example.demospring.excel.fesod.pojo.TemplateData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.example.demospring.excel.fesod.utils.Util.getTemplateInputStream;

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

            String name = "zhangsan" + i;

            double chinese = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(60.0, 100.0))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            double math = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(60.0, 100.0))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            double english = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(60.0, 100.0))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            double number = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(60.0, 100.0))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            data.setName(name);
            data.setChinese(chinese);
            data.setMath(math);
            data.setEnglish(english);
            data.setNumber(number);

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

        // 方案1：基于对象填充
        FillData fillData = new FillData();
        fillData.setName("张三");
        fillData.setNumber(5.2);
        FesodSheet.write("simpleFill.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .sheet()
                .doFill(fillData);

        // 方案2：基于 Map 填充
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("number", 5.2);
        FesodSheet.write("simpleFillMap.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .sheet()
                .doFill(map);
    }

    @Test
    public void listFill() throws Exception {
        String templateFilePath = "template/demoTemplate.xlsx";

        // 方案1：一次性填充所有数据
        FesodSheet.write("listFill.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .sheet()
                .doFill(data());

        // 方案2：分批填充
        try (ExcelWriter excelWriter = FesodSheet.write("listFillBatch.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .build()) {
            WriteSheet sheet = FesodSheet.writerSheet().build();
            excelWriter.fill(data(), sheet);
            excelWriter.fill(data(), sheet);
        }
    }

    @Test
    public void complexFill() {
        String templateFilePath = "template/demoTemplate.xlsx";

        try (ExcelWriter writer = FesodSheet.write("complexFill.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .build()) {
            WriteSheet sheet = FesodSheet.writerSheet().build();

            FillConfig config = FillConfig.builder().forceNewRow(true).build();

            writer.fill(data(), config, sheet);

            Map<String, Object> map = new HashMap<>();
            map.put("date", new Date());
            map.put("total", 1000);
            writer.fill(map, sheet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @Description 这个大数据量填充，可能意思是生成新行填充的话大数据量情况下很慢
     * 这种是直接全部覆盖填充完之后填写统计行
     * @Author wangzhipeng
     * @Date 2026/6/8 10:34
     */
    @Test
    public void complexFillWithTable() {
        String templateFilePath = "template/demoTemplate.xlsx";

        try (ExcelWriter writer = FesodSheet.write("complexFillWithTable.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .build()) {
            WriteSheet writeSheet = FesodSheet.writerSheet().build();

            // 填充列表数据
            writer.fill(data(), writeSheet);

            // 填充其他变量
            Map<String, Object> map = new HashMap<>();
            map.put("date", "2024年11月20日");
            writer.fill(map, writeSheet);

            // 填充统计信息
            List<List<String>> totalList = new ArrayList<>();
            totalList.add(Arrays.asList(null, null, null, "统计: 1000"));
            writer.write(totalList, writeSheet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void horizontalFill() {
        String templateFilePath = "template/horizontalTemplate.xlsx";

        try (ExcelWriter writer = FesodSheet.write("horizontalFill.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .build()) {
            WriteSheet writeSheet = FesodSheet.writerSheet().build();

            FillConfig config = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();

            // 填充列表数据
            writer.fill(data(), config, writeSheet);

            // 填充其他变量
            Map<String, Object> map = new HashMap<>();
            map.put("date", new Date());
            writer.fill(map, writeSheet);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void compositeFill() {
        String templateFilePath = "template/compositeTemplate.xlsx";

        try (ExcelWriter writer = FesodSheet.write("compositeFill.xlsx")
                .withTemplate(getTemplateInputStream(templateFilePath))
                .build()) {
            WriteSheet writeSheet = FesodSheet.writerSheet().build();

            FillConfig config = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();

            // 使用 FillWrapper 进行多列表填充
            writer.fill(new FillWrapper("data1", data()), config, writeSheet);
            writer.fill(new FillWrapper("data2", data()), writeSheet);
            writer.fill(new FillWrapper("data3", data()), writeSheet);

            Map<String, Object> map = new HashMap<>();
            map.put("date", new Date());
            writer.fill(map, writeSheet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
