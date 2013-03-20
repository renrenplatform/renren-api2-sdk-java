package com.renren.api.mapper.types;

import java.lang.reflect.Field;

import com.renren.api.json.JSONObject;
import com.renren.api.mapper.JsonMappingException;

public class ObjectArrayType extends Type {

	private static ObjectArrayType INSTANCE;

	private ObjectArrayType() {

	}
	
	@Override
	public void set(Field f, Object object, Object value) throws JsonMappingException {
		if (value == JSONObject.NULL) {
			return;
		}
	}
	
	public static Type getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ObjectArrayType();
		}
		return INSTANCE;
	}

	public static boolean isObjectArray(Field field) {
		boolean isObjectArray = false;
		if (!field.getType().isArray()) {
			isObjectArray = false;
		} else {
			isObjectArray = !BasicType.isBasicType(field.getType().getComponentType());
		}
		return isObjectArray;
	}

}
