package com.gsoft.widget.willdo.dto;

import com.gsoft.cos3.dto.BaseDto;

/**
 * 统一待办设置表
 *
 */
public class WilldoSettingDto  extends BaseDto {

	private static final long serialVersionUID = -5529252807120233494L;

	/**
	 * 来源（应用系统）
	 */
	private String srcCode;
	/**
	 * 来源（应用系统）名称
	 */
	private String srcName;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 排序号
	 */
	private int sort;
	
	/**
	 * 状态  1启用，0停用
	 */
	private int status;
	
	/**
	 * 类型：0：仅按时间排序 / 1：按来源排序
	 */
	private int c_type;

	public String getSrcName() {
		return srcName;
	}

	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	public String getSrcCode() {
		return srcCode;
	}

	public void setSrcCode(String srcCode) {
		this.srcCode = srcCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getC_type() {
		return c_type;
	}

	public void setC_type(int c_type) {
		this.c_type = c_type;
	}
	

}
