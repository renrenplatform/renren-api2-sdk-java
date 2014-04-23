package com.renren.api.mapper.types;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.renren.api.json.JSONObject;
import com.renren.api.mapper.JsonMappingException;

/**
 * 用于反序列化java中的Enum属性
 * EnumType
 * @author DuYang yang.du@renren-inc.com
 * @since 2013-1-6 下午6:47:55
 *
 */
@SuppressWarnings("rawtypes")
public class EnumType extends Type{
	private static EnumType INSTANCE;
	private EnumType() {
		
	}
	@Override
	public void set(Field field, Object object, Object value)
			throws JsonMappingException {
		if (value == JSONObject.NULL) {
			return;
		}
		try {
			//获得一个Enum类型的值
			Class<?> enumClazz = field.getType();
			Method enumValueOf = enumClazz.getMethod("valueOf", String.class);
			Object enumValue = enumValueOf.invoke(enumClazz, value);
			//设置enum值
			field.set(object, enumValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JsonMappingException(e);
		}
		
	}
	public static boolean isEnum(Field field) {
		return isEnum(field.getType());
	}
	public static boolean isEnum(Class type) {
		return type.isEnum();
	}
	public static Type getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EnumType();
        }
        return INSTANCE;
    }
}
