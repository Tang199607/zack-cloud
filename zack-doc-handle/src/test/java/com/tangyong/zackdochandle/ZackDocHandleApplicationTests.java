package com.tangyong.zackdochandle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

@SpringBootTest
class ZackDocHandleApplicationTests {

	@Test
	void contextLoads() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = "2021-12-04 14:10:12";
	}

}
