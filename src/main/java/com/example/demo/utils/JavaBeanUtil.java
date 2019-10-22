package com.example.demo.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: JavaBean工具类
 * Date: 2019-07-16
 *
 * @Author: chy
 */
public class JavaBeanUtil {

    /**
     * Map转JavaBean
     *
     * @param type JavaBean类型
     * @param map  Map数据对象
     * @param <T>  泛型对象
     * @return JavaBean类型对象
     * @throws Exception
     */
    public static <T> T convertToJavaBean(Class<T> type, Map map) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Object obj = type.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                descriptor.getWriteMethod().invoke(obj, value);
            }
        }
        return (T) obj;
    }

    /**
     * JavaBean转Map数据对象
     *
     * @param bean JavaBean对象
     * @return Map数据对象
     * @throws Exception
     */
    public static Map convertToMap(Object bean) throws Exception {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
}
