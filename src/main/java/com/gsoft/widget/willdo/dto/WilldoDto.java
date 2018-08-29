package com.gsoft.widget.willdo.dto;

import com.gsoft.cos3.dto.BaseDto;

import javax.persistence.Column;
import java.util.Date;

/**
 * 统一待办表
 *
 */
public class WilldoDto  extends BaseDto {

	private static final long serialVersionUID = -5804776424836978829L;

	/**
	 * 来源（应用系统）
	 */
	private String srcCode;

	private String srcName; //来源名称
	
	/**
	 * 业务（所属功能模块）
	 */
	private String service;
	
	/**
	 * 待办描述信息
	 */
	private String describe;
	
	/**
	 * 办理人
	 */
	private Long operatorId;
	
	/**
	 * 办理人姓名
	 */
	private String operatorName;
	
	/**
	 * 交办人
	 */
	private Long nextOperatorId;
	
	/**
	 * 交办人姓名
	 */
	private String nextOperatorName;
	
	/**
	 * 待办到达时间
	 */
	private Date arrivalTime;
	
	/**
	 * 限办时间
	 */
	private Date deadline;
	/**
	 * 业务链接URL
	 */
	@Column(name = "c_url", length = 100)
	private String url;
	/**
	 * 状态
	 */
	@Column(name = "c_status", length = 3)
	private int status;

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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Long getNextOperatorId() {
		return nextOperatorId;
	}
	public void setNextOperatorId(Long nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}
	public String getNextOperatorName() {
		return nextOperatorName;
	}
	public void setNextOperatorName(String nextOperatorName) {
		this.nextOperatorName = nextOperatorName;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
}
