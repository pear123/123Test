package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Password_record;
import pojo.User;
import service.Password_recordService;
import service.UserService;
import util.MD5Utils;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class Password_recordController {
    @Resource
    private Password_recordService password_recordService;
    @Resource
    private UserService userService;
    //跳转到获取密码的页面
    @RequestMapping("pre_findPassword")
    public String  pre_findPassword()throws Exception {
        return "findPassword";
    }
    //返回验证码
    @RequestMapping(value="/validate_number_",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    public @ResponseBody
    String validate_number(Model model,String phone) throws Exception {
        User user=userService.queryUserByPhone(phone);
        Date date=new Date();
        if(user!=null){
            Password_record password_record=new Password_record();
            String code=userService.getCode();
            Integer number=Integer.parseInt(code);
            password_record.setU_phone(phone);
            password_record.setValidate_number(number);
            password_record.setP_createtime(date);
            password_record.setStatus("0");//未失效
           boolean b= password_recordService.addPassword_record(password_record);
           if(b){
               return "identifying code is："+code;
           }
        }
        return "Acquisition failure";
    }
    //密码找回
    @RequestMapping(value="/findPassword",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    public @ResponseBody
    String findPassword(Model model,String phone,String validate_number) throws Exception {
        Date date=new Date();
        Integer validate_number2=Integer.parseInt(validate_number);
        String password=password_recordService.getPassword(phone,validate_number2,date);
        if(password.equals("")){
           /* return "Verification code error or timeout";*/
            return "fail";
        }

       return "success";
       /* return "Your password is "+password2+",please login and modify in time";*/
    }

}
