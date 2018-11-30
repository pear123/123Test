package mapper;

import pojo.Information;

import java.util.List;

public interface InformationMapper {
    public List<Information> querryInforList()throws Exception;
    public void  addInformation(Information information)throws Exception;
    public void deleteInformation(Integer id)throws Exception;
    public void updateInformation(Information information)throws Exception;
    public List<Information> querryInformationByU_id(Integer u_id)throws Exception;
    public Information querryInformationById(Integer id)throws Exception;

}
