package com.renren.api.mapper;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.renren.api.json.JSONArray;
import com.renren.api.json.JSONException;
import com.renren.api.json.JSONObject;

import com.renren.api.mapper.types.Type;

@SuppressWarnings("unchecked")
public class ObjectMapper {

	/**
	 * Maps a json string to an Object.
	 * @param jsonString The json representation of  the object. 
	 * @param clazz The return type of the method.  
	 * @return An object of type <b>clazz</b>.
	 * @throws JsonMappingException If the json is not parsable or if any error occurred while binding the values to the object. 
	 */
	public <T> T map(String jsonString, Class<T> clazz) throws JsonMappingException{
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
		} catch (JSONException e) {
			throw new JsonMappingException(e);
		}
		return map(jsonObject, clazz);
	}

	/**
	 * Maps a {@link JSONObject} to an Object.
	 * @param jsonObject The {@link JSONObject} representation of  the object. 
	 * @param clazz The return type of the method.  
	 * @return An object of type <b>clazz</b>.
	 * @throws JsonMappingException If any error occurred while binding the values to the object. 
	 */
	public <T> T map(JSONObject jsonObject, Class<T> clazz) throws JsonMappingException{
		return mapSingleValue(jsonObject, clazz);
	}
	
	
	/**
	 * Maps a json string to an Array of Object.
	 * @param jsonString The json representation of the array. 
	 * @param clazz The type of the returned array.  
	 * @return An Array of objects of type <b>clazz</b>.
	 * @throws JsonMappingException If the json is not parsable or if any error occurred while binding the values to the object array. 
	 */
	public <T> T[] mapArray(String jsonString, Class<T> clazz) throws JsonMappingException{
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException e) {
			throw new JsonMappingException(e);
		}
		return mapArray(jsonArray, clazz);
	}


	/**
	 * Maps a {@link JSONArray} to an Array of Object.
	 * @param jsonArray The {@link JSONArray} representation of the array. 
	 * @param clazz The type of the returned array.  
	 * @return An Array of objects of type <b>clazz</b>.
	 * @throws JsonMappingException If the json is not parsable or if any error occurred while binding the values to the object array. 
	 */
	public <T> T[] mapArray(JSONArray jsonArray, Class<T> clazz) throws JsonMappingException {
		T[] values = (T[]) Array.newInstance(clazz, jsonArray.length());
		for (int i = 0; i < values.length; i++) {
			values[i] = mapSingleValue(jsonArray.optJSONObject(i), clazz);
		}
		return values;
	}
	private <T> T mapSingleValue(JSONObject jsonObject, Class<T> clazz) throws JsonMappingException {
		T t = null;
		try {
			t = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = getFieldName(field);
				boolean ignore = getIgnoreFIeld(field);
				if (!ignore && jsonObject.has(fieldName)) {
					Type type = Type.getFieldType(field);
					field.setAccessible(true);
					type.set(field, t,jsonObject.get(fieldName));
				}
			}
		} catch (Exception e) {
			throw new JsonMappingException(e);
		}
		return t;
	}
	public <T> T[] mapBaseArray(String jsonString, Class<T> clazz) throws JsonMappingException {
		jsonString = jsonString.replaceAll("\\[","");
		jsonString = jsonString.replaceAll("\\]", "");
		if(jsonString.equals("")) {
			return (T[]) Array.newInstance(clazz, 0);
		}
		String [] strArray = jsonString.split(",");
		T[] values = (T[]) Array.newInstance(clazz, strArray.length);
		Constructor<?> ctor = null;
		for(int i=0;i<strArray.length;i++) {
			try {
				ctor = clazz.getConstructor(String.class);
				values[i] = (T) ctor.newInstance(strArray[i].trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new JsonMappingException(e);
			}
		}
		return values;
	}
	/**
	 * 根据jsonStr的不同值，返回不同的object
	 * @param jsonStr
	 *         1.JsonObject 类型，返回clazz类型的对象
	 *         2.JsonArray 类型 返回clazz类型对象的数组
	 *         3.不符合json格式的字符串，认定为基本类型，则返回clazz对应的基本类型封装（Integer Long String 等）
	 * @param clazz 类型
	 * @return
	 * @throws JsonMappingException
	 */
	public Object mapCommon(String jsonStr, Class<?> clazz) throws JsonMappingException{
		if (jsonStr.startsWith("[")) {
			if(jsonStr.contains("{")) {
				return mapArray(jsonStr, clazz);
			}else {
				//反序列化基本类型数组
				return mapBaseArray(jsonStr,clazz);
			}
		} else if (jsonStr.startsWith("{")) {
			return map(jsonStr, clazz);
		} else {
			Constructor<?> ctor = null;
			try {
				ctor = clazz.getConstructor(String.class);
				return ctor.newInstance(jsonStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new JsonMappingException(e);
			}
		}
	}
	private boolean getIgnoreFIeld(Field field) {
		IgnoreField annotation = field.getAnnotation(IgnoreField.class);
		return annotation != null;
	}

	private String getFieldName(Field field) {
		JsonField annotation = field.getAnnotation(JsonField.class);
		return annotation != null ? annotation.value() : field.getName();
	}
}
