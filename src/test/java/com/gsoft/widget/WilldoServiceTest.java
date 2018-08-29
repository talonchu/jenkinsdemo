package com.gsoft.widget;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gsoft.widget.willdo.entity.WilldoSrcEntity;
import com.gsoft.widget.willdo.persistence.WilldoSrcPersistence;
import com.gsoft.widget.willdo.service.WilldoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class WilldoServiceTest {
	@Resource
	private WilldoService willdoService;
	@Resource
	private WilldoSrcPersistence willdoSrcPersistence;
	
	@Test
	public void testSave() {
		willdoService.save("ykjh", "用款计划", "计划审批", 2L, "张三", new Date(), "", 1L, "测试");
	}
	@Test
	public void testSaveSrc() {
		WilldoSrcEntity entity = new WilldoSrcEntity();
		entity.setSrcCode("ykjh");
		entity.setSrcName("用款计划");
		willdoSrcPersistence.save(entity);
	}

}
