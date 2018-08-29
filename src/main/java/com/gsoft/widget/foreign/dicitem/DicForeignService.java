package com.gsoft.widget.foreign.dicitem;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsoft.widget.foreign.FeignClientConfiguration;

@FeignClient(value = "cos3-system-manager", configuration = FeignClientConfiguration.class)
public interface DicForeignService {
	 /**
	  * 根据key获取字典信息
	  * @param id
	  * @return
	  */
	@RequestMapping(value = "/dictionary/getDicItemListByDicKey", method = RequestMethod.GET)
	public List<DictionaryItemDto> getDicItemListByDicKey(@RequestParam("dicKey") String dicKey);
}
