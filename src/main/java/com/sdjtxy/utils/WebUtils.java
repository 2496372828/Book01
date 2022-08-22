package com.sdjtxy.utils;

import com.sdjtxy.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static <T>T copyParameterToBean(T t, Map map) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.populate(t,map);
        return t;

    }
    /*public static Integer parseInt(String str,int defaultValue){
        return Integer.parseInt(str);
    }*/
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }
}
