package org.example.demospring.excel.fesod.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName FillData
 * @Description TODO
 * @Author wangzhipeng
 * @Date 2026/6/5 15:16
 */

@Getter
@Setter
@EqualsAndHashCode
public class FillData {
    private String name;
    private double number;
    private Date date;
}
