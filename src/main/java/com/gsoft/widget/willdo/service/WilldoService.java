package com.gsoft.widget.willdo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gsoft.widget.willdo.dto.WilldoDto;
import com.gsoft.widget.willdo.entity.WilldoSrcEntity;

public interface WilldoService {

	WilldoDto save(String srcCode, String service, String describe, Long operatorId, String operatorName, Date deadline,
			String url, Long personnelId, String personnelName);

	boolean checkSrc(String srcCode);

	void changeStatus(String ids, int status,Long personnelId);

	List<Map<String, Object>> getWilldoList(Integer status,String src,String widgetid, Long personnelId);

	List<WilldoSrcEntity> getSrcList();

	int getTotal(Integer status, Long personnelId);
	
	
	String ttt(String params);


	List<Map<String, Object>> getTotalBySrc(String src, Integer status, Long personnelId);

//	List<Map<String, Object>> changeType(int type, Long personnelId);

//	List<Map<String, Object>> changeSort(String codes, String sorts, Long personnelId);

//	List<Map<String, Object>> getSetting(Long personnelId);



}
