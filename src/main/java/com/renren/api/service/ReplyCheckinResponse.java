/**
 * Autogenerated by renren-api2-generator 2014-04-23 18:03:22
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.renren.api.service;
import java.util.*;
/**
 *
 * 回复签到结果
 */
public class ReplyCheckinResponse {
	
	/**
	 * 操作结果code 成功=0,字数超常=1，敏感词=2，参数不完整=3，回复失败=4，相同内容=5，报道删除=6，隐私设置不允许=16
	 */
	private int code;
	/**
	 * 回复id
	 */
	private long id;
	/**
	 * 回复内容
	 */
	private String replyContent;
	/**
	 * 回复时间
	 */
	private String replyTime;
	/**
	 * 回复者头像
	 */
	private String replyerHeader;
	/**
	 * 回复者id
	 */
	private long replyerId;
	/**
	 * 回复者姓名
	 */
	private String replyerName;
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return this.code;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return this.id;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyContent() {
		return this.replyContent;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyTime() {
		return this.replyTime;
	}
	public void setReplyerHeader(String replyerHeader) {
		this.replyerHeader = replyerHeader;
	}
	public String getReplyerHeader() {
		return this.replyerHeader;
	}
	public void setReplyerId(long replyerId) {
		this.replyerId = replyerId;
	}
	public long getReplyerId() {
		return this.replyerId;
	}
	public void setReplyerName(String replyerName) {
		this.replyerName = replyerName;
	}
	public String getReplyerName() {
		return this.replyerName;
	}
}
