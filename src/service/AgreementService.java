package service;

import mapper.AgreementMapper;
import pojo.Agreement;

public class AgreementService {
    private AgreementMapper agreementMapper;

    public void setAgreementMapper(AgreementMapper agreementMapper) {
        this.agreementMapper = agreementMapper;
    }
    public Agreement queryAgreementById(Integer id)throws Exception{
        return agreementMapper.querryAgreementById(id);
    }
    //根据名字查询
    public Agreement queryAgreementByName(String name)throws Exception{
        return agreementMapper.querryAgreementByName(name);
    }
}
