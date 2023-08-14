package com.tangyong.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 解析相关
 *
 * @author TangYong
 * @date 2023年08月14日 16:44
 */
@RestController
@RequestMapping("/parse")
public class ParseController {

    @Resource
    ObjectMapper objectMapper;

    /**
     * json去除转义符号
     *
     * @return
     */
    @RequestMapping("/jsonParse")
    public String parseJson(String jsonStr) {
        return StringEscapeUtils.unescapeJson(jsonStr);
    }
}
