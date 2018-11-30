<%--
  Created by IntelliJ IDEA.
  User: Lili.Chen
  Date: 2018/11/28
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
    <style>
        #div1{
            width: 50%;
            height: 500px;
            background-color: antiquewhite;
        }
        #div2{
            width: 80%;
            height: 80%;
            background-color: white;
        }
        #content{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<center>
<div id="div1">
    <form id="frm" action="/addInformation.action">
        <input type="hidden" name="u_id" value="${u_id}" >
    标题：<input type="text" id="title" name="title">
        <span id="span1" style="color: red">${missMessage}</span>
        <br/>
    <center>
    <div id="div2">
        <textarea type="text" id="content" name="content"></textarea>
    </div>
        <br/>
        <input id="btn1" type="button" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="内容重置">&nbsp<a href="/user_main.action?u_id=${u_id}&score=${score}">退出</a>
    </center>
    </form>
</div>
</center>

<script type="text/javascript">
    $("#btn1").click(function () {
        var title=$("#title").val();
        var content=$("#content").val();
        if(title==null||title==""){
            alert("标题不可为空");
        }else if(content==null||content==""){
            alert("内容不可为空");
        }else{

            $("#frm").submit();
        }
    })

</script>

</body>
</html>
