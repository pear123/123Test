package mapper;

import pojo.Login_record;

public interface Login_recordMapper {
    public void addRecord(Login_record login_record)throws Exception;
    public void updateRecord(Login_record login_record)throws Exception;
    public Login_record querryRecordByPhone(String u_phone)throws Exception;
    public void deleteRecord(Integer login_record_id)throws Exception;
    public Login_record querryRecordById(Integer login_record_id)throws Exception;
}
