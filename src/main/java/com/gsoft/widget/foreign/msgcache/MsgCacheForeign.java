package com.gsoft.widget.foreign.msgcache;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsoft.widget.foreign.FeignClientConfiguration;


@FeignClient(value = "cos3-system-manager", configuration = FeignClientConfiguration.class)
public interface MsgCacheForeign {
	@RequestMapping(value = "/syncData/saveBadgeInfo")
	public String saveBadgeInfo(@RequestParam("badge") String badge, @RequestParam("userId") String userId, @RequestParam("json") String json);
	
	@RequestMapping(value = "/syncData/modifyBadgeInfo")
	public String modifyBadgeInfo(@RequestParam("badge") String badge, @RequestParam("userId") String userId, @RequestParam("ids") String ids);

	@RequestMapping(value = "/syncData/coverBadgeInfo")
	public String coverBadgeInfo(@RequestParam("badge") String badge, @RequestParam("userId") String userId, @RequestParam("json") String json);

	@RequestMapping(value = "/syncData/getBadgeInfoJson")
	public String getBadgeInfoJson(@RequestParam("badge") String badge, @RequestParam("userId") String userId);
}
