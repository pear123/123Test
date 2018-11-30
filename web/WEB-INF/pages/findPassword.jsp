<%--
  Created by IntelliJ IDEA.
  User: Lili.Chen
  Date: 2018/11/30
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
</head>
<body>

<center>
    <table>
        <tr><td colspan="2"><input type="text" name="phone" id="phone" placeholder="请输入手机号码"></td></tr>
        <tr><td><input type="text" name="validate_number" id="validate_number" placeholder="请输入验证码"></td><td><input id="btn1" type="button" value="找回密码"></td></tr>
        <tr><td colspan="2"><input type="button" value="确定" id="btn2"></td></tr>
    </table>
</center>
<script type="text/javascript">
    $("#btn1").click(function () {
        var phone=$("#phone").val();
        var re = /^1\d{10}$/;
        if(!re.test(phone)){
            alert("请输入正确的号码获取验证码！！");
        }else{$.ajax({
            type: "post",
            url: "validate_phone.action",
            data: "phone=" + phone,
            success: function (msg) {
                if (msg == "not_exit") {
                    alert("会员不存在")
                }else{
                            $.ajax({
                                type:"post",
                                url:"validate_number_.action",
                                data:"phone="+phone,
                                success:function (msg) {
                                    alert(msg+"(验证码时效为一分钟,且只能用一次)");
                                    var countdown=60;
                                    var chen=null
                                    function settime() {
                                        if(countdown==0){
                                            $("#btn1").val("获取验证码");
                                            countdown=60;
                                            clearInterval(chen);
                                            return false;
                                        }else{
                                            $("#btn1").val("重新发送(" + countdown + ")");
                                            countdown--;
                                        }
                                    }
                                    chen=setInterval(function () {
                                        settime();
                                    },1000);
                                }
                            })
                }
            }
        })

    }
    })
    $("#btn2").click(function () {
        var phone=$("#phone").val();
        var validate_number=$("#validate_number").val();
        var re = /^1\d{10}$/;
        if(!re.test(phone)){
            alert("*手机号码格式错误");
        }else if(validate_number==null||validate_number==""){
            alert("验证码不可为空");
        }else{
            $.ajax({
                type: "post",
                url: "validate_phone.action",
                data: "phone=" + phone,
                success: function (msg) {
                    if (msg == "not_exit") {
                        alert("会员不存在")
                    }else{
                        $.ajax({
                            type: "post",
                            url: "findPassword.action",
                            data: {"phone":phone,"validate_number":validate_number},
                            success: function (msg) {
                               /*alert(msg);*/
                                if(msg=="success"){
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
                                                url:"find_update_Password.action",
                                                data:{"password":password,"re_password":re_password,"phone":phone},
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
                                }else{
                                    alert("验证码错误或者失效");
                                }
                            }
                        })
                    }
                }
            })
        }

    })
</script>
</body>
</html>
