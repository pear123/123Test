package controller;

import mapper.InformationMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Information;
import pojo.Score;
import service.InformationService;
import service.ScoreService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class InformationController {
    @Resource
    private InformationService informationService;
    @Resource
    private ScoreService scoreService;

    //用户主界面
    @RequestMapping("user_main")
    public String user_main(Model model, Integer u_id,Integer score) throws Exception {
        List<Information> inforList = informationService.queryInformationByUid(u_id);
        model.addAttribute("u_id", u_id);
        model.addAttribute("inforList", inforList);
        model.addAttribute("score",score);
        return "userMain";
    }

    //跳转到增加消息的页面
    @RequestMapping("pre_addInformation")
    public String pre_addInformation(Model model, Integer u_id) throws Exception {
        Score score=scoreService.queryScore(u_id);
        model.addAttribute("u_id", u_id);
        model.addAttribute("score",score.getSc());
        return "addInformation";
    }

    //增加消息
    @RequestMapping("addInformation")
    public String addInformation(Model model, Information information) throws Exception {
        String title = new String(information.getTitle().getBytes("ISO8859_1"), "utf-8");
        String content = new String(information.getContent().getBytes("ISO8859_1"), "utf-8");
        Date date = new Date();
        information.setCreatetime(date);
        information.setTitle(title);
        information.setContent(content);
        boolean b = informationService.addInformation(information);
        if (b) {
            Score score=scoreService.queryScore(information.getU_id());
            model.addAttribute("u_id", information.getU_id());
            model.addAttribute("score",score.getSc());
            return "redirect:user_main.action";
        }
        model.addAttribute("u_id", information.getU_id());
        model.addAttribute("missMessage", "添加失败");
        return "addInformation";
        /* return "redirect:pre_addInformation.action";*/

    }

    //删除消息
    @RequestMapping("deleteInformation")
    public @ResponseBody
    String deleteInformation(Model model, Integer infor_id) throws Exception {
        boolean b = informationService.deleteInformation(infor_id);
        if (b) {
            return "success";
        }
        return "fail";
    }
    //跳转到编辑页面
    @RequestMapping("pre_editInformation")
    public String pre_editInformation(Model model, Integer infor_id) throws Exception {
        Information information=informationService.queryInformationById(infor_id);
        Score score=scoreService.queryScore(information.getU_id());
        model.addAttribute("information",information);
        model.addAttribute("score",score.getSc());
        return "edit";
    }
    //修改
    @RequestMapping("editformation")
    public @ResponseBody
    String editInformation(Model model,Information information) throws Exception {
        boolean b = informationService.updateInformation(information);
        if (b) {
            return "success";
        }
        return "fail";
    }
}
