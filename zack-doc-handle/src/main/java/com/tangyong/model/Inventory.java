package com.tangyong.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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
//@TableName("inventory")
@TableName("C_RECEIPTDETAIL_TEST")
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
    @ExcelProperty(value = "box")
    private String box;
    /**
     * 内标签号
     */
    @ExcelProperty(value = "sn")
    private String sn;
    /**
     * 数量
     */
    @ExcelProperty(value = "qty")
    private Double qty;
    /**
     * 单位
     */
    @ExcelProperty(value = "uom")
    private String uom;
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
     * 日期
     */
    @ExcelProperty(value = "lot04")
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private Date lot04;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialkey=").append(serialkey);
        sb.append(", sku=").append(sku);
        sb.append(", box=").append(box);
        sb.append(", sn=").append(sn);
        sb.append(", qty=").append(qty);
        sb.append(", uom=").append(uom);
        sb.append(", loc=").append(loc);
        sb.append(", lot01=").append(lot01);
        sb.append(", lot04=").append(lot04);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}