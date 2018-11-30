<%--
  Created by IntelliJ IDEA.
  User: Lili.Chen
  Date: 2018/11/29
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
    <style type="text/css">
        #div1{
            width: 50%;
            height: 100%;
        }
        #div2{
            width: 100%;
            height: 50px;
            text-align: right;
        }
    </style>
</head>
<body>
<center>
    <div id="div2">
        <a href="javaScript:history.back(-1)">返回上一页</a>
    </div>
    <h2>${agreement.name}</h2>
    <div id="div1">
        <p style="font-size:17px">${agreement.content}</p>
    </div>
</center>
</body>
</html>
