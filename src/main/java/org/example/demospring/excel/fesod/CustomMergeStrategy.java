package org.example.demospring.excel.fesod;

import org.apache.fesod.sheet.metadata.Head;
import org.apache.fesod.sheet.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @ClassName CustomMergeStrategy
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/5 9:59
 */
public class CustomMergeStrategy extends AbstractMergeStrategy {
    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        if (relativeRowIndex != null && relativeRowIndex % 2 == 0 && head.getColumnIndex() == 0) {
            // 注意：+1 假设只有一行表头。多行表头场景请使用 cell.getRowIndex() 代替。
            int startRow = relativeRowIndex + 1;
            int endRow = startRow + 1;
            sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 0, 0));
        }
    }
}