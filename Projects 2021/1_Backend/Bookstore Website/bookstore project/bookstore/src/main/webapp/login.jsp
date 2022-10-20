<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Bookstore List</title>
    <%--导入css --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css" type="text/css"/>
</head>

<body class="main">

<jsp:include page="WEB-INF/pages/list/head.jsp"/>
<jsp:include page="WEB-INF/pages/list/menu_search.jsp"/>

<div id="divpagecontent">
    <div width="100%" border="0" cellspacing="0">
        <div>

            <div>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="index.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;<c:choose>
                    <c:when test="${empty requestScope.category}">All Categories</c:when>
                    <c:otherwise>${requestScope.category}</c:otherwise>
                </c:choose>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;Book List
                </div>

            </div>
        </div>
        <span id="uSpan" style="text-align: left;"></span>

        <div id="app" style="background-color: #5CA5D6">

            <table border="1px" width="50%">

                <form id="myForm" action="loginServlet?operation=login" method="post"
                      autocomplete="off">
                    <tr align="center">
                        <td style="border-color: #5CA5D6;height: 40px"><label
                                style="font-weight:bold;">Username:</label>
                        </td>
                        <td><input id="username" type="text" name="username" autocomplete="off"><br></td>
                    </tr>
                    <tr align="center">
                        <td style="border-color: #5CA5D6"><label style="font-weight:bold;">Password</label></td>
                        <td><input id="password" type="password" name="password" autocomplete="off"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input id="submitBtn" type="submit" value="Login"></td>

                    </tr>

                </form>
            </table>
        </div>
    </div>

</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script>

    // check if username is valid using AJAX
    $("#username").blur(function () {

        let username = $("#username").val();

        $.ajax({
            url: "loginServlet?operation=checkUsername",
            async: true,
            data: {
                "username": username,
            },
            type: "POST",
            dataType: "text",
            success: function (data) {
                $("#uSpan").html(data);
            },
            error() {
                alert("Something went wrong...");
            }
        })

    })


</script>

</body>
</html>
