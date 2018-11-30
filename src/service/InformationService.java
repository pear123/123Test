package service;

import mapper.InformationMapper;
import mapper.UserMapper;
import pojo.Information;
import pojo.User;

import java.util.List;

public class InformationService {
    private InformationMapper informationMapper;
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void setInformationMapper(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }
    //增加消息
    public boolean addInformation(Information information)throws Exception{
        informationMapper.addInformation(information);
        return true;
    }
    //删除消息
    public boolean deleteInformation(Integer id)throws Exception{
        Information information=informationMapper.querryInformationById(id);
        if(information!=null){
            informationMapper.deleteInformation(id);
            return true;
        }
        return false;
    }
    //修改消息
    public boolean updateInformation(Information information)throws Exception{
        Information information1=informationMapper.querryInformationById(information.getInfor_id());
        if(information1!=null){
            informationMapper.updateInformation(information);
            return true;
        }
        return false;
    }
    //查看所有消息列表
    public List<Information> queryInformationList()throws Exception{
        return informationMapper.querryInforList();
    }
    //根据用户id查找消息列表
    public List<Information> queryInformationByUid(Integer id)throws Exception{
        User user=userMapper.querryUserById(id);
        if(user!=null){
            return informationMapper.querryInformationByU_id(id);
        }
        return null;
    }
    //根据id查找消息
    public Information queryInformationById(Integer id)throws Exception{
       Information information=null;
       information=informationMapper.querryInformationById(id);
       return information;
    }
}
