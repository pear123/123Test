package pojo;

import java.util.Date;

public class Password_record {
    private Integer p_id;
    private String u_phone;
    private String status;
    private Date p_createtime;
    private Integer validate_number;

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_phone() {
        return u_phone;
    }

    public Integer getValidate_number() {
        return validate_number;
    }

    public void setValidate_number(Integer validate_number) {
        this.validate_number = validate_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getP_createtime() {
        return p_createtime;
    }

    public void setP_createtime(Date p_createtime) {
        this.p_createtime = p_createtime;
    }

    public Password_record() {
    }
}
