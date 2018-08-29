package com.gsoft.widget.willdo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gsoft.cos3.entity.BaseEntity;

/**
 * 统一待办来源系统表
 *
 */
@Entity
@Table(name = "t_willdo_src")
public class WilldoSrcEntity  extends BaseEntity {

	private static final long serialVersionUID = 1277160177867920500L;
	
	/**
	 * 来源（应用系统）名
	 */
	@Column(name = "c_src_Name", length = 30)
	private String srcName;
	
	/**
	 * 来源（应用系统）简称
	 */
	@Column(name = "c_src_code", length = 30)
	private String srcCode;
	
	/**
	 * 应用页面地址
	 */
	@Column(name = "c_base_path")
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
