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

<jsp:include page="head.jsp"/>
<jsp:include page="menu_search.jsp"/>

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
                            <h1>Book List</h1>
                            <h1>
                                <c:choose>
                                    <c:when test="${empty requestScope.category}">All Categories</c:when>
                                    <c:otherwise>${requestScope.category}</c:otherwise>
                                </c:choose>

                            </h1>


                            <hr/>
                            <div style="margin-top:20px; margin-bottom:5px">
                                <img src="${pageContext.request.contextPath }/admin/images/productlist.gif" width="100%"
                                     height="38"/>
                            </div>


                            <table cellspacing="0" cellpadding="1" rules="all"
                                   bordercolor="gray" border="1" id="DataGrid1"
                                   style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                                <tr
                                        style="FONT-WEIGHT: bold; FONT-SIZE: 12pt;  BACKGROUND-COLOR: #afd1f3">
                                    <td align="center" width="15%">Book ID</td>
                                    <td align="center" width="20%">Book Name</td>
                                    <td align="center" width="15%">Qty In Stock</td>
                                    <td align="center" width="15%">Unit Price</td>
                                    <td width="15%" align="center">Category</td>
                                    <td width="10%" align="center">Edit</td>
                                    <td width="10%" align="center">Delete</td>
                                    <td width="10%" align="center">Add</td>
                                </tr>

                                <c:forEach items="${requestScope.pb.list }" var="b">
                                    <tr onmouseover="this.style.backgroundColor = 'white'"
                                        onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                        <td>${b.id }</td>
                                        <td>${b.name }</td>
                                        <td>${b.pnum }</td>
                                        <td>$ ${b.price }</td>
                                        <td>${b.category }</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/homeServlet?operation=findBookById&id=${b.id }">
                                            <img src="${pageContext.request.contextPath}/admin/images/i_edit.png" border="0" style="CURSOR: hand"> </a>
                                        </td>

                                        <td>
                                            <button type="button" onclick="return delBook('${b.id}', '${b.name}');">
                                            <img
                                                    src="${pageContext.request.contextPath}/admin/images/i_del.png"
                                                    width="16" height="16" border="0" style="CURSOR: hand">
                                            </button>
                                        </td>

                                        <td>
                                            <a href="${pageContext.request.contextPath}/add.jsp">
                                            <img src="${pageContext.request.contextPath}/admin/images/i_add.png"
                                                 width="16" height="16" border="0" style="CURSOR: hand">
                                            </a>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </table>
                            <div class="pagination">
                                <ul>
                                    <li class="nextPage">
                                        <a href="${pageContext.request.contextPath  }/homeServlet?${requestScope.category==null?"operation=list":"operation=showByCategory&category=".concat(requestScope.category)}&curPage=${pb.pageNum==1?1:pb.pageNum-1}">&lt;&lt;Previous
                                            Page</a></li>

                                    <li>Page ${pb.pageNum } / ${pb.pages }</li>

                                    <li class="nextPage"><a
                                            href=${pageContext.request.contextPath}/homeServlet?${requestScope.category==null?"operation=list":"operation=showByCategory&category=".concat(requestScope.category)}&curPage=${pb.pageNum==pb.pages?pb.pages:pb.pageNum+1}>Next
                                        Page&gt;&gt;</a></li>

                                </ul>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<script src="../../../js/jquery-3.3.1.min.js"></script>
<script src="../../../js/myJS.js"></script>

<script>
    function delBook(bookId, bookName) {
        if (confirm("Are you sure to delete " + bookName + "?")) {
            window.location.replace("${pageContext.request.contextPath}/homeServlet?operation=delete&Id="+bookId);
            return false;
        }
    }
</script>


<jsp:include page="foot.jsp"/>


</body>
</html>
