package mapper;

import pojo.User;
import pojo.UserVo;

import java.util.List;

public interface UserMapper {
    public List<User> querryUser() throws Exception;
    public void addUser(User user) throws Exception;
    public void update(UserVo userVo) throws Exception;
    public User querryUserByPhone(String phone)throws Exception;
    public User querryUserById(Integer id)throws Exception;
    public void updatePassword(UserVo userVo)throws Exception;
}
