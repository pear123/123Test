package mapper;

import pojo.Password_record;

public interface Password_recordMapper {
    public void addRecord(Password_record password_record)throws Exception;
    public void updateRecord(Password_record password_record)throws Exception;
    public Password_record querryRecordByPhone(String u_phone)throws Exception;
    public void deleteRecord(Integer p_id)throws Exception;
    public Password_record querryRecordById(Integer p_id)throws Exception;
}
