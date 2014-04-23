package com.renren.api.mapper.types;

import java.lang.reflect.Field;

import com.renren.api.json.JSONObject;
import com.renren.api.mapper.JsonMappingException;

@SuppressWarnings("rawtypes")
public class BasicType extends Type {

	protected static final Class[] BASIC_TYPES = { int.class, Integer.class, Double.class, double.class, Float.class, float.class,
		Boolean.class, boolean.class, String.class, Long.class, long.class };
	
	private static BasicType instance;
	
	private BasicType() {
	}
	
	public static Type getInstance() {
		if(instance == null){
			instance = new BasicType();
		}
		return instance;
	}
	
	@Override
	public void set(Field field, Object object, Object value) throws JsonMappingException {
		if (value == JSONObject.NULL) {
			return;
		}
		try {
			field.set(object, value);
		} catch (Exception e) {
			throw new JsonMappingException(e);
		}
	}

	public static boolean isBasicType(Field field) {
		return isBasicType(field.getType());
	}
	public static boolean isBasicType(Class type) {
		boolean contains = false;
		for (Class clazz : BASIC_TYPES) {
			if (type.equals(clazz)) {
				contains = true;
				break;
			}
		}
		return contains;
	}

}
