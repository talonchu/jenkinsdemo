package com.gsoft.widget.willdo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsoft.cos3.jdbc.dao.BaseDao;
import com.gsoft.cos3.util.Assert;
import com.gsoft.cos3.util.BeanUtils;
import com.gsoft.cos3.util.JsonUtils;
import com.gsoft.cos3.util.MsgUtils;
import com.gsoft.widget.foreign.msgcache.MsgCacheForeign;
import com.gsoft.widget.willdo.dto.SrcSort;
import com.gsoft.widget.willdo.dto.WilldoDto;
import com.gsoft.widget.willdo.entity.Test;
import com.gsoft.widget.willdo.entity.WilldoEntity;
import com.gsoft.widget.willdo.entity.WilldoSrcEntity;
import com.gsoft.widget.willdo.persistence.TestPersistence2;
import com.gsoft.widget.willdo.persistence.WilldoPersistence;
import com.gsoft.widget.willdo.persistence.WilldoSettingPersistence;
import com.gsoft.widget.willdo.persistence.WilldoSrcPersistence;
import com.gsoft.widget.willdo.service.WilldoService;

@Service
public class WilldoServiceImpl implements WilldoService{
	
	@Resource
	WilldoPersistence willdoPersistence;
	@Resource
	WilldoSrcPersistence willdoSrcPersistence;
	@Resource
	WilldoSettingPersistence willdoSettingPersistence;
	@Resource
	TestPersistence2 testPersistence2;
	@Resource
	BaseDao baseDao;
	
	@Resource
	private MsgCacheForeign msgCacheForeign;
	
	@Value("${gsoft.msgcenter.server}")
	private String msgServer;
	@Value("${gsoft.msgcenter.moudule.willdo.key}")
	private String moudulekey;

	@Override
	public WilldoDto save(String srcCode, String service, String describe, Long operatorId, String operatorName,
			Date deadline, String url, Long personnelId, String personnelName) {
		Date now =  new Date();
		WilldoEntity entity = new WilldoEntity();
		entity.setSrcCode(srcCode);
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
		WilldoEntity reEntity = willdoPersistence.save(entity);
		
		WilldoDto dto = BeanUtils.convert(reEntity, WilldoDto.class);
		
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
		return dto;
	}

	@Override
	public boolean checkSrc(String srcCode) {
		WilldoSrcEntity entity = willdoSrcPersistence.findByCode(srcCode);
		return Assert.isNotEmpty(entity);
	}

	@Override
	public void changeStatus(String ids, int status,Long personnelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("C_STATUS", status);
		map.put("C_UPDATE_BY", personnelId);
		map.put("C_UPDATE_TIME", new Date());
		String[] id = ids.split(",");
		baseDao.modify("t_global_willdo", "C_ID", id, map);
		msgCacheForeign.modifyBadgeInfo(moudulekey, String.valueOf(personnelId), ids);
		MsgUtils.noticeMsgCenter(msgServer, moudulekey,String.valueOf(personnelId),false);
	}

	@Override
	public List<Map<String, Object>> getWilldoList(Integer status,String src, String widgetid, Long personnelId) {
		//查询所有待办
		Map<String, Object> map = new HashMap<>();
		map.put("personnelId", personnelId);
		map.put("src", src);
		String sql = "select a.*,b.c_src_name as c_src_name from t_global_willdo a,t_willdo_src b where a.c_src_code = b.c_src_code ";
		sql += " and a.c_operator_id = ${personnelId}";
		sql += " and a.c_src_code = ${src}";
		if(Assert.isNotEmpty(status)){
			sql += " and a.c_status = ${status}";
			map.put("status", status);
		}else{
			sql += " and a.c_status <> ${status}";
			map.put("status", "0");
		}
		sql += " ORDER BY a.c_arrival_time desc";
		List<Map<String, Object>> list = baseDao.query(sql, map);
		//查询偏好设置
		Map<String, Object> sortMap = new HashMap<>();
		String sortSql = "select c_json as json from cos_custom_profile where 1=1";
		sortMap.put("personnelId", personnelId);
		sortSql += " and c_user_id = ${personnelId}";
		sortMap.put("widgetid", widgetid);
		sortSql += " and c_widget_uu_id = ${widgetid}";
		List<Map<String, Object>> sortList = baseDao.query(sortSql, sortMap);
		//按偏好设置排序
		if(Assert.isNotEmpty(sortList)){
			List<WilldoSrcEntity> allSrc = getSrcList();
			List<SrcSort> slist = null;
			String resource = "";
			try {
				Map<String, String> fmap = JsonUtils.fromJsonMap(sortList.get(0).get("json").toString(), String.class, String.class);
				resource = fmap.get("resource");
				if(Assert.isEmpty(fmap.get("srcSort"))){
					return list;
				}
				slist = JsonUtils.fromJsonList(fmap.get("srcSort"), SrcSort.class);
				//对偏好设置的来源插入未设置的来源
				for(WilldoSrcEntity w : allSrc){
					boolean flag = true;
					for(SrcSort s : slist){
						if(w.getSrcCode().equals(s.getSrcCode())){
							flag = false;
							break;
						}
					}
					if(flag){
						SrcSort n = new SrcSort();
						n.setSort(1);
						n.setSrcCode(w.getSrcCode());
						n.setSrcName(w.getSrcName());
						slist.add(n);
					}
				}
				//对来源排序
				Collections.sort(slist, new Comparator<SrcSort>() {  
					@Override  
		            public int compare(SrcSort o1, SrcSort o2) {  
		                int i = o1.getSort() - o2.getSort();  
		                return i;  
		            }  
		        });  
			} catch (IOException e) {
				e.printStackTrace();
			}
			if("1".equals(resource)){	//1是按来源排序
				if(Assert.isNotEmpty(slist)){
					List<Map<String, Object>> returnList = new ArrayList<>();
					for(SrcSort s : slist){
						for(Map<String, Object> m : list){
							if(s.getSrcCode().equals(m.get("C_SRC_CODE"))){
								returnList.add(m);
							}
						}
					}
					return returnList;
				}
			}
		}
		
		return list;
	}

