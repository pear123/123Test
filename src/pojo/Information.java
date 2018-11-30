package pojo;

import java.util.Date;

public class Information {
    private Integer infor_id;
    private Date createtime;
    private String title;
    private String content;
    private Integer u_id;

    public Integer getInfor_id() {
        return infor_id;
    }

    public void setInfor_id(Integer infor_id) {
        this.infor_id = infor_id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Information() {
    }
}
