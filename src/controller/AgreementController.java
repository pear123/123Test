package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Agreement;
import service.AgreementService;

import javax.annotation.Resource;

@Controller
public class AgreementController {
    @Resource
    private AgreementService agreementService;
    //查看注册协议
    @RequestMapping("queryAgreement")
    public String  queryAgreement(Model model)throws Exception {
        Agreement agreement=agreementService.queryAgreementByName("注册协议");
        model.addAttribute("agreement",agreement);
        return "queryAgreement";
    }
}
