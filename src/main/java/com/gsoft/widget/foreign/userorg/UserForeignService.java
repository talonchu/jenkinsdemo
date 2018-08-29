package com.gsoft.widget.foreign.userorg;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsoft.widget.foreign.FeignClientConfiguration;


@FeignClient(value = "cos3-system-manager", configuration = FeignClientConfiguration.class)
public interface UserForeignService {
	
	 @RequestMapping(value = "/personnel/getPersonsByOrgId", method = RequestMethod.GET)
	 public List<PersonnelDto> getAllPersonByOrgId(@RequestParam("orgId") Long orgId);

	 @RequestMapping(value = "/personnel/getAllPerson", method = RequestMethod.GET)
	 public List<PersonnelDto> getAllPerson();
	 
	 @RequestMapping(value = "/personnel/select/getOneById", method = RequestMethod.GET)
	 PersonnelDto getPersonnelById(@RequestParam(name="id") Long id);
}
