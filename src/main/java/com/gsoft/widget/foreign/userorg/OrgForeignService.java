package com.gsoft.widget.foreign.userorg;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsoft.widget.foreign.FeignClientConfiguration;

@FeignClient(value = "cos3-system-manager", configuration = FeignClientConfiguration.class)
public interface OrgForeignService {
	 /**
	  * 根据id获取机构信息
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/org/select/getOneById", method = RequestMethod.GET)
	 public OrganizationDto getOneById(@RequestParam("id") Long id);

	//根据当前用户id和维度得到机构
	@RequestMapping(value = "/org/getOrgDtoByPersonnelIdAndDimension", method = RequestMethod.GET)
	List<OrganizationDto> getOrgDtoByPersonnelIdAndDimension(@RequestParam("personnelId") Long personnelId, @RequestParam("dimension") String dimension);
}
