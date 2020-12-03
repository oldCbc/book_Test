<%--
  Created by IntelliJ IDEA.
  User: CBC
  Date: 2020/11/17
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--要想用本页，需要静态包含--%>
        <%--
        获取客户端地址
        --%>
        <%
            String basePath=request.getScheme()
                    +"://"
                    +request.getServerName()
                    +":"
                    +request.getServerPort()
                    +request.getContextPath()
                    +"/";
            pageContext.setAttribute("basePath",basePath)   ;
        %>
  <%--      &lt;%&ndash;base 标签，固定相对路径的跳转的结果，利用静态包含&ndash;%&gt;
<base href="<%=basePath%>">--%>

