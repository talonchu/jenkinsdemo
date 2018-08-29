package com.gsoft.widget.willdo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gsoft.cos3.entity.BaseEntity;

/**
 * 统一待办表
 *
 */
@Entity
@Table(name = "t_global_willdo")
public class WilldoEntity  extends BaseEntity {

	private static final long serialVersionUID = 6476219902180400759L;

	/**
	 * 来源（应用系统）
	 */
	@Column(name = "c_src_code", length = 30)
	private String srcCode;
	
	/**
	 * 业务（所属功能模块）
	 */
	@Column(name = "c_service", length = 30)
	private String service;
	
	/**
	 * 待办描述信息
	 */
	@Column(name = "c_describe", length = 100)
	private String describe;
	
	/**
	 * 办理人
	 */
	@Column(name = "c_operator_id", length = 9)
	private Long operatorId;
	
	/**
	 * 办理人姓名
	 */
	@Column(name = "c_operator_name", length = 20)
	private String operatorName;
	
	/**
	 * 交办人
	 */
	@Column(name = "c_next_operator_id", length = 9)
	private Long nextOperatorId;
	
	/**
	 * 交办人姓名
	 */
	@Column(name = "c_next_operator_name", length = 20)
	private String nextOperatorName;
	
	/**
	 * 待办到达时间
	 */
	@Column(name = "c_arrival_time")
	private Date arrivalTime;
	
	/**
	 * 限办时间
	 */
	@Column(name = "c_deadline")
	private Date deadline;
	/**
	 * 业务链接URL
	 */
	@Column(name = "c_url", length = 100)
	private String url;
	/**
	 * 状态 0：待办，1：已办，2：未办
	 */
	@Column(name = "c_status", length = 3)
	private int status;
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
