package org.example.demospring.excel.fesod;

import org.apache.fesod.sheet.enums.CellDataTypeEnum;
import org.apache.fesod.sheet.metadata.Head;
import org.apache.fesod.sheet.metadata.data.WriteCellData;
import org.apache.fesod.sheet.write.metadata.holder.WriteSheetHolder;
import org.apache.fesod.sheet.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomCellWriteWidthStyleStrategy
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/9 10:57
 */
public class CustomCellWriteWidthStyleStrategy extends AbstractColumnWidthStyleStrategy {

    // 缓存每列的最大宽度
    private final Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        boolean isNotEmpty = false;
        // 获取当前列的索引
        int columnWidth = 0;

        for (WriteCellData<?> data : cellDataList) {
            if (data.getType() == CellDataTypeEnum.STRING) {
                // 如果是字符串，计算字节长度（中文占2个字节，英文占1个）
                String str = data.getStringValue();
                if (str != null) {
                    // 将换行符替换掉再计算，防止换行导致宽度计算异常
                    str = str.replaceAll("\r\n", "").replaceAll("\n", "");
                    columnWidth = Math.max(columnWidth, str.getBytes().length);
                    isNotEmpty = true;
                }
            } else {
                // 非字符串（数字、日期等），给一个默认的基础宽度
                columnWidth = Math.max(columnWidth, data.getStringValue() == null ? 10 : data.getStringValue().getBytes().length);
                isNotEmpty = true;
            }
        }

        // 如果是表头，也要参与计算
        if (isHead) {
            String headName = head.getFieldName();
            if (headName != null) {
                columnWidth = Math.max(columnWidth, headName.getBytes().length);
                isNotEmpty = true;
            }
        }

        if (isNotEmpty) {
            // 获取当前 sheet 的列宽缓存
            Map<Integer, Integer> maxColumnWidthMap = cache.computeIfAbsent(writeSheetHolder.getSheetNo(), k -> new HashMap<>());

            // 记录当前列目前的最大宽度
            Integer maxColumnWidth = maxColumnWidthMap.get(cell.getColumnIndex());
            if (maxColumnWidth == null || columnWidth > maxColumnWidth) {
                maxColumnWidthMap.put(cell.getColumnIndex(), columnWidth);

                // 【核心调整逻辑】
                // 1. 预留 2 个字符的边距，防止贴着单元格边缘
                // 2. 设置最小列宽为 10，防止太短
                // 3. 设置最大列宽为 50，防止超长文本（如几百字的备注）撑爆表格
                int finalWidth = Math.min(Math.max(columnWidth + 2, 10), 50);

                // POI 设置列宽的单位是 1/256 个字符宽度，所以要乘以 256
                Sheet sheet = writeSheetHolder.getSheet();
                sheet.setColumnWidth(cell.getColumnIndex(), finalWidth * 256);
            }
        }
    }
}
