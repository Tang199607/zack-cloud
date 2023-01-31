package com.tangyong.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.tangyong.listener.InventoryExcelListener;
import com.tangyong.model.Inventory;
import com.tangyong.service.InventoryService;
import common.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import utils.ResultUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


/**
 * excel处理
 *
 * @author TangYong
 */
@RestController
@RequestMapping("/excel")
public class ExcelHandleController {

    @Resource
    private InventoryService inventoryService;

    /**
     * 从excel导入（10w级别数据）
     */
    @RequestMapping("/import")
    public void import2DbFromExcel(@RequestParam("file") MultipartFile file) throws IOException {
        EasyExcelFactory.read(file.getInputStream(), Inventory.class, new InventoryExcelListener(inventoryService)).sheet().doRead();
    }

    @RequestMapping("/list")
    public BaseResponse listInventory() {
        List<Inventory> list = inventoryService.listInventory();
        return ResultUtils.successs(list);
    }
}
