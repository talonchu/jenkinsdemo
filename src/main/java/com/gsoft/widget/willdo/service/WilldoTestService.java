package com.gsoft.widget.willdo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gsoft.widget.willdo.dto.WilldoSrcDto;

public interface WilldoTestService {
	
	public void saveSrc(WilldoSrcDto dto);
	
	public List<WilldoSrcDto> getSrcList();
	
	public void save(String srcCode, String service, String describe, Long operatorId, String operatorName, Date deadline,String url, Long personnelId, String personnelName);
	
	public void deleteSrc(Long id);
	
	List<Map<String,Object>> getUsers();
	
}
