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
 * 新鲜事包含的资源
 */
public class FeedResource {
	
	/**
	 * 资源id
	 */
	private long id;
	/**
	 * 资源标题
	 */
	private String title;
	/**
	 * 资源的内容
	 */
	private String content;
	/**
	 * 资源的url地址
	 */
	private String url;
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return this.id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return this.content;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return this.url;
	}
}
