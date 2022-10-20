<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <table width="100%" border="0" cellspacing="0">
        <tr>

            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="${pageContext.request.contextPath }/index.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${empty requestScope.category}">All Categories</c:when>
                        <c:otherwise>${requestScope.category}</c:otherwise>
                    </c:choose>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;List
                </div>

                <table cellspacing="0" class="listcontent">
                    <tr>
                        <td>
                            <div style="margin-top:20px; margin-bottom:5px">
                                <img src="${pageContext.request.contextPath }/admin/images/productlist.gif" width="100%"
                                     height="38"/>
                            </div>

                            <form onsubmit="return add();">
                                <table>
                                    <td>Book ID <input id="id" type="text" value="${requestScope.book.id}">
                                    </td>
                                    <td>Book Name <input id="bookName" type="text"></td>
                                    <td>Qty In Stock <input id="qty" type="text"></td>
                                    <td>Unit Price <input id="price" type="text">
                                    </td>
                                    <td>Category <input id="category" type="text">
                                    </td>
                                    <td><input type="submit" value="Add"></td>
                                </table>
                            </form>

                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/myJS.js"></script>

<script>
    function add() {
        alert("add");
        let id = document.getElementById("id").value;
        let bookName = document.getElementById("bookName").value;
        let qty = document.getElementById("qty").value;
        let price = document.getElementById("price").value;
        let category = document.getElementById("category").value;
        window.location.replace("${pageContext.request.contextPath}/homeServlet?operation=addProduct&id="+id+"&name="+bookName+"&qty="+qty+"&price="+price+"&category="+category);
        return false;
    }
</script>


<jsp:include page="WEB-INF/pages/list/foot.jsp"/>


</body>
</html>
