package com.book1.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         /*  System.out.println("文件传送成功");
        ServletInputStream inputStream = req.getInputStream();
        byte[] buffer=new byte[1024000];
        int read = inputStream.read(buffer);
        System.out.println(new String(buffer,0,read));*/

        //判断上传的数据是否为多段（false则不能上传）
        if(ServletFileUpload.isMultipartContent(req)){
            //创建工厂实现类
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            try {
                //解析上传的数据，获得一个表单项
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //循环遍历每一个表单项是普通类型还是文件类型，分开上传
                for(FileItem fileItem:list){
                    if(fileItem.isFormField()){
                        //普通类型
                        //获取name和value属性值
                        System.out.println("表单项的name属性值"+fileItem.getFieldName());
                        //参数UTF-8解决乱码
                        System.out.println("表单项的value属性值"+fileItem.getString("UTF-8"));

                    }else {
                        System.out.println("表单项的name属性值"+fileItem.getFieldName());
                        System.out.println("上传的文件名"+fileItem.getName());
                        //保存文件到指定路径，
                        fileItem.write(new File("d:\\"+fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
