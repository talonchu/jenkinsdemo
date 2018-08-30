package com.gsoft.widget.willdo.controller;

import com.gsoft.cos3.dto.ReturnDto;
import com.gsoft.cos3.util.Assert;
import com.gsoft.cos3.util.MathUtils;
import com.gsoft.widget.willdo.dto.WilldoSrcDto;
import com.gsoft.widget.willdo.service.WilldoService;
import com.gsoft.widget.willdo.service.WilldoTestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/willdo/test")
public class TestController {
	@Resource
	private WilldoTestService willdoTestService;

	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource
	private WilldoService willdoService;
	
	@ApiOperation("查询")
	@RequestMapping(value = "/ttt", method = RequestMethod.GET)
	ReturnDto ttt(@RequestParam String ids,HttpServletRequest request) {
		ids = "返回的数据为："+ids;
		return new ReturnDto(200,"这是我的结果11111！！！！！！！！！！！！！！！！！！");
	}
	
	@ApiOperation("保存源")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	ReturnDto save(HttpServletRequest request, @RequestBody WilldoSrcDto srcDto) {
		willdoTestService.saveSrc(srcDto);
		return new ReturnDto(200,null);
	}
	
	@ApiOperation("待办列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	ReturnDto getWilldoList(HttpServletRequest request){
		return new ReturnDto(200,willdoTestService.getSrcList());
		
	}
	
	@ApiOperation("保存待办")
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	ReturnDto save(@RequestParam String srcCode,@RequestParam String service,@RequestParam String describe,
			@RequestParam Long operatorId,@RequestParam String operatorName,
			@RequestParam Date deadline,@RequestParam String url,@RequestParam(required=false) Long createId,@RequestParam String createName,HttpServletRequest request) {
		if(!willdoService.checkSrc(srcCode)){
			throw new RuntimeException("此系统未接入统一待办");
		}
		willdoService.save(srcCode, service, describe, operatorId, operatorName, deadline, url, createId, createName);
//		willdoTestService.save(srcCode,service,describe,operatorId,operatorName,deadline,url,createId,createName);
		return new ReturnDto(200,"待办发送成功");
	}
	
	@ApiOperation("办理")
	@RequestMapping(value = "/done", method = RequestMethod.GET)
	public ReturnDto changeStatus(@RequestParam String ids,HttpServletRequest request){
		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
		willdoService.changeStatus(ids,1,personnelId);
		return new ReturnDto(200,"办理成功");
	}
	
	@ApiOperation("删除源")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ReturnDto deleteSrc(@RequestParam Long id,HttpServletRequest request){
		willdoTestService.deleteSrc(id);
		return new ReturnDto(200,"删除成功");
	}
	
	@InitBinder  
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
           dateFormat.setLenient(false);  
           binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));  
        //initDataBinder(request, binder);  
    }  
	
	@ApiOperation("获取系统用户列表")
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ReturnDto deleteSrc(HttpServletRequest request){
		return new ReturnDto(200,willdoTestService.getUsers());
	}


	@ApiOperation("获取缓存的值")
	@RequestMapping(value = "/getCatch", method = RequestMethod.GET)
	public ReturnDto getCatch(HttpServletRequest request,@RequestParam String badge ){

		String personId = request.getHeader("PersonnelId");
		if (Assert.isEmpty(personId)) {
			personId = "1";
		}
		String oldJson = stringRedisTemplate.opsForValue().get(getRedisKey(personId, badge));

		return new ReturnDto(200,oldJson);
	}

	public String getRedisKey(String userId, String badge) {
		return userId + "_" + badge;
	}


}
