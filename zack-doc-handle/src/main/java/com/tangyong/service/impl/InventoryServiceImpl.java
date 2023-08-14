package com.tangyong.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangyong.config.DruidDataSourceWrapper;
import com.tangyong.mapper.InventoryMapper;
import com.tangyong.model.Inventory;
import com.tangyong.service.InventoryService;
import com.tangyong.utils.JDBCDruidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Resource
    private DruidDataSourceWrapper dataSourceWrapper;

    @Override
    public Map<String, Object> import2DbByJdbcFromExcel(List<Inventory> cachedDataList) {
        Map<String, Object> result = new HashMap<>();
        if (cachedDataList.isEmpty()) {
            result.put("未处理数据", "0");
            return result;
        }
        // JDBC分批插入+事务操作完成对10w数据的插入
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //记录时间
            long startTime = System.currentTimeMillis();
            String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_PATTERN));
            log.info("开始导入-----------------开始导入时间：{}", start);
            connection = dataSourceWrapper.getConnection();
            // 设置为默认不提交
            connection.setAutoCommit(false);
            String insertSql = "insert into YT_RELATION_ASN_20230401 " +
                    "(storerkey, pokey, polinenumber, externreceiptkey, externlineno, supplier, box, sn, sku, plan_qty, SNSOURCE, pclotno) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ' ', ' ')";
            ps = connection.prepareStatement(insertSql);
            for (Inventory inventory : cachedDataList) {
                String storerkey = getString(inventory);
                String pokey = inventory.getPokey();
                String polinenumber = inventory.getPolinenumber();
                Double planQty = inventory.getPlan_qty();
                String externreceiptkey = inventory.getExternreceiptkey();
                String externlineno = inventory.getExternlineno();
                String supplier = inventory.getSupplier();
                if (StringUtils.isEmpty(pokey)) {
                    pokey = " ";
                }
                if (StringUtils.isEmpty(storerkey)) {
                    storerkey = "3000";
                }
                if (StringUtils.isEmpty(polinenumber)) {
                    polinenumber = " ";
                }
                if (planQty == null) {
                    planQty = 0.00;
                }
                if (StringUtils.isEmpty(externreceiptkey)) {
                    externreceiptkey = " ";
                }
                if (StringUtils.isEmpty(externlineno)) {
                    externlineno = " ";
                }
                if (StringUtils.isEmpty(supplier)) {
                    supplier = " ";
                }
                String box = inventory.getBox();
                if (StringUtils.isEmpty(box)) {
                    box = " ";
                }
                String sn = inventory.getSn();
                if (StringUtils.isEmpty(sn)) {
                    sn = " ";
                }
                String sku = inventory.getSku();
                if (StringUtils.isEmpty(sku)) {
                    sku = " ";
                }
                ps.setString(1, storerkey);
                ps.setString(2, pokey);
                ps.setString(3, polinenumber);
                ps.setString(4, externreceiptkey);
                ps.setString(5, externlineno);
                ps.setString(6, supplier);
                ps.setString(7, box);
                ps.setString(8, sn);
                ps.setString(9, sku);
                ps.setDouble(10, planQty);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            long endTime = System.currentTimeMillis();
            String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_PATTERN));
            log.info("导入完成-----------------------导入完成时间：{}", end);
            log.info("导入条数：{}----------------消耗时间：{}", cachedDataList.size(), (endTime - startTime) / 1000);
            result.put("插入数据成功", "1");
        } catch (Exception e) {
            result.put("插入数据失败", "-1");
            e.printStackTrace();
        } finally {
            JDBCDruidUtils.close(ps, connection);
        }
        return result;
    }

    private static String getString(Inventory inventory) {
        String storerkey = inventory.getStorerkey();
        return storerkey;
    }

    @Override
    public List<Inventory> listInventory() {
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Inventory::getSerialkey, 183);
        return this.list(queryWrapper);
    }
}




