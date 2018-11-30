package mapper;

import pojo.Agreement;

public interface AgreementMapper {
    public void addAgreement(Agreement agreement)throws Exception;
    public void updateAgreement(Agreement agreement)throws Exception;
    public Agreement querryAgreementById(Integer id)throws Exception;
    public void deleteAgreement(Integer id)throws Exception;
    public Agreement querryAgreementByName(String name)throws Exception;
}
