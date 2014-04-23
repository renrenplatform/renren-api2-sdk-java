/**
 * Autogenerated by renren-api2-generator 2014-04-16 15:13:50
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.renren.api.service;
import java.util.*;
/**
 *
 * 人人网用户手机号码信息
 */
public class Phone {
	
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 经过RSA加密，使用Base64编码后的用户手机号码，可以先进行Base64解码，再使用[http://wiki.dev.renren.com/wiki/V2/rsakey/get /v2/rsakey/get]得到的公钥进行解密
	 */
	private String phoneNumber;
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserId() {
		return this.userId;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
}