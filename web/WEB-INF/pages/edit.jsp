<%--
  Created by IntelliJ IDEA.
  User: Lili.Chen
  Date: 2018/11/29
  Time: 14:30
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
        <form id="frm">
            <input type="hidden" name="u_id" value="${information.u_id}" id="u_id" >
            <input type="hidden" name="score" value="${score}" id="score" >
            <input type="hidden" name="createtime" value="<fmt:formatDate value="${information.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" >
            <input type="hidden" name="infor_id" value="${information.infor_id}" >
            标题：<input type="text" id="title" name="title" value="${information.title}">
            <span id="span1" style="color: red">${missMessage}</span>
            <br/>
            <center>
                <div id="div2">
                    <textarea type="text" id="content" name="content">${information.content}</textarea>
                </div>
                <br/>
                <input id="btn1" type="button" value="保存修改">&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn2" type="button" value="删除" onclick="deleteInfor(${information.infor_id})">&nbsp<a href="/user_main.action?u_id=${information.u_id}&score=${score}">退出</a>
            </center>
        </form>
    </div>
</center>
<script type="text/javascript">
    $("#btn1").click(function () {
        var score=$("#score").val();
  var confirm_=confirm('确认修改？');
        if(confirm_){
            var title=$("#title").val();
            var content=$("#content").val();
            var u_id=$("#u_id").val();
            if(title==null||title==""){
                alert("标题不可为空");
            }else if(content==null||content==""){
                alert("内容不可为空");
            }else{
                $.ajax({
                    type:"post",
                    url:"editformation.action",
                    data: $("#frm").serialize(),
                    success:function (msg) {
                        if(msg=="success"){
                            alert("修改成功");
                            window.location.href="user_main.action?u_id="+u_id+"&score="+score+"";
                        }else {
                            alert("修改失败");
                        }
                    }
                })

            }
        }

    })
    //删除
   function deleteInfor(id) {
       var score=$("#score").val();
       var confirm_=confirm('确认删除？');
       var u_id=$("#u_id").val();
       if(confirm_){
           $.ajax({
               type:"post",
               url:"deleteInformation.action",
               data:"infor_id="+id,
               success:function (msg) {
                   if(msg=="success"){
                       alert("删除成功")
                       window.location.href="user_main.action?u_id="+u_id+"&score="+score;
                   }else{
                       alert("删除失败");
                   }
               }
           })
       }
   }

</script>

</body>
</html>
