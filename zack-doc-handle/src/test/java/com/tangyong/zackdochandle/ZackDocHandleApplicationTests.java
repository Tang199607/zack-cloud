package com.tangyong.zackdochandle;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Map;

@SpringBootTest
class ZackDocHandleApplicationTests {

    @Test
    void contextLoads() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = "2021-12-04 14:10:12";
    }

    @Test
    void parseJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //String json = "{\"data_type\": \"append\",\"data\": [{\"BusinessLevel\": \"1\",\"BusTime\": \"2023-04-24 15:29:55.0\",\"GuestName\": \"测试\",\"GuestNo\": \"SW02\",\"TransportCountry\": \"null\",\"GuaranteeType\": \"随机报关\",\"BusinessUserWorkNo\": \"sceadmin\",\"LoadType\": \"null\",\"memo\": \"\",\"deliveryItems\": [{\"rowIndex\": \"1\",\"deliveryBusCode\": \"WMS3000721150\"},{\"rowIndex\": \"2\",\"deliveryBusCode\": \"WMS3000721150\"}],\"boxItems\": [{\"rowIndex\": \"1\",\"boxNo\": \"YSD202304240072\",\"weight_M\": \"405\",\"weight\": \"N\",\"length\": \"10\",\"width\": \"20\",\"height\": \"30\",\"boxType\": \"null\",\"boxInfo\": [{\"rowIndex\": \"1\",\"containBoxNo\": \"304020001086.4\",\"packageAmount\": \"0\"},{\"rowIndex\": \"2\",\"containBoxNo\": \"819851320000.4\",\"packageAmount\": \"0\"}] }] }]}";
        String json = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        System.out.println(objectMapper.readValue(json, Map.class));
    }

}
