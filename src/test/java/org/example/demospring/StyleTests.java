package org.example.demospring;

import org.apache.fesod.common.util.BooleanUtils;
import org.apache.fesod.common.util.ListUtils;
import org.apache.fesod.sheet.FesodSheet;
import org.apache.fesod.sheet.write.handler.CellWriteHandler;
import org.apache.fesod.sheet.write.handler.context.CellWriteHandlerContext;
import org.apache.fesod.sheet.write.metadata.style.WriteCellStyle;
import org.apache.fesod.sheet.write.metadata.style.WriteFont;
import org.apache.fesod.sheet.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;
import org.example.demospring.excel.fesod.CustomCellStyleWriteHandler;
import org.example.demospring.excel.fesod.pojo.DemoData;
import org.example.demospring.excel.fesod.pojo.DemoStyleData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class StyleTests {

    private List<DemoStyleData> data() {
        List<DemoStyleData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoStyleData data = new DemoStyleData();
            data.setString("String" + i);
            data.setDate(new Date());
            data.setDoubleData(34.56789);
            list.add(data);
        }
        return list;
    }

    @Test
    public void converterWrite() {
        String fileName = "annotationStyleWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoStyleData.class)
                .sheet()
                .doWrite(data());
    }


    @Test
    public void handlerStyleWrite() {
        String fileName = "handlerStyleWrite" + System.currentTimeMillis() + ".xlsx";

        // 定义表头样式
        WriteCellStyle headStyle = new WriteCellStyle();
        headStyle.setFillForegroundColor(IndexedColors.RED.getIndex()); // 红色背景
        WriteFont headFont = new WriteFont();
        headFont.setFontHeightInPoints((short) 20); // 字体大小为20
        headStyle.setWriteFont(headFont);

        // 定义内容样式
        WriteCellStyle contentStyle = new WriteCellStyle();
        contentStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex()); // 绿色背景
        contentStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        WriteFont contentFont = new WriteFont();
        contentFont.setFontHeightInPoints((short) 20);
        contentStyle.setWriteFont(contentFont);

        // 使用策略设置样式
        HorizontalCellStyleStrategy styleStrategy =
                new HorizontalCellStyleStrategy(headStyle, contentStyle);

        FesodSheet.write(fileName, DemoData.class)
                .registerWriteHandler(styleStrategy)
                .sheet("样式模板")
                .doWrite(data());
    }


    @Test
    public void customCellStyleWrite() {
        String fileName = "customCellStyleWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoData.class)
                .registerWriteHandler(new CustomCellStyleWriteHandler())
                .sheet("自定义样式")
                .doWrite(data());
    }


    @Test
    public void poiStyleWrite() {
        String fileName = "poiStyleWrite" + System.currentTimeMillis() + ".xlsx";

        FesodSheet.write(fileName, DemoData.class)
                .registerWriteHandler(new CellWriteHandler() {
                    @Override
                    public void afterCellDispose(CellWriteHandlerContext context) {
                        if (BooleanUtils.isNotTrue(context.getHead())) {
                            Cell cell = context.getCell();
                            Workbook workbook = context.getWriteWorkbookHolder().getWorkbook();

                            // 创建并设置样式
                            CellStyle cellStyle = workbook.createCellStyle();
                            cellStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
                            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell.setCellStyle(cellStyle);
                        }
                    }
                })
                .sheet("POI样式")
                .doWrite(data());
    }

}
