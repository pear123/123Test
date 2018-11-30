package controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.*;
import service.InformationService;
import service.Login_recordService;
import service.ScoreService;
import service.UserService;
import util.ImageValidate;
import util.MD5Utils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private Login_recordService login_recordService;
    @Resource
    private InformationService informationService;
    @Resource
    private ScoreService scoreService;

    @RequestMapping("test")
    public String  test()throws Exception {
        return "test";
    }
    //跳转到登陆页面
    @RequestMapping("pre_login")
    public String  pre_login()throws Exception {
        return "login";
    }
    //登录
    @RequestMapping("login")
    public String  login(User user, Model model) throws Exception {
        Date date=new Date();
        if(user!=null){
            Login_record record=login_recordService.queryRecordByPhone(user.getPhone());
            if(record!=null){
                if(record.getFail_num()==3){//第四次进行输入
                    Date date2=record.getLogin_date();
                    int c=(int)((date.getTime()-date2.getTime())/1000);
                    if(c<120){//等待时间未结束
                        model.addAttribute("message","连续三次输入错误,需等待2分钟后再登录");
                        return "login";
                    }else{
                        login_recordService.deleteRecord(record.getLogin_record_id());//到达等待条件
                    }
                }
            }
            if(user.getPhone()!=null&&user.getPhone()!=""){
               User user1=userService.queryUserByPhone(user.getPhone());
               if(user1!=null){
                   String  password2= new String(user.getPassword().getBytes("ISO8859_1"), "utf-8");
                   String password=MD5Utils.md5(password2);
                   user.setPassword(password);
                   boolean b=userService.login(user,date);
                   if(b){
                       List<Information> inforList=informationService.queryInformationByUid(user1.getId());
                       Score score=scoreService.queryScore(user1.getId());
                       model.addAttribute("phone",user.getPhone());
                       model.addAttribute("message"," ");
                       model.addAttribute("inforList",inforList);
                       model.addAttribute("u_id",user1.getId());
                       model.addAttribute("score",score.getSc());
                       return "userMain";
                   }else{
                       //输入错误一次后必定有记录
                       Login_record record1=login_recordService.queryRecordByPhone(user.getPhone());
                           if(record1.getFail_num()==3){//第三次输入错误
                               model.addAttribute("message","连续三次输入错误,需等待2分钟后再登录");
                           }else{
                               model.addAttribute("message","密码错误");
                           }
                     return "login";
                   }
               }
            }
        }
     return "login";
    }
    //跳转到注册页面
    @RequestMapping("pre_register")
    public String  pre_register()throws Exception {
        return "register";
    }
    //注册
    @RequestMapping("register")
    public @ResponseBody String  register(User user,Model model) throws Exception {
        System.out.println(user.getPhone()+"  :"+user.getPassword());
        if(user!=null){
            if(user.getPhone()!=null&&user.getPhone()!=""){
                User user1=userService.queryUserByPhone(user.getPhone());
                if(user1==null){
                    if(user.getPassword()!=null&&user.getPassword()!=""){
                        String passWord = MD5Utils.md5(user.getPassword());
                        user.setPassword(passWord);
                      boolean b= userService.register(user);
                      if(b){
                          User user2=userService.queryUserByPhone(user.getPhone());
                          Score score=new Score();
                          score.setU_id(user2.getId());
                          score.setSc(500);
                          boolean a=scoreService.addScore(score);
                          if(a){
                              return "success";
                          }
                      }else{
                          return "fail";
                      }
                    }
                }
            }
        }
        return "fail";
    }

    //验证手机号
    @RequestMapping("validate_phone")
    public @ResponseBody String  validate_phone(String phone )throws Exception {
        User user=userService.queryUserByPhone(phone);
        if(user!=null){
            return "exit";
        }
        return "not_exit";
    }
    //手机短信发送
    @RequestMapping("send_validate_number")
    public @ResponseBody String  send_validate_number(String phone,HttpServletRequest request)throws Exception {
        String code=userService.getCode();
        System.out.println("验证码是:"+code);
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(60);
        session.setAttribute("code",code);
        return code;
    }
    //验证码对比
    @RequestMapping("validate_number")
    public @ResponseBody String  validate_number(String massage_number,HttpServletRequest request)throws Exception {
        HttpSession session=request.getSession();
       String code= (String) session.getAttribute("code");
        System.out.println("session获取code:"+massage_number);
        System.out.println("session获取code:"+code);
        if(code!=null){
            if(code.equals(massage_number)){
                return "success";
            }
        }
        System.out.println("验证码错误");
        return "fail";
    }
    //生成图片验证码
    @RequestMapping("getCaptchaCode")
    public @ResponseBody String  getCaptchaCode(String massage_number, HttpServletRequest request, HttpServletResponse response)throws Exception {
        HttpSession session=request.getSession();
        //设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //指定生成的响应图片,一定不能缺少这句话,否则错误.
        response.setContentType("buffImg/jpeg");

       ImageValidate imageValidate=new ImageValidate(80,30,4,2);
       String image_code=imageValidate.getCode();
        BufferedImage buffImg =imageValidate.getBuffImg();
        session.setAttribute("image_code",image_code);
        ImageIO.write(buffImg,"JPEG",response.getOutputStream());
        session.removeAttribute("code");
        return "success";
    }
    //图片验证码验证
    @RequestMapping("validate_code")
    public @ResponseBody String  validate_code(String code_number,HttpServletRequest request)throws Exception {
        HttpSession session=request.getSession();
       String code_number2= (String) session.getAttribute("image_code");
       if(code_number.equals(code_number2)){
           return "success";
       }
       return "fail";
    }
//修改密码
@RequestMapping("update_Password")
public @ResponseBody String update_Password(Integer u_id,String password,String re_password)throws Exception{
    System.out.println(password);
    User user2=userService.queryUserById(u_id);
    if(user2!=null){
        String password2= MD5Utils.md5(password);
        String re_password2=MD5Utils.md5(re_password);
        UserVo userVo=new UserVo();
        User user=new User();
        user.setPhone(user2.getPhone());
        user.setPassword(password2);
        userVo.setRe_password(re_password2);
        userVo.setUser(user);
        boolean b=userService.updatePassword(userVo);
        if(b){
            return "success";
        }
    }

    return "fail";
}
//find_update_Password.action
@RequestMapping("find_update_Password")
public @ResponseBody String find_update_Password(String phone,String password,String re_password)throws Exception{
    User user2=userService.queryUserByPhone(phone);
    if(user2!=null){
        String password2= MD5Utils.md5(password);
        String re_password2=MD5Utils.md5(re_password);
        UserVo userVo=new UserVo();
        User user=new User();
        user.setPhone(user2.getPhone());
        user.setPassword(password2);
        userVo.setRe_password(re_password2);
        userVo.setUser(user);
        boolean b=userService.updatePassword(userVo);
        if(b){
            return "success";
        }
    }

    return "fail";
}
}

