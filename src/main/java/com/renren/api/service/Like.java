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
 * 兴趣
 */
public class Like {
	
	/**
	 * 喜欢的类型
	 */
	private LikeCatagory catagory;
	/**
	 * 喜欢的东西
	 */
	private String name;
	public void setCatagory(LikeCatagory catagory) {
		this.catagory = catagory;
	}
	public LikeCatagory getCatagory() {
		return this.catagory;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
