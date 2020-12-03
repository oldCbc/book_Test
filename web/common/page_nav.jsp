<%--
  Created by IntelliJ IDEA.
  User: CBC
  Date: 2020/11/18
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%--
    分页代码共用，用的时候只需在对应的Servlet类中赋url值即可
    --%>

<%--分页开始--%>
<div align="center">
    <%--如果 当前页>1，则显示上一页和首页，否则不显示--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--1、不足3页--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<=3}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <%--2、大于3页
        --%>
        <c:when test="${requestScope.page.pageTotal>3}">
            <c:choose><%--2是3的中位数--%><%--①前3页--%>
                <c:when test="${requestScope.page.pageNo<=2}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="3"></c:set>
                </c:when><%--②末尾--%>
                <c:when test="${requestScope.page.pageNo>=requestScope.page.pageTotal-1}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when><%--③中间页--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-1}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+1}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>


    <%--公用代码，上边赋值，下边执行循环遍历--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>



    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}几条记录
    到第<input value="${requestScope.page.pageNo}" type="text" id="pageinput" style="width: 20px ;">页
    <input type="button" value="确定" id="001">
    <script>
        $(function (){
            $("#001").click(function (){
                //先获取当前页
                var pageNoInitial=${requestScope.page.pageNo};
                //在获取输入框中的页码
                var pageNo=$("#pageinput").val();
                if(pageNo<1||pageNo>${requestScope.page.pageTotal}){
                    alert("请输入正确的页码！");
                    location.href="http://localhost:8080/book_Test/bookServlet?action=page&pageNo="+pageNoInitial;
                }
                else {
                    location.href="http://localhost:8080/book_Test/bookServlet?action=page&pageNo="+pageNo;
                }
            })
        })
    </script>
</div>
<%--分页结束--%>