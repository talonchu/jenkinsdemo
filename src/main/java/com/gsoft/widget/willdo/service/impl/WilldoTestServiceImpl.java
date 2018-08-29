package com.gsoft.widget.willdo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsoft.cos3.jdbc.dao.BaseDao;
import com.gsoft.cos3.util.Assert;
import com.gsoft.cos3.util.BeanUtils;
import com.gsoft.cos3.util.JsonUtils;
import com.gsoft.cos3.util.MsgUtils;
import com.gsoft.widget.foreign.msgcache.MsgCacheForeign;
import com.gsoft.widget.willdo.dto.WilldoDto;
import com.gsoft.widget.willdo.dto.WilldoSrcDto;
import com.gsoft.widget.willdo.entity.WilldoEntity;
import com.gsoft.widget.willdo.entity.WilldoSrcEntity;
import com.gsoft.widget.willdo.persistence.WilldoPersistence;
import com.gsoft.widget.willdo.persistence.WilldoSrcPersistence;
import com.gsoft.widget.willdo.service.WilldoTestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WilldoTestServiceImpl implements WilldoTestService{
	@Resource
	private WilldoSrcPersistence willdoSrcPersistence;
	@Resource
	private WilldoPersistence willdoPersistence;
	@Resource
	private MsgCacheForeign msgCacheForeign;
	
 	@Resource
 	private BaseDao baseDao;
	
	@Value("${gsoft.msgcenter.server}")
	private String msgServer;
	@Value("${gsoft.msgcenter.moudule.willdo.key}")
	private String moudulekey;

	@Override
	public void saveSrc(WilldoSrcDto dto) {
		willdoSrcPersistence.save(BeanUtils.convert(dto, WilldoSrcEntity.class));
		
	}

	@Override
	public List<WilldoSrcDto> getSrcList() {
		return BeanUtils.convert(willdoSrcPersistence.findAll(), WilldoSrcDto.class);
	}

	@Override
	public void save(String srcCode, String service, String describe, Long operatorId, String operatorName,
			Date deadline, String url, Long personnelId, String personnelName) {
		Date now =  new Date();
		WilldoEntity entity = new WilldoEntity();
		entity.setSrcCode(srcCode);  //这里还要把srcName保存下来,否则前台展示的时候拿不到来源名字

		entity.setService(service);
		entity.setDescribe(describe);
		entity.setOperatorId(operatorId);
		entity.setOperatorName(operatorName);
		entity.setArrivalTime(now);
		entity.setDeadline(deadline);
		entity.setUrl(url);
		entity.setCreateBy(personnelId);
		entity.setCreateTime(now);
		entity.setNextOperatorId(personnelId);
		entity.setNextOperatorName(personnelName);
		entity.setStatus(0);
		entity = willdoPersistence.save(entity);
		long cur = System.currentTimeMillis();
		System.out.println("当前时间：" + System.currentTimeMillis());

		WilldoDto dto = BeanUtils.convert(entity, WilldoDto.class);
		//根据来源得到来源名称
		WilldoSrcEntity srcDto = willdoSrcPersistence.findByCode(dto.getSrcCode());
		if(Assert.isNotEmpty(srcDto)){
			dto.setSrcName(srcDto.getSrcName());
		}
		Map<Long,Object> data = new HashMap<>();
		data.put(dto.getId(), dto);

		String dataStr = "";
		try {
			dataStr = JsonUtils.toJson(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		msgCacheForeign.saveBadgeInfo(moudulekey, String.valueOf(operatorId), dataStr);
		MsgUtils.noticeMsgCenter(msgServer, moudulekey,String.valueOf(operatorId),false);
		System.out.println("当前时间：" + (System.currentTimeMillis() -cur));
		
	}

	@Override
	public void deleteSrc(Long id) {
		willdoSrcPersistence.delete(id);
	}

	@Override
	public List<Map<String, Object>> getUsers() {
		return baseDao.query("select * from cos_sys_personnel", new HashMap<String,Object>());
	}

}
