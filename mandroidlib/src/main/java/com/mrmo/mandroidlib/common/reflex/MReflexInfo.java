package com.mrmo.mandroidlib.common.reflex;

import java.lang.reflect.Field;

/**
 * Created by moguangjian on 16/5/12 10:40.
 */
public class MReflexInfo {
    private final String TAG = MReflexInfo.class.getSimpleName();

    private Object reflexObject; // 被反射的对象
    private String fieldName = ""; // 成员对象名称
    private boolean hasField; // 是否有该属性
    private Object fieldValues; // 返射得到的成员对象的值
    private Field field; // 返射得到的成员对象，并已经设置field.setAccessible(true)，所以可以直接修改其值

    public Object getReflexObject() {
        return reflexObject;
    }

    public void setReflexObject(Object reflexObject) {
        this.reflexObject = reflexObject;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Object fieldValues) {
        this.fieldValues = fieldValues;
        try {
            getField().set(getReflexObject(), fieldValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean isHasField() {
        return hasField;
    }

    public void setHasField(boolean hasField) {
        this.hasField = hasField;
    }
}
