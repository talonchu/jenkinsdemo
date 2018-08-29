package com.gsoft.widget.willdo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gsoft.cos3.entity.BaseEntity;

/**
 * 统一待办设置表
 *
 */
@Entity
@Table(name = "t_willdo_setting")
public class WilldoSettingEntity  extends BaseEntity {

	private static final long serialVersionUID = 1651040196893242108L;

	/**
	 * 来源（应用系统）
	 */
	@Column(name = "c_src_code", length = 30)
	private String srcCode;
	
	/**
	 * 用户id
	 */
	@Column(name = "c_user_id", length = 9)
	private Long userId;
	
	/**
	 * 排序号
	 */
	@Column(name = "c_sort", length = 3)
	private int sort;
	
	/**
	 * 状态  1启用，0停用
	 */
	@Column(name = "c_status", length = 1)
	private int status;
	
	/**
	 * 类型：0：仅按时间排序 / 1：按来源排序
	 */
	@Column(name = "c_type", length = 1)
	private int type;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	

}
