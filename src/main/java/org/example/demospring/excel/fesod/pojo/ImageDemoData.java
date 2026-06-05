package org.example.demospring.excel.fesod.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.apache.fesod.sheet.annotation.write.style.ColumnWidth;
import org.apache.fesod.sheet.annotation.write.style.ContentRowHeight;
import org.apache.fesod.sheet.converters.string.StringImageConverter;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @ClassName ImageDemoData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/5 10:04
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(100)
@ColumnWidth(25)
public class ImageDemoData {
    private File file;
    private InputStream inputStream;
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;
    private byte[] byteArray;
    private URL url;
}