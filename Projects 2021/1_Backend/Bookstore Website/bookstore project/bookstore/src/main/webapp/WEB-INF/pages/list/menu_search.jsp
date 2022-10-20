<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>
<script type="text/javascript">

    function fillNameValue(subDiv) {
        document.getElementById("name").value = subDiv.innerHTML;

        document.getElementById("content").style.display = "none";
    }

    function searchName(uriComponent) {
        let nameElement = document.getElementById("name");
        //获取输入的信息
        let nameValue = nameElement.value;

        let div = document.getElementById("content");
        div.innerHTML = "";
        //1.获取XMLHttpRequest对象
        let xmlhttp = getXMLHttpRequest();

        //2.绑定回调函数 | 通过原生JavaScript方式实现AJAX: 搜索联想
        xmlhttp.onreadystatechange = function () {

            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // readyState=4: 请求已完成. status=200: 响应已全部OK

                let jsonObj = eval("(" + xmlhttp.responseText + ")");

                if (jsonObj.length > 0) {
                    document.getElementById("content").style.display = "block";
                    for (let i = 0; i < jsonObj.length; i++) {
                        div.innerHTML += "<div onclick='fillNameValue(this)' onmouseover='changeBackground_over(this)' onmouseout='changeBackground_out(this)'>"
                            + jsonObj[i].name + "</div>" // 搜索联想
                    }
                }

            }
        };
        //3. 打开链接
        xmlhttp.open("GET",
            "${pageContext.request.contextPath}/homeServlet?operation=findProductName&name="
            + nameValue
            + "&time=" + new Date().getTime());
        //4.send
        xmlhttp.send();
    }

    function changeBackground_over(div) {
        div.style.background = "gray";
    }

    function changeBackground_out(div) {
        div.style.background = "white";
    }

    function showSearchResult() { // handle form submission via JS
        let nameElement = document.getElementById("name");
        let nameValue = nameElement.value;
        window.location.replace("${pageContext.request.contextPath}/homeServlet?operation=showSearchResult&name=" + nameValue);
        return false; // when it returns false, your form will not submit or redirect
    }

</script>

<div id="divmenu">
    <a
            href="${pageContext.request.contextPath}/homeServlet?operation=showByCategory&category=Computer Science">Computer
        Science</a>
    <a
            href="${pageContext.request.contextPath}/homeServlet?operation=showByCategory&category=Life">Life</a>
    <a
            href="${pageContext.request.contextPath}/homeServlet?operation=showByCategory&category=Economics">Economics</a>
    <a
            href="${pageContext.request.contextPath}/homeServlet?operation=showByCategory&category=Novel">Novel</a>
    <a
            href="${pageContext.request.contextPath}/homeServlet?operation=showByCategory&category=MBA">MBA</a>

    <a href="${pageContext.request.contextPath}/homeServlet?operation=list"
       style="color:#FFFF00">All Categories</a>
</div>
<div id="divsearch">
    <form method="post" onsubmit="return showSearchResult();">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td style="text-align:right; padding-right:220px">
                    <input
                            type="text" name="name" class="inputtable" onkeyup="searchName();"
                            id="name"/>
                    <input type="submit" value="Search"/>

                </td>
            </tr>
        </table>

    </form>
</div>
<div id="content"
     style="background-color:white;width:128px; text-align:left;margin-left:945px;color:black;float:left;margin-top: -20px;display: none">
</div>