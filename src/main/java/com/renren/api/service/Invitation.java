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
 * 邀请信息
 */
public class Invitation {
	
	/**
	 * 发送邀请用户id
	 */
	private long inviterId;
	/**
	 * 被邀请用户id
	 */
	private long inviteeId;
	/**
	 * 邀请类别
	 */
	private InvitationType invitationType;
	/**
	 * 礼物名称
	 */
	private String giftName;
	public void setInviterId(long inviterId) {
		this.inviterId = inviterId;
	}
	public long getInviterId() {
		return this.inviterId;
	}
	public void setInviteeId(long inviteeId) {
		this.inviteeId = inviteeId;
	}
	public long getInviteeId() {
		return this.inviteeId;
	}
	public void setInvitationType(InvitationType invitationType) {
		this.invitationType = invitationType;
	}
	public InvitationType getInvitationType() {
		return this.invitationType;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getGiftName() {
		return this.giftName;
	}
}