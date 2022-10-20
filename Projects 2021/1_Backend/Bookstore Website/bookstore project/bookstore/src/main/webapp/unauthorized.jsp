<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Legancy Bookstore</title>
    <meta name="description">
    <meta name="keywords">
<%--    <meta name="description" content="AdminLTE2定制版">--%>
<%--    <meta name="keywords" content="AdminLTE2定制版">--%>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Unauthorized Page
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="error-page">
           <div class="error-content" style="margin-left:0">
                <h3><i class="fa fa-warning text-yellow"></i> Oops! You don't have the permission to view this page.</h3>

                <p>
                    <a href="${pageContext.request.contextPath}/login.jsp">Return to the login page.</a>
                </p>

            </div>
        <!-- /.error-page -->
        </div>
    </section>
</div>
<!-- 内容区域 /-->
</body>

</html>