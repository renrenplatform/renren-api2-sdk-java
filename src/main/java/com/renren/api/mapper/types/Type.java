package com.renren.api.mapper.types;

import java.lang.reflect.Field;

import com.renren.api.mapper.JsonMappingException;
import com.renren.api.mapper.ObjectMapper;

public abstract class Type {

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    public static Type getFieldType(Field field) {
        Type type = null;
        if (BasicType.isBasicType(field)) {
            type = BasicType.getInstance();
        } else if (BasicArrayType.isBasicArray(field)) {
            type = BasicArrayType.getInstance();
        } else if (BasicListType.isBasicList(field)){
        	type = BasicListType.getInstance();
        }else if (ObjectListType.isObjectList(field)) {
            type = ObjectListType.getInstance();
        } else if (ObjectArrayType.isObjectArray(field)) {
            type = ObjectArrayType.getInstance();
        } else if (EnumType.isEnum(field)) {
        	type = EnumType.getInstance();
        } else {
            type = ObjectType.getInstance();
        }
        return type;
    }

    public abstract void set(Field f, Object object, Object value) throws JsonMappingException;

}
