package service;

import mapper.Login_recordMapper;
import pojo.Login_record;

public class Login_recordService {
    private Login_recordMapper login_recordMapper;

    public void setLogin_recordMapper(Login_recordMapper login_recordMapper) {
        this.login_recordMapper = login_recordMapper;
    }
    //增加记录
    public boolean addRecord(Login_record login_record)throws Exception{
        if(login_record!=null){
            if(login_record.getU_phone()!=null){
               boolean b=login_record.getU_phone().matches("[0-9]{1,}");//是否都为数字
                if(login_record.getU_phone().length()==11&&b){
                    login_recordMapper.addRecord(login_record);
                    return true;
                }
            }
        }
        return false;
    }
    //修改记录
    public boolean updateRecord(Login_record login_record)throws Exception{
        Login_record login_record2=login_recordMapper.querryRecordByPhone(login_record.getU_phone());
        if(login_record2!=null){
            login_recordMapper.updateRecord(login_record);
            return true;
        }
        return false;
    }
    //根据号码查找记录
    public Login_record queryRecordByPhone(String phone)throws Exception{
        Login_record record=login_recordMapper.querryRecordByPhone(phone);
        return record;
    }
    //删除记录
    public boolean deleteRecord(Integer login_record_id)throws Exception{
        Login_record login_record=login_recordMapper.querryRecordById(login_record_id);
        if(login_record!=null){
            login_recordMapper.deleteRecord(login_record_id);
            return true;
        }
        return false;
    }
}
