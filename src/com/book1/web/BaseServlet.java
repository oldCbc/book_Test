package com.book1.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
//不仅UserServlet可用，任何类都可用，只需继承此类即可（因为都是Servlet类，所以此类继承Servlet即可，其他类继承此类）
//因为此方法体必须使用this来达到通用，只有抽象类中没有对象，也就是谁继承这个抽象类，this就代表谁的对象
//抽象类不一定含有抽象方法！！！
public abstract class BaseServlet extends HttpServlet {
    /**
     * 动态获取action的属性值来调用对应的BookServlet方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求乱码
        req.setCharacterEncoding("UTF-8");
        //响应乱码
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
/*        if("login".equals(action)){
            login(req,resp);

        }else if("regist".equals(action)){
            regist(req,resp);
        }*/

        //反射动态解决问题，上边各个方法名必须与隐藏域name属性值一致
        try {
            //获取隐藏域的鉴别字符串，获取相应的业务，方法反射对象
            //反射的方法getDeclareMethod动态获取对象的方法，action是方法名，后边是参数
            //反射获取方法格式
            /*
            子类对象this.getClass()是获取Class的对象实例，
            方法别名=getDeclareMethod（方法名，方法的参数一，方法参数二）
            方法别名.invoke（对象this，参数一，参数二）
             */
            Method method=this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
          //调用目标业务，方法
           /*System.out.println("第三次的this对象是获取的具体类的对象"+this);*/
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            //把异常抛给Filter过滤器
            throw new RuntimeException();
        }

    }

    /**
     * doPost方法中的doget方法（就是全部可以使用了）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
