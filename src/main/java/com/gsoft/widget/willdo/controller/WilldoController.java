package com.gsoft.widget.willdo.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsoft.cos3.dto.ReturnDto;
import com.gsoft.cos3.dto.SuccessDto;
import com.gsoft.cos3.util.MathUtils;
import com.gsoft.widget.willdo.service.WilldoService;

import io.swagger.annotations.ApiOperation;

/**
 * 统一待办
 *
 */
@RestController
@RequestMapping("/test")
public class WilldoController {
	
	@Resource
	WilldoService willdoService;
	
	@ApiOperation("测试")
	@RequestMapping(value = "/ttt", method = RequestMethod.GET)
	public ReturnDto ttt(@RequestParam String ids,HttpServletRequest request) {
		String rst = willdoService.ttt(ids);
		return new ReturnDto(200,"返回结果为："+rst);
	}
	
	/**
	 * 包含属性：来源（应用系统）、业务（所属功能模块）、待办描述信息、办理人、办理人姓名、交办人、交办人姓名、待办到达时间、限办时间、业务链接URL、状态
	 * @param path
	 * @param request
	 * @return
	 */
	@ApiOperation("保存待办")
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ReturnDto save(@RequestParam String srcCode,@RequestParam String service,@RequestParam String describe,
			@RequestParam Long operatorId,@RequestParam String operatorName,
			@RequestParam Date deadline,@RequestParam String url,@RequestParam(required=false) Long createId,@RequestParam String createName,HttpServletRequest request) {
		if(!willdoService.checkSrc(srcCode)){
			throw new RuntimeException("此系统未接入统一待办");
		}
		return new ReturnDto(200,willdoService.save(srcCode,service,describe,operatorId,operatorName,deadline,url,createId,createName));
	}
	
	/**
	 * 校验来源系统，为true时存在
	 * @param srcCode
	 * @return
	 */
	@ApiOperation("校验来源系统")
	@RequestMapping(value = "/checkSrc", method = RequestMethod.GET)
	public ReturnDto checkSrc(@RequestParam String srcCode){
		return  new ReturnDto(200,willdoService.checkSrc(srcCode));
	}
	/**
	 * 待办状态变更，0：待办，1：已办，2：未办
	 * @param ids
	 * @param status
	 * @param request
	 */
	@ApiOperation("待办状态变更")
	@RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
	public ReturnDto changeStatus(@RequestParam String ids,@RequestParam int status,@RequestParam(required=false) Long personnelId,HttpServletRequest request){
		willdoService.changeStatus(ids,status,personnelId);
		return new ReturnDto(200,new SuccessDto("保存成功"));
	}
	
	/**
	 * 待办列表
	 * @param src
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@ApiOperation("待办列表")
	@RequestMapping(value = "/getWilldoList", method = RequestMethod.GET)
	public ReturnDto getWilldoList(@RequestParam String src,@RequestParam String widgetid,HttpServletRequest request){
		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
		return new ReturnDto(200,willdoService.getWilldoList(0,src,widgetid,personnelId));
		
	}
	
	/**
	 * 来源列表
	 * @return
	 */
	@ApiOperation("来源列表")
	@RequestMapping(value = "/getSrcList", method = RequestMethod.GET)
	public ReturnDto getSrcList(){
		return new ReturnDto(200,willdoService.getSrcList());
	}
	
	/**
	 * 待办历史列表
	 * @param status
	 * @param src
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@ApiOperation("待办历史列表")
	@RequestMapping(value = "/getHistory", method = RequestMethod.GET)
	public ReturnDto getWilldoList(@RequestParam Integer status,@RequestParam String src,@RequestParam String widgetid,HttpServletRequest request){
		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
		return new ReturnDto(200,willdoService.getWilldoList(status,src,widgetid,personnelId));
		
	}
	/**
	 * 获取待办数量
	 * @param status
	 * @param request
	 * @return
	 */
	@ApiOperation("获取待办数量")
	@RequestMapping(value = "/getTotal", method = RequestMethod.GET)
	public ReturnDto getTotal(@RequestParam Integer status,HttpServletRequest request){
		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
		return new ReturnDto(200,willdoService.getTotal(status,personnelId));
	}
	
	/**
	 * 按来源统计待办数量
	 * @param src
	 * @param status
	 * @param request
	 * @return
	 */
	@ApiOperation("按来源统计待办数量")
	@RequestMapping(value = "/getTotalBySrc", method = RequestMethod.GET)
	public ReturnDto getTotalBySrc(@RequestParam String src,@RequestParam Integer status,HttpServletRequest request){
		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
		return new ReturnDto(200,willdoService.getTotalBySrc(src,status,personnelId));
		
	}
	
//	/**
//	 * 改变待办设置类型
//	 * @param type
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation("改变待办设置类型")
//	@RequestMapping(value = "/setting/changeType", method = RequestMethod.GET)
//	public ReturnDto changeType(@RequestParam int type,HttpServletRequest request) {
//		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
//		return new ReturnDto(200,willdoService.changeType(type,personnelId));
//	}
	
//	/**
//	 * 改变待办设置排序
//	 * @param codes
//	 * @param sorts
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation("改变待办设置排序")
//	@RequestMapping(value = "/setting/changeSort", method = RequestMethod.GET)
//	public ReturnDto changeSort(@RequestParam String codes,@RequestParam String sorts,HttpServletRequest request) {
//		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
//		return new ReturnDto(200,willdoService.changeSort(codes,sorts,personnelId));
//	}
	
//	/**
//	 * 改变待办设置排序
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation("改变待办设置排序")
//	@RequestMapping(value = "/setting/getSetting", method = RequestMethod.GET)
//	public ReturnDto getSetting(HttpServletRequest request){
//		Long personnelId = MathUtils.numObj2Long(request.getHeader("personnelId"));
//		return new ReturnDto(200,willdoService.getSetting(personnelId));
//	}
}

