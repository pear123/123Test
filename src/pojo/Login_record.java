package pojo;

import java.util.Date;

public class Login_record {
    private Integer login_record_id;
    private String u_phone;
    private Date login_date;
    private Integer fail_num;
    private String lock_flag;

    public Integer getLogin_record_id() {
        return login_record_id;
    }

    public void setLogin_record_id(Integer login_record_id) {
        this.login_record_id = login_record_id;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public Integer getFail_num() {
        return fail_num;
    }

    public void setFail_num(Integer fail_num) {
        this.fail_num = fail_num;
    }

    public String getLock_flag() {
        return lock_flag;
    }

    public void setLock_flag(String lock_flag) {
        this.lock_flag = lock_flag;
    }

    public Login_record() {
    }
}