	@Override
	public List<WilldoSrcEntity> getSrcList() {
		return willdoSrcPersistence.findAll();
	}

	@Override
	public int getTotal(Integer status, Long personnelId) {
		Map<String, Object> map = new HashMap<>();
		String sql = "select count(*) as count from t_global_willdo where 1=1";
		map.put("personnelId", personnelId);
		sql += " and c_operator_id = ${personnelId}";
		
		if(Assert.isNotEmpty(status)){
			map.put("status", status);
			sql += " and c_status = ${status}";
		}
		List<Map<String, Object>> countlist = baseDao.query(sql, map);
		return Integer.parseInt( countlist.get(0).get("COUNT").toString());
	}

	@Override
	public List<Map<String, Object>> getTotalBySrc(String src, Integer status, Long personnelId) {
		String sql = "select c_src_code , count(*) count FROM t_global_willdo where 1=1";
		Map<String, Object> map = new HashMap<>();
		map.put("personnelId", personnelId);
		sql += " and c_operator_id = ${personnelId}";
		map.put("status", status);
		sql += " and c_status = ${status}";
		map.put("src", src);
		sql += " and c_src_code = ${src}";
		sql += " group by c_src_code;";
		
		List<Map<String, Object>> clist = baseDao.query(sql, map);
		List<WilldoSrcEntity> srcList = willdoSrcPersistence.findAll();
		List<Map<String, Object>> relist = new ArrayList<>();
		for(WilldoSrcEntity s: srcList){
			Map<String, Object> n = new HashMap<>();
			n.put("secCode", s.getSrcCode());
			n.put("srcName", s.getSrcName());
			n.put("count", 0);
			for(Map<String, Object> c : clist){
				if(s.getSrcCode().equals(c.get("C_SRC_CODE").toString())){
					n.put("count", c.get("COUNT"));
				}
			}
			relist.add(n);
		}
		return relist;
	}

	@Override
	public String ttt(String params) {
		Test tt = new Test();
		tt.setC_codes(params);
		tt.setC_names(params+"00000000000000000000000");
		testPersistence2.save(tt);
		return "保存成功！";
	}

//	@Override
//	public List<Map<String, Object>> changeType(int type, Long personnelId) {
//		List<WilldoSettingEntity> list = willdoSettingPersistence.findByPid(personnelId);
//		for(WilldoSettingEntity e : list){
//			e.setType(type);
//		}
//		willdoSettingPersistence.save(list);
//		return getSetting(personnelId);
//	}

//	@Override
//	public List<Map<String, Object>> changeSort(String codes, String sorts, Long personnelId) {
//		List<WilldoSettingEntity> list = willdoSettingPersistence.findByPid(personnelId);
//		String[] cs = codes.split(",");
//		String[] ss = sorts.split(",");
//		Map<String, Object> map = new HashMap<>();
//		for(int i=0;i<cs.length;i++){
//			map.put(cs[i], ss[i]);
//		}
//		List<Map<String, Object>> plist = new ArrayList<>();
//				
//		for(WilldoSettingEntity e : list){
//			Map<String, Object> pmap = new HashMap<>();
//			pmap.put("C_ID", e.getId());
//			pmap.put("C_SORT", map.get(e.getSrcCode()));
//			plist.add(pmap);
//		}
//		baseDao.modify("t_willdo_setting", "C_ID", plist);
//		return getSetting(personnelId);
//	}
//
//	@Override
//	public List<Map<String, Object>> getSetting(Long personnelId) {
////		List<WilldoSettingEntity> list = willdoSettingPersistence.findByPid(personnelId);
//		
//		Map<String, Object> p = new HashMap<>();
//		String sql = "select a.*,b.c_src_name as srcName from t_willdo_setting a,t_willdo_src b where a.c_src_code=b.c_src_code";
//		p.put("personnelId", personnelId);
//		sql += " and a.c_user_id = ${personnelId}";
//		sql += " order by a.c_sort";
//		List<Map<String, Object>> list = baseDao.query(sql, p);
//		
//		
//		List<WilldoSrcEntity> srcList = getSrcList();
//		if(list.size() != srcList.size()){
//			List<Map<String, Object>> values = new ArrayList<>();
//			for(int i=0;i<srcList.size();i++){
//				Map<String, Object> map = new HashMap<>();
//				map.put("C_SRC_CODE", srcList.get(i).getSrcCode());
//				map.put("C_USER_ID", personnelId);
//				map.put("C_TYPE", 0);
//				map.put("C_SORT", i+1);
//				map.put("C_STATUS", 1);
//				values.add(map);
//			}
//			baseDao.insert("T_WILLDO_SETTING", "C_ID", values);
//			return baseDao.query(sql, p);
//		}
//		return list;
//	}


}
