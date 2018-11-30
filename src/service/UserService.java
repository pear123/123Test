package service;

import mapper.Login_recordMapper;
import mapper.UserMapper;
import pojo.Login_record;
import pojo.User;
import pojo.UserVo;

import java.util.Date;
import java.util.List;

public class UserService {
    private UserMapper userMapper;
    private Login_recordMapper login_recordMapper;

    public void setLogin_recordMapper(Login_recordMapper login_recordMapper) {
        this.login_recordMapper = login_recordMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public List<User> getAllUser() throws Exception {
        return userMapper.querryUser();
    }
    //生成4位数的验证码
    public String getCode(){
        String code = (int) (Math.random() * 9000 + 1000) + "";//强制类型转换为整数，因为random函数返回double
        return code;
    }
    //注册用户
    public boolean register(User user)throws Exception{
    User user1=userMapper.querryUserByPhone(user.getPhone());
    if(user.getPassword().length()<4){
        return false;
    }
    if(user1==null){
        userMapper.addUser(user);
        return true;
    }
    return false;
    }
    //登录
    public boolean login(User user, Date date)throws Exception{
        User user1=userMapper.querryUserByPhone(user.getPhone());
        if(user1!=null){//用户不为空
            if(user.getPassword().equals(user1.getPassword())){
                Login_record record2=login_recordMapper.querryRecordByPhone(user.getPhone());
                if(record2!=null){
                    login_recordMapper.deleteRecord(record2.getLogin_record_id());//成功登录删除失败记录
                }
                return true;
            }else{//密码输入错误
                Login_record record=login_recordMapper.querryRecordByPhone(user.getPhone());
                if(record==null){//记录为空
                    record=new Login_record();
                    record.setU_phone(user.getPhone());
                    record.setLogin_date(date);
                    record.setFail_num(1);
                    record.setLock_flag("0");
                    login_recordMapper.addRecord(record);
                }else{//记录不为空
                    record.setU_phone(user.getPhone());
                    record.setLogin_date(date);
                    record.setFail_num(record.getFail_num()+1);
                   if(record.getFail_num()==3){
                       record.setLock_flag("1");//锁定
                   }else{
                       record.setLock_flag("0");
                   }
                   login_recordMapper.updateRecord(record);
                   return false;
                }
            }
        }
        return false;
    }
    //根据电话号码找用户
    public User queryUserByPhone(String phone)throws Exception{
        User user=null;
        user =userMapper.querryUserByPhone(phone);
        return user;
    }
    //更改密码
    public boolean updatePassword(UserVo userVo)throws Exception{
        User user=userMapper.querryUserByPhone(userVo.getUser().getPhone());
        if(userVo.getRe_password().length()<4){
            return false;
        }
        if(user!=null){
            if(userVo.getRe_password().equals(userVo.getUser().getPassword())){
                userMapper.updatePassword(userVo);
                return true;
            }

        }
        return false;
    }
//根据用户id找用户
    public User queryUserById(Integer id)throws Exception{
        return userMapper.querryUserById(id);
    }
}
