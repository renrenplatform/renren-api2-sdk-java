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
 * 封装分享数及浏览数结果，值为-1表示获取不到对应数据
 */
public class ShareViewCount {
	
	/**
	 * 分享数
	 */
	private int shareCount;
	/**
	 * 浏览数
	 */
	private int viewCount;
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public int getShareCount() {
		return this.shareCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getViewCount() {
		return this.viewCount;
	}
}
