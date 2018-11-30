<%--
  Created by IntelliJ IDEA.
  User: Lili.Chen
  Date: 2018/11/28
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
    <style type="text/css">
        #div2{
            width: 50%;
            height: 400px;
            float: left;
        }
        #div3{
            width: 50%;
            height:400px;
            float: left;
        }
        .btn{
            width: 125px;
            height: 40px;
        }
        .input1{
            width: 250px;
            height:40px;
        }
        #span3{
            width: auto;
            height: 50px;
        }

    </style>
</head>
<body>
<div id="div1">
    <div id="div2" ><img src="images/1.jpg" width="100%"></div>
    <div id="div3">
        <br/>
        <br/>
        <br/>
        <center>
            <input type="hidden" name="count" id="count" value="0">
            <form action="/login.action" id="frm">
                <table>
                    <tr><td colspan="2"><input id="phone" name="phone"  class="input1" placeholder="请输入手机号码"/></td>
                        <td><span id="span1" style="color: red"></span></td>
                    </tr>
                    <tr><td colspan="2"><input type="password" class="input1" name="password" id="password" placeholder="密码" />
                    </td>
                        <td><span id="span2" style="color: red"></span></td>
                    </tr>
                    <tr>
                        <td colspan="3"><span  style="color: red">${message}</span></td>
                    </tr>
                    <tr ><td colspan="3"><span id="span3" style="color: white">0</span></td></tr>
                    <tr><td ><input class="btn" id="btn1" type="button" value="登录"></td>
                        <td><input class="btn" type="reset" value="重置"></td><td></td>
                    </tr>
                    <tr><td ><a href="/pre_register.action" style="color: black">快速注册</a></td>
                        <td ><a href="/pre_findPassword.action" style="color: black">密码找回?</a></td>
                    </tr>
                </table>
            </form>
        </center>

    </div>
</div>
<span hidden id="message">${message}</span>
<script type="text/javascript">
    $(function () {



    })

    $("#phone").blur(function () {
        var phone=$(this).val();
        var re = /^1\d{10}$/;
        if(!re.test(phone)){
            $("#span1").html("注意手机号码格式");
        }else{
            $("#span1").html("");
            $.ajax({
                type:"post",
                url:"validate_phone.action",
                data:"phone="+phone,
                success:function (msg) {
                    if(msg=="not_exit"){
                        $("#span1").html("*不存在该会员");
                    }
                }
            })
        }
    })
    $("#password").blur(function () {
        var password=$(this).val();
        if(password==null||password==""){
            $("#span2").html("*密码不可为空");
            $("#message").html("");
        }
    })
    //提交
    $("#btn1").click(function () {
        alert("提交")
        var password=$("#password").val();
        var phone=$("#phone").val();
        var re = /^1\d{10}$/;
        if(!re.test(phone)){
            $("#span1").html("*注意手机号码格式");
        }else if(password==null||password==""){
            $("#span2").html("*密码不可为空");
        }else{
            $("#span1").html("");
            $("#span2").html("");
            $.ajax({
                type:"post",
                url:"validate_phone.action",
                data:"phone="+phone,
                success:function (msg) {
                    if(msg=="not_exit"){
                        $("#span1").html("*不存在该会员");
                    }else{
                        $("#frm").submit();
                    }
                }
            })

        }
    })
</script>
</body>
</html>
