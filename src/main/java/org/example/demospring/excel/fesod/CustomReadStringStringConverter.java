package org.example.demospring.excel.fesod;

import org.apache.fesod.sheet.converters.Converter;
import org.apache.fesod.sheet.converters.ReadConverterContext;
import org.apache.fesod.sheet.converters.WriteConverterContext;
import org.apache.fesod.sheet.enums.CellDataTypeEnum;
import org.apache.fesod.sheet.metadata.data.WriteCellData;

/**
 * @ClassName CustomStringStringConverter
 * @Description 自定义转换器
 * @Author wangzhipeng
 * @Date 2026/6/4 17:23
 */
public class CustomReadStringStringConverter implements Converter<String> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadConverterContext<?> context) throws Exception {
        return "自定义" + context.getReadCellData().getStringValue();
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) throws Exception {
        return new WriteCellData<>(context.getValue());
    }
}
