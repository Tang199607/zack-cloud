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
@TableName("C_RECEIPTDETAIL")
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
    @ExcelProperty(value = "sku")
    private String sku;
    /**
     * 箱号
     */
    @ExcelProperty(value = "lpn")
    private String lpn;
    /**
     * 内标签号
     */
    @ExcelProperty(value = "sn")
    private String sn;
    /**
     * 数量
     */
    @ExcelProperty(value = "qty")
    @NumberFormat
    private Double qty;
    /**
     * 单位
     */
    @ExcelProperty(value = "uom")
    private String uom;
    /**
     * 包装
     */
    @ExcelProperty(value = "packkey")
    private String packkey;
    /**
     * 库位
     */
    @ExcelProperty(value = "loc")
    private String loc;
    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot01")
    private String lot01;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot02")
    private String lot02;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot03")
    private String lot03;

    /**
     * 日期
     */
    @ExcelProperty(value = "lot04")
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private Date lot04;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot05")
    private String lot05;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot06")
    private String lot06;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot07")
    private String lot07;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot08")
    private String lot08;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot09")
    private String lot09;

    /**
     * 逻辑仓
     */
    @ExcelProperty(value = "lot10")
    private String lot10;

    @Override
    public String toString() {
        return "Inventory{" +
                ", sku='" + sku + '\'' +
                ", lpn='" + lpn + '\'' +
                ", sn='" + sn + '\'' +
                ", qty=" + qty +
                ", uom='" + uom + '\'' +
                ", packkey='" + packkey + '\'' +
                ", loc='" + loc + '\'' +
                ", lot01='" + lot01 + '\'' +
                ", lot02='" + lot02 + '\'' +
                ", lot03='" + lot03 + '\'' +
                ", lot04=" + lot04 +
                ", lot05='" + lot05 + '\'' +
                ", lot06='" + lot06 + '\'' +
                ", lot07='" + lot07 + '\'' +
                ", lot08='" + lot08 + '\'' +
                ", lot09='" + lot09 + '\'' +
                ", lot10='" + lot10 + '\'' +
                '}';
    }
}