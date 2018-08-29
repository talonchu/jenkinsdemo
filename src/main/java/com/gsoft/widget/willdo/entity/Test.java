package com.gsoft.widget.willdo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gsoft.cos3.entity.BaseEntity;

/**
 * 测试列
 *
 */
@Entity
@Table(name = "t_willdo_test")
public class Test  extends BaseEntity {

	private static final long serialVersionUID = 6476219902180400759L;

	/**
	 * 来源（应用系统）
	 */
	@Column(name = "c_codes", length = 100)
	private String c_codes;
	
	/**
	 * 业务（所属功能模块）
	 */
	@Column(name = "c_names", length = 100)
	private String c_names;

	public String getC_codes() {
		return c_codes;
	}

	public void setC_codes(String c_codes) {
		this.c_codes = c_codes;
	}

	public String getC_names() {
		return c_names;
	}

	public void setC_names(String c_names) {
		this.c_names = c_names;
	}
}
