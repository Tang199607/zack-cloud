package com.tangyong.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * 库存表
 */
@Data
@EqualsAndHashCode
@TableName("YT_RELATION_ASN_20230401")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 流水号
     */
    @TableId(type = IdType.AUTO)
    private Integer serialkey;
    /**
     * 货品
     */
    @ExcelProperty(value = "STORERKEY")
    private String storerkey;
    /**
     * 箱号
     */
    @ExcelProperty(value = "POKEY")
    private String pokey;
    /**
     * 内标签号
     */
    @ExcelProperty(value = "POLINENUMBER")
    private String polinenumber;
    /**
     * 数量
     */
    @ExcelProperty(value = "PLAN_QTY")
    @NumberFormat
    private Double plan_qty;
    /**
     * 单位
     */
    @ExcelProperty(value = "EXTERNRECEIPTKEY")
    private String externreceiptkey;
    /**
     * 包装
     */
    @ExcelProperty(value = "EXTERNLINENO")
    private String externlineno;
    /**
     * 库位
     */
    @ExcelProperty(value = "SUPPLIER")
    private String supplier;
    /**
     * 逻辑仓
     */
    //@ExcelProperty(value = "PCLOTNO")
    //private String pclotno;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "BOX")
    private String box;

    @ExcelProperty(value = "SN")
    private String sn;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "SKU")
    private String sku;

    @Override
    public String toString() {
        return "Inventory{" +
                "serialkey=" + serialkey +
                ", storerkey='" + storerkey + '\'' +
                ", pokey='" + pokey + '\'' +
                ", polinenumber='" + polinenumber + '\'' +
                ", plan_qty=" + plan_qty +
                ", externreceiptkey='" + externreceiptkey + '\'' +
                ", externlineno='" + externlineno + '\'' +
                ", supplier='" + supplier + '\'' +
                ", box='" + box + '\'' +
                ", sn='" + sn + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }
}