package com.book1.web;

import com.alibaba.druid.util.Utils;
import com.book1.pojo.User;
import com.book1.service.UserService;
import com.book1.service.impl.UserServiceImpl;
import com.book1.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

//继承了BaseServlet所以不必调用
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    /**
     * 登录页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //      resp.setContentType("text/html;charset=UTF-8");
        //      PrintWriter writer = resp.getWriter();

        //1、获取请求参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        System.out.println("用户名"+username+"密码"+password);


        //2、调用UserServiceImpl处理业务
        User login = userService.login(new User(null, username, password, null));
        if(login==null){
            System.out.println("用户名和密码错误，登陆失败");
            //       writer.print("检测能否输出");
            //如果信息错误，则回显用户名
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }else {
            System.out.println("登陆成功！");
            //response域不能刷新获取域信息，所以升级为session域保存信息，获取用户名刷新或者所有用户界面切换不会失去信息显示登录的信息直到下次关闭
            //将登录成功的用户信息javabean 传给了login，出入login，待页面取任何值，如名字
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 注销页面 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //销毁session(或者指定session，就得先获取域数据）
        req.getSession().invalidate();
        //重定向至首页
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 注册页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //1、获取请求的参数，获取的是name 属性值！！！！！！！！！！！！！！！！！！！！！
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //获取生成的验证码的值
        String token=(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //获取完毕立即删除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //获取参数的值并赋值给User类中的成员变量
        User user=WebUtils.copyParamToBean(req.getParameterMap(),new User());

/*①        try {
            BeanUtils.populate(user,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
/*②       try {
            user = (User) WebUtils.copyParamToBean1(new User(), req);
            System.out.println("获取之后"+user);
        } catch (Exception e) {
            e.printStackTrace();
        }*/




        //2、验证码====》先必须abcde
        System.out.println("用户名"+username+"密码"+password+"邮箱"+email+"验证码"+code);
        if(token.equalsIgnoreCase(code) && code!=null){
            if(userService.existsUsername(username)){
                System.out.println("用户名已存在"); 
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                //请求转发转至另一个servlet，  /  代表至http://localhost:8080/book_Test/web
                req.getRequestDispatcher("/regist_static.jsp").forward(req,resp);
            }else {
                userService.registUser(new User(null,username,password,email));
                //用重定向来防止F5刷新重复提交
                resp.sendRedirect(req.getContextPath()+"/regist_success.jsp");
               /* req.getRequestDispatcher("/regist_success.jsp").forward(req,resp);*/
            }
        }else {
/*
            //控制台打印网页信息
            PrintWriter out= resp.getWriter();
            out.print("验证码："+code+"错误");
*/

            //错误则跳回登录页面
            System.out.println("验证码："+code+"错误");
            req.getRequestDispatcher("/regist_static.jsp").forward(req,resp);
        }
    }
   /* protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            login(req, resp);

        } else if ("regist".equals(action)) {
            regist(req, resp);
        }
*//*      修改时将这段删除，继承改为BaseServlet才能调用BaseServlet

        关于java.lang.NoClassDefFoundError: org/apache/commons/collections/FastHashMap的错误解决办法
        在JavaEE开发中，在把配置文件中的数据或用户表单提交上来的数据，封装在相应JavaBean的对象的对应属性中时：在实际开发中，使用第三方法工具包BeanUtils（commons-beanutils-xxx.jar 依赖于commons-logging-xx.jar）。

        如博主在JavaWeb工程中，导入的jar包:commons-beanutils-1.9.3.jar和commons-logging-1.2.jar

        这是在运行时报错了：eption in thread "main" j0ava.lang.NoClassDefFoundError: org/apache/commons/collections/FastHashMap

        看来是需要导入commons-collections-xx.jar包

        然后博主导入了commens-collections4-4.1.jar没有解决问题

        最后，发现导入commens-collections-3.2.2.jar后解决问题了*//*


    }*/

    /**
     * JSON动态失去焦点判断用户名是否存在
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        String username = req.getParameter("username");
        //判断username是否存在
        boolean existsUsername = userService.existsUsername(username);
        //将赶回结果包装陈Map对象
        Map<String,Object>restltMap=new HashMap<>();
        restltMap.put("existsUsername",existsUsername);
        //转成Json对象
        Gson gson=new Gson();
        String json = gson.toJson(restltMap);
        //通过响应的字符输出流输出
        resp.getWriter().write(json);

    }
}

