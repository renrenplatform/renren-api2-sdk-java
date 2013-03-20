package com.renren.api.mapper.types;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.renren.api.json.JSONArray;
import com.renren.api.json.JSONObject;

import com.renren.api.mapper.JsonMappingException;

public class BasicListType extends Type {

    private static BasicListType INSTANCE;

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void set(Field field, Object object, Object value) throws JsonMappingException {
    	if (value == JSONObject.NULL) {
			return;
		}
        try {
            if (value instanceof JSONArray) {
                JSONArray objectJsonArray = (JSONArray) value;
                List list = new ArrayList();
                for (int i = 0; i < objectJsonArray.length(); i++) {
                    list.add(objectJsonArray.get(i));
                }
                field.set(object, list);
            }
        } catch (Exception e) {
            throw new JsonMappingException(e);
        }

    }
    public static Type getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BasicListType();
        }
        return INSTANCE;
    }

    public static boolean isBasicList(Field field) {
        if(List.class.isAssignableFrom(field.getType())){
        	//如果是list类型，获取范型
        	java.lang.reflect.Type fc = field.getGenericType();
        	ParameterizedType pt = (ParameterizedType)fc;
        	Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];
        	return BasicType.isBasicType(genericClazz);
        }
        return false;
    }

}
