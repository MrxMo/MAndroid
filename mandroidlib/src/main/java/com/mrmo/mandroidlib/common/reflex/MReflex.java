package com.mrmo.mandroidlib.common.reflex;

import android.support.annotation.Nullable;

import java.lang.reflect.Field;

/**
 * Created by moguangjian on 16/5/12 10:39.
 */
public class MReflex {

    /**
     * 获取类指定属性相关信息
     *
     * @param object
     * @param filedName
     * @return
     */
    public static MReflexInfo getClassFieldsInfo(Object object, String filedName) {
        MReflexInfo mReflexInfo = new MReflexInfo();
        mReflexInfo.setFieldName(filedName);

        if (isObjectNull(object) || isEmpty(filedName)) {
            return mReflexInfo;
        }

        try {
            Class clazz = (Class) object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object val = field.get(object);

                if (field.getName().equals(mReflexInfo.getFieldName())) {
                    mReflexInfo.setReflexObject(object);
                    mReflexInfo.setField(field);
                    mReflexInfo.setHasField(true);
                    mReflexInfo.setFieldValues(val);
                    return mReflexInfo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mReflexInfo;
    }

    /**
     * 该类是否有对应fieldKey成员变量
     *
     * @param object
     * @param fieldKey
     * @return
     */
    public static boolean hasClassFields(Object object, String fieldKey) {
        return getClassFieldsInfo(object, fieldKey).isHasField();
    }

    /**
     * 设置fieldKey成员变量对应的值
     *
     * @param object
     * @param fieldKey
     * @param fieldValues
     */
    public static void setClassFieldsValues(Object object, String fieldKey, String fieldValues) {
        MReflexInfo mReflexInfo = getClassFieldsInfo(object, fieldKey);
        if (mReflexInfo.isHasField()) {
            mReflexInfo.setFieldValues(fieldValues);
        }
    }

    /**
     * fieldKey成员变量是否为null
     *
     * @param object
     * @param fieldKey
     * @return
     */
    public static boolean isNullClassFieldsValues(Object object, String fieldKey) {
        return isObjectNull(getClassFieldsInfo(object, fieldKey).getFieldValues());
    }


    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;

        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                e.printStackTrace();
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }

        return null;
    }

    /**
     * 实例化泛型
     *
     * @param t
     * @param <T>
     * @return
     */
    public static
    @Nullable
    <T> T instance(T t) {
        if (isObjectNull(instanceClass(t.getClass()))) {
            return null;
        }

        return (T) instanceClass(t.getClass());
    }

    /**
     * 实例化
     *
     * @param className
     * @param <T>
     * @return
     */
    public static
    @Nullable
    <T> T instanceClassName(String className) {
        Class clazz = null;
        if (isEmpty(className)) {
            return null;
        }

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        if (isObjectNull(instanceClass(clazz))) {
            return null;
        }

        return (T) instanceClass(clazz);
    }

    /**
     * 实例化
     * @param clazz
     * @param <T>
     * @return
     */
    public static
    @Nullable
    <T> T instanceClass(Class clazz) {
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isObjectNull(object)) {
            return null;
        }

        return (T) object;
    }

    /**
     * 判断字符串或长度是否为空（空白也算为空）
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (isObjectNull(string) || string.toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isObjectNull(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }
}
