<%--
  Created by IntelliJ IDEA.
  User: Lili.Chen
  Date: 2018/11/26
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        .number{
            width: auto;
            height: 40px;
        }
        #span1{
            width: auto;
            height: 50px;
        }

    </style>
<body>
    <div id="div1">
        <div id="div2" ><img src="images/1.jpg" width="100%"></div>
        <div id="div3">
            <br/>
            <br/>
            <br/>
            <center>
                <form id="frm">
            <table>
   <!-- value="短信验证码"  onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue; this.style.color='#999'}" style="color:#999"-->
                <tr><td colspan="2"><input id="phone" name="phone"  class="input1" placeholder="请输入手机号码"/></td>
                <td><span id="span2" style="color: red"></span></td>
                </tr>
                <tr><td><input class="number"type="number" id="massage_number" placeholder="短信验证码"  /></td>
                <td><input id="btn1" type="button" value="获取验证码"></td><td><span id="span4" style="color: red"></span></td>
                </tr>
                <tr id="tr1" hidden><td><input type="text" class="number" id="code_number"></td><td> <img id="_img"  src="getCaptchaCode.action">
                    <a href="#" id="btn3" onclick="change_code()">看不清，换一张</a></td><td><input id="btn4" type="button" value="确定"></td>
                </tr>
                <tr><td colspan="2"><input type="password" class="input1" name="password" id="password" placeholder="密码" />
                </td>
                    <td><span id="span3" style="color: red"></span></td>
                </tr>
                <tr><td colspan="3"><input id="agreement" type="checkbox">我已经阅读并同意<a href="/queryAgreement.action">《用户注册协议》</a></td></tr>
                <tr ><td colspan="3"><span id="span1" style="color: white">0</span></td></tr>
                <tr><td ><input class="btn" id="btn2" type="button" value="注册"></td>
                    <td><input class="btn" type="reset" value="重置"></td><td></td>
                </tr>
                <tr><td colspan="2"><a href="/pre_login.action" style="color: black">返回登陆</a></td>
                </tr>
            </table>
                </form>
                <br/>
                <span style="color: red">*注册后即可得500积分哦</span>
            </center>

        </div>
    </div>
</body>
<script type="text/javascript">
    $("#password").blur(function () {
       var password=$(this).val();
       if(password==null||password==""){
           $("#span3").html("*密码不可为空");
       }else{
           $("#span3").html("");
       }
    })
    /*var blurjdg=false*/
    $("#btn4").click(function () {
        var code_number=$("#code_number").val();
        $.ajax({
            type:"post",
            url:"validate_code.action",
            data:"code_number="+code_number,
            success:function (msg) {
               if(msg=="fail"){
                   $("#code_number").val("");
                  change_code();
               }else{
                   $("#tr1").hide();
                   $("#btn1").attr("disabled", false);
                   $("#massage_number").attr("readonly",false);
               }

                }
        })
    })
    function change_code(){
        var src= $("#_img").attr("src","getCaptchaCode.action?"+new Date().getTime());
    }

    $("#btn1").click(function (e) {
        e.stopPropagation()
        console.log(2)
        var phone=$("#phone").val();
        var re = /^1\d{10}$/;
        if(!re.test(phone)){
            //$("#phone").trigger('blur')
            /*$("#span2").html("*注意手机号码格式哦");*/
            alert("请输入正确的号码获取验证码！！");
        }else{
            $("#span2").html("");
            $.ajax({
                type:"post",
                url:"validate_phone.action",
                data:"phone="+phone,
                success:function (msg) {
                    if(msg=="exit"){
                       alert("会员已经被注册过了")
                    }else{
                        $.ajax({
                            type:"post",
                            url:"send_validate_number.action",
                            data:"phone="+phone,
                            success:function (msg) {
                                alert("验证码："+msg);
                            var countdown=60;
                            var chen=null
                            function settime() {
                              if(countdown==0){
                                  $("#btn1").attr("disabled",false);
                                  $("#btn1").val("获取验证码");
                                  countdown=60;
                                  clearInterval(chen);
                                  return false;
                              }else{
                                  $("#btn1").attr("disabled", true);
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
    $("#phone").on('change',function () {
        console.log(1)
        var phone=$(this).val();
        var re = /^1\d{10}$/;
        if(!re.test(phone)){
            $("#span2").html("*注意手机号码格式哦");
        }else{
            $("#span2").html("");
            $.ajax({
                type:"post",
                url:"validate_phone.action",
                data:"phone="+phone,
                success:function (msg) {
                    if(msg=="exit"){
                        $("#span2").html("*会员已经注册");
                    }
                }
            })
        }
    })
    //提交
$("#btn2").click(function () {
    $("#code_number").val("")
    var massage_number=$("#massage_number").val();
    var password=$("#password").val();
    var phone=$("#phone").val();
    var re = /^1\d{10}$/;
    if(!re.test(phone)){
        alert("*手机号码格式错误");
    }else if(password==null||password==""){
        alert("*密码不可为空");
    }else if(password.length<4){
        alert("密码不可以小于4位");
    }else if(massage_number==""||massage_number==null){
        alert("*手机验证码不可为空");
        /*$("#span4").html("*手机验证码不可为空");*/
    }else if(!$("#agreement").is(':checked')){
        alert("请同意相关协议才可注册");
    }else{
        $("#span2").html("");
        $("#span3").html("");
        $("#span4").html("");
        $.ajax({
            type:"post",
            url:"validate_number.action",
            data:"massage_number="+massage_number,
            success:function (msg) {
                alert(msg);
                if (msg == "success") {
                    alert("输入手机验证码正确");
                        $.ajax({
                            type:"post",
                            url:"validate_phone.action",
                            data:"phone="+phone,
                            success:function (msg) {
                                if(msg=="exit"){
                                    alert("*该会员已经注册过,快去登录吧")
                                }else{
                                    $.ajax({
                                        type:"post",
                                        url:"register.action",
                                        data: $("#frm").serialize(),
                                        success:function (msg) {
                                            if(msg=="success"){
                                                alert("注册成功,恭喜你成为会员");
                                                window.location.href="pre_login.action";
                                            }else {
                                                alert("注册失败");
                                            }
                                        }
                                    })
                                }
                            }
                        })
                } else if (msg == "fail") {
                    alert("输入验证码失败");
                    $("#btn1").attr("disabled", true);
                    $("#massage_number").attr("readonly",true);
                    change_code();
                    $("#tr1").show();
                }
            }
        })
    }

})
</script>
</html>
