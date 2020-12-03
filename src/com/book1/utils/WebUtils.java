package com.book1.utils;

import com.book1.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils{
    /**
     * 一次性获取form表单中的所有属性值即key=value，然后封装到JavaBean中
     * @param value
     * @param bean
     * @param <T>
     * @return
     */


    public static <T> T copyParamToBean(Map value, T bean){

        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
/*    public static Object copyParamToBean1(Object bean,HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.populate(bean,req.getParameterMap());
        return bean;
    }*/

    /**
     * 因为req.getParameter获取的是字符串，所以讲字符串转为Integer，defaultvalue是默认值
     * @param req---请求参数
     * @param defaultvalue---默认值
     * @return
     */
    public static int parseInt(String req,int defaultvalue){
       try {
           //如果放入defaultValue则相当于转换进制，上边defaultvalue是默认值是如果转换不成功，返回defaultValue
           return Integer.parseInt(req);
       }catch (Exception e){
/*           e.printStackTrace();*/
           //报异常因为赋了默认值
       }
       //果转换不成功，返回defaultValue
       return defaultvalue;
    }
}
