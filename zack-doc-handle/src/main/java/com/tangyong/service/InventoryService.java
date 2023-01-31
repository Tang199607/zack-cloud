package com.tangyong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangyong.model.Inventory;

import java.util.List;
import java.util.Map;

public interface InventoryService extends IService<Inventory> {

    Map<String, Object> import2DbByJdbcFromExcel(List<Inventory> cachedDataList);

    List<Inventory> listInventory();
}
