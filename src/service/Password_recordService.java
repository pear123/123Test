package service;

import mapper.Password_recordMapper;
import mapper.UserMapper;
import pojo.Password_record;
import pojo.User;

import java.util.Date;

public class Password_recordService {
    private Password_recordMapper password_recordMapper;
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void setPassword_recordMapper(Password_recordMapper password_recordMapper) {
        this.password_recordMapper = password_recordMapper;
    }
    //发送验证码时，数据库对密码记录的操作（找回密码）
    public boolean addPassword_record(Password_record password_record)throws Exception{
        User user=userMapper.querryUserByPhone(password_record.getU_phone());
        Password_record password_record1=password_recordMapper.querryRecordByPhone(password_record.getU_phone());
        if(user!=null){
            if(password_record1==null){
                System.out.println("密码记录为空");
                password_recordMapper.addRecord(password_record);
                return true;
            }else{
                System.out.println("密码记录不为空");
                    password_recordMapper.deleteRecord(password_record1.getP_id());//先删除原来的找回记录
                    password_recordMapper.addRecord(password_record);
                    return true;
            }
        }
        return false;
    }
    //删除记录
    public boolean deletePasswordRecord(Integer p_id)throws Exception{
        Password_record password_record=password_recordMapper.querryRecordById(p_id);
        if(password_record!=null){
            password_recordMapper.deleteRecord(p_id);
            return true;
        }
        return false;
    }
    //获得密码
    public String getPassword(String phone, Integer validate_number, Date date)throws Exception{
        User user=userMapper.querryUserByPhone(phone);
        if(user!=null){
            Password_record password_record=password_recordMapper.querryRecordByPhone(phone);
            if(password_record!=null){
                int c=(int)((date.getTime()-password_record.getP_createtime().getTime())/1000);
                if(c<=60){
                    if(password_record.getU_phone().equals(phone)&&password_record.getValidate_number().equals(validate_number)){
                        password_recordMapper.deleteRecord(password_record.getP_id());
                        return user.getPassword();
                    }
                }
            }
        }
        return "";
    }


}
