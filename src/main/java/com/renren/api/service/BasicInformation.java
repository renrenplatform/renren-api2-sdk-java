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
 * 基本信息
 */
public class BasicInformation {
	
	/**
	 * 用户性别
	 */
	private Sex sex;
	/**
	 * 用户生日，格式为'yyyy-mm-dd'或'y0后-mm-dd'
	 */
	private String birthday;
	/**
	 * 家乡信息
	 */
	private HomeTown homeTown;
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Sex getSex() {
		return this.sex;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return this.birthday;
	}
	public void setHomeTown(HomeTown homeTown) {
		this.homeTown = homeTown;
	}
	public HomeTown getHomeTown() {
		return this.homeTown;
	}
}