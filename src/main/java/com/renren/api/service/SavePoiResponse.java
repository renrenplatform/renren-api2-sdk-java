/**
 * Autogenerated by renren-api2-generator 2013-11-21 17:47:04
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.renren.api.service;
import java.util.*;
/**
 *
 * 签到结果
 */
public class SavePoiResponse {
	
	/**
	 * 操作结果code
	 */
	private int code;
	/**
	 * 操作结果信息
	 */
	private String msg;
	/**
	 * pid
	 */
	private String pid;
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return this.code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return this.msg;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPid() {
		return this.pid;
	}
}