package com.gsoft.Exception;

public class EmailExceptionMsg {
	
	/**
	 * 邮件服务器异常！
	 */
	public static final String MAIL_SERVER_EXCEPTION = "邮件服务器异常";
	
	/**
	 * 没有找到pop3邮件服务器！
	 */
	public static final String NO_POP3_SERVER = "没有找到pop3邮件服务器";
	
	/**
	 * 邮件消息体异常！
	 */
	public static final String MAIL_MESSAGE_ERROR = "邮件消息体异常";
	
	/**
	 * 邮件服务器错误
	 */
	public static final String MAIL_SERVER_ERROR = "邮件服务器错误";
	
	/**
	 * 邮件账号或密码错误
	 */
	public static final String MAIL_ACCOUNT_OR_PWD_ERROR = "邮件账号或密码错误";
	
	/**
	 * smtp服务器连接异常,请检查用户名或授权信息或邮件服务器是否开启smtp功能
	 */
	public static final String SMTP_CONNECT_ERROR = "smtp服务器连接有误,请检查用户名或授权密码";
	
	/**
	 * pop3服务器连接异常,请检查用户名或授权信息或邮件服务器是否开启pop3功能
	 */
	public static final String POP3_CONNECT_ERROR = "pop服务器连接有误";

}
