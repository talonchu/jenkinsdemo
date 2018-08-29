package com.gsoft.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gsoft.cos3.table.service.SingleTableService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SingleTableBatchSaveTest {
	@Resource
	private SingleTableService singleTableService;
	
	@Test
	public void testSave() {
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		for(int i=0;i<20;i++) {
			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("C_ID", null);
			map.put("C_LEVEL", 1);
			map.put("C_DESC", "主表测试"+i);
			map.put("C_AGE", 1+i);
			map.put("C_NAME", "主表" + i);
			map.put("C_TEST", "主表" + i);
			datas.add(map);
		}
		singleTableService.batchSave("t_zfk_test", datas);
	}

}
