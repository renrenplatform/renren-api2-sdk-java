package com.renren.api;

import java.util.Collection;
public class RennParam {
	private RennParam() {
		
	}

	public static String asString(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj.getClass().isArray()) {
			// 是数组
			Object[] objs = (Object[]) obj;
			StringBuilder sb = new StringBuilder();
			for (Object object : objs) {
				sb.append(object.toString() + ",");
			}
			return (sb.length() > 0) ? (sb.substring(0, sb.length() - 1)) : (sb
					.toString());
		} else if (obj instanceof Collection) {
			Collection<?> objs = (Collection<?>) obj;
			StringBuilder sb = new StringBuilder();
			for (Object object : objs) {
				sb.append(object.toString() + ",");
			}
			return (sb.length() > 0) ? (sb.substring(0, sb.length() - 1)) : (sb
					.toString());
		} else {
			return String.valueOf(obj);
		}
	}
	
	public static String asString(boolean b) {
		return String.valueOf(b);
	}
	public static String asString(char c) {
		return String.valueOf(c);
	}
	public static String asString(char [] data) {
		return String.valueOf(data);
	}
	public static String asString(double d) {
		return String.valueOf(d);
	}
	public static String asString(float f) {
		return String.valueOf(f);
	}
	public static String asString(int i) {
		return String.valueOf(i);
	}
	public static String asString(long l) {
		return String.valueOf(l);
	}
	public static String asString(char[] data, int offset,int count) {
		return String.valueOf(data,offset,count);
	}
}
