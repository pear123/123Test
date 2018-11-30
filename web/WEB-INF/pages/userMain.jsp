<%--
  Created by IntelliJ IDEA.
  User: Lili.Chen
  Date: 2018/11/28
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
   <style>
       #div1{
           width: 50%;
           height: 100%;
           background-color: antiquewhite;
       }
       .div2{
           width: 100%;
           height: 90px;

       }
       #divTitle{
           width: 100%;
           height: 60%;

       }
       #divContent{
           width: 100%;
           height: 40%;

       }
       #divLeft{
           width: 50%;
           height: 100%;
           float: left;

           text-align: left;
       }
       #divRight{
           width: 50%;
           height: 100%;
           float: left;
           text-align: right;

       }
       
   </style>

</head>
<body>
<div style="text-align: left;width: 50%;" >
    <c:if test="${!empty score}">
   <span>你的积分是：${score}</span>
    </c:if>
</div>
<div style="text-align: right;">
    <c:if test="${!empty phone}">
    欢迎${phone}用户登录！
    </c:if>
<a onclick="updatePassword()" href="#">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/pre_login.action">登录</a>&nbsp;&nbsp;&nbsp;
    <a href="/pre_register.action">注册</a>
<hr/>
</div>
<center>
    <div id="div1">
        <c:if test="${empty inforList}">
            <p>###你还没有写任何日志哦！！快去添加吧！！</p>
            <br/>
            <a href="/pre_addInformation.action?u_id=${u_id}">去写日志</a>
        </c:if>
    <c:if test="${!empty inforList}">
        <a href="/pre_addInformation.action?u_id=${u_id}">去写日志</a>
        <c:forEach items="${inforList}" var="infor">
            <div id="div_${infor.infor_id}" class="div2">
                <div id="divTitle">
        <hr/>
          <h4 align="left">${infor.title}</h4><br/>
                </div>
                <div id="divContent">
            <div id="divLeft">
                <span><fmt:formatDate value="${infor.createtime}" pattern="yyyy-MM-dd"/></span>
          <span><fmt:formatDate value="${infor.createtime}" pattern="yyyy-MM-dd"/></span>
            </div>
           <div id="divRight">
               <a href="/pre_editInformation.action?infor_id=${infor.infor_id}"><input type="button" value="编辑"></a>&nbsp;<input id="delete" onclick="delete_infor(${infor.infor_id})" type="button" value="删除" >
           </div>
                    <hr/>
            </div>
            </div>
        </c:forEach>
    </c:if>

    </div>
</center>
<script type="text/javascript">
    function delete_infor(infor_id) {
        var confirm_=confirm('确认删除？');
        if(confirm_){
            $.ajax({
                type:"post",
                url:"deleteInformation.action",
                data:"infor_id="+infor_id,
                success:function (msg) {
                    if(msg=="success"){
                        $("#div_"+infor_id).remove();
                        alert("删除成功")
                    }else{
                        alert("删除失败");
                    }
                }
            })
        }

    }

    //修改密码
    function updatePassword() {
        var u_id=${u_id};
        var password=window.prompt("请输入新的密码","");
        var re_password=window.prompt("请再次输入你的密码","");
        if(password==""||re_password==""){
            alert("密码不能为空");
        } if(password.length<4){
            alert("密码不能小于四位数");
        }else if(password!=re_password){
            alert("两次密码需要一致");
        }else if(password!=null&&re_password!=null){
            var confirm_ = confirm('确认修改密码？');
            if(confirm_){
                $.ajax({
                    ContentType:"application/json;charset=UTF-8",
                    type:"post",
                    url:"update_Password.action",
                    data:{"password":password,"re_password":re_password,"u_id":u_id},
                    success:function (msg) {
                        if(msg=="success"){
                            alert("密码更改成功,请重新登录")
                            window.location.href="pre_login.action";
                        }else {
                            alert("更改失败");
                        }
                    }
                })
            }
        }
    }
</script>
</body>
</html>
