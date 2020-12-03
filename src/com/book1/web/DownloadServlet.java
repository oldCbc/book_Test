package com.book1.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取下载的文件名
        String downloadFileName="lang.jpg";
        //2、读取要获取的文件内容（ServletContext对象读取）
        ServletContext servletContext=getServletContext();
        //获取下载的文件类型路径
        String mimeType = servletContext.getMimeType("/File/" + downloadFileName);
        //4、回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);
        //5、通过响应头告诉客户端收到的数据用于下载使用(而不是仅仅查看)
        //Content-Disposition响应头，表示收到的数据怎么处理
        //attachment表示附件，表示下载使用
        //filename表示下载的文件名，可以自定义任何名字
        // 但是中文乱码,要想不乱吗，应该是 .d
        //使用URL编码，将汉字转换为%xx%xx格式
        // 火狐得需要BASE64编解码操作resp.setHeader("Content-Disposition","attachment;filename==?UTF-8?B?"+new BASE64Encoder().encode("中国.jpg".getBytes("UTF-8"))+"?=");
        //注意  只有两个参数值！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("中国.png","UTF-8"));

        //斜杠表示解析到工程名，映射到web目录
        InputStream resourceAsStream = servletContext.getResourceAsStream("/File/" + downloadFileName);
        //3、下载的内容传回客户端
        //获取响应的输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        //通过复制输入流 给 输出流，输出给客户端
        IOUtils.copy(resourceAsStream,outputStream);

    }
}

