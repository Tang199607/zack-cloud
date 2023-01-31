package com.tangyong.service.impl;


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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
            /*String insertSql =
                    "insert into inventory (sku, box, sn, qty, uom, loc, lot01, lot04) values (?,?,?,?,?,?,?,?)";*/
            String insertSql =
                    "insert into C_RECEIPTDETAIL_TEST (sku, box, sn, qty, uom, loc, lot01, lot04) values (?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(insertSql);
            for (Inventory inventory : cachedDataList) {
                String sku = inventory.getSku();
                String box = inventory.getBox();
                String sn = inventory.getSn();
                Double qty = inventory.getQty();
                String uom = inventory.getUom();
                String loc = inventory.getLoc();
                String lot01 = inventory.getLot01();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN);
                Date lot04 = inventory.getLot04();
                ps.setString(1, sku);
                ps.setString(2, box);
                ps.setString(3, sn);
                ps.setDouble(4, qty);
                ps.setString(5, uom);
                ps.setString(6, loc);
                ps.setString(7, lot01);
                ps.setString(8, simpleDateFormat.format(lot04));
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

    @Override
    public List<Inventory> listInventory() {
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Inventory::getSerialkey, 1);
        return this.list(queryWrapper);
    }
}




