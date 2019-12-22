package com.xxq.utils;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {
    
    /**
     * 将JavaBean对象封装到Map集合当中
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> bean2map(Object bean) throws Exception
    {
        //创建Map集合对象
        Map<String,Object> map=new HashMap<String, Object>();
        //获取对象字节码信息,不要Object的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(),Object.class);
        //获取bean对象中的所有属性
        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : list) {
            String key = pd.getName();//获取属性名
            Object value = pd.getReadMethod().invoke(bean);//调用getter()方法,获取内容
            map.put(key, value);//增加到map集合当中
        }
        return map;
    }
    
    
    /**
     * 将Map集合中的数据封装到JavaBean对象中
     * @param map        集合
     * @param classType 封装javabean对象
     * @throws Exception 
     */
    public static <T> T map2bean(Map<String, Object> map,Class<T> classType) throws Exception
    {
        //采用反射动态创建对象
        T obj = classType.newInstance();
        //获取对象字节码信息,不要Object的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(classType,Object.class);
        //获取bean对象中的所有属性
        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : list) {
            String key = pd.getName();    //获取属性名
            Object value=map.get(key);  //获取属性值
            pd.getWriteMethod().invoke(obj, value);//调用属性setter()方法,设置到javabean对象当中
        }
        return obj;
    }
    public static <T> List<Map<String, Object>> convertListBean2ListMap(List<T> beanList, Class<T> T)throws Exception
    {
    List<Map<String, Object>> mapList = new ArrayList<>();
    for (int i = 0, n = beanList.size(); i < n; i++)
    {
    Object bean = beanList.get(i);
    Map<String, Object> map = convertBean2Map(bean);
     mapList.add(map);
    }
    return mapList;
    }
    public static Map<String, Object> convertBean2Map(Object bean)throws IntrospectionException, IllegalAccessException, InvocationTargetException
    {
    Class<? extends Object> type = bean.getClass();
    Map<String, Object> returnMap = new HashMap<>();
    BeanInfo beanInfo = Introspector.getBeanInfo(type);
    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
    for (int i = 0; i < propertyDescriptors.length; i++)
    {
    PropertyDescriptor descriptor = propertyDescriptors[i];
     String propertyName = descriptor.getName();
    if (!"class".equals(propertyName))
    {
    Method readMethod = descriptor.getReadMethod();
     Object result = readMethod.invoke(bean, new Object[0]);
    if (result != null)
    {
     returnMap.put(propertyName, result);
     }
    else{
    	returnMap.put(propertyName, null);
    	}
    	}
    }
    	return returnMap;
    }
}