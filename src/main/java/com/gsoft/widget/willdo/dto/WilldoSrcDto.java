package com.gsoft.widget.willdo.dto;

import com.gsoft.cos3.dto.BaseDto;

/**
 * 统一待办来源系统表
 *
 */
public class WilldoSrcDto  extends BaseDto {

	private static final long serialVersionUID = -6265747809013335865L;
	
	/**
	 * 来源（应用系统）名
	 */
	private String srcName;
	
	/**
	 * 来源（应用系统）简称
	 */
	private String srcCode;
	
	/**
	 * 应用页面地址
	 */
	private String basePath;

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

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
	

}
