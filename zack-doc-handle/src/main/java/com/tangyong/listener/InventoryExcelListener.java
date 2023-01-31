package com.tangyong.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.tangyong.model.Inventory;
import com.tangyong.service.InventoryService;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.Map;

/**
 * Excel listener.
 */
@Slf4j
public class InventoryExcelListener extends AnalysisEventListener<Inventory> {

    // 超过5w可能会内存溢出
    public static final int BATCH_COUNT = 30000;
    private List<Inventory> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private InventoryService inventoryService;

    public InventoryExcelListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * 每读到一条数据都会调用
     */
    @Override
    public void invoke(Inventory inventory, AnalysisContext context) {
        cachedDataList.add(inventory);
        if (cachedDataList.size() > BATCH_COUNT) {
            // 存入数据库
            saveData(cachedDataList);
            // 清空缓存list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 读取完成后的操作
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //再调用一下，防止没有导入完
        saveData(cachedDataList);
        log.info("所有行读取完毕");
    }

    /**
     * 批量存入数据库
     *
     * @param cachedDataList 缓存列表
     * @return Map
     */
    private Map<String, Object> saveData(List<Inventory> cachedDataList) {
        return inventoryService.import2DbByJdbcFromExcel(cachedDataList);
    }
}
