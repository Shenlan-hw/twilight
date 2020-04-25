package club.deepblue.twilight.pojo;

import java.io.Serializable;
import java.util.Date;

public class Cinema implements Serializable {
    private Integer ci_id;

    private Integer u_id;

    private Byte ci_type;

    private Byte ci_number;

    private Date ci_created_time;

    private String ci_notice;

    public Integer getCi_id() {
        return ci_id;
    }

    public void setCi_id(Integer ci_id) {
        this.ci_id = ci_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Byte getCi_type() {
        return ci_type;
    }

    public void setCi_type(Byte ci_type) {
        this.ci_type = ci_type;
    }

    public Byte getCi_number() {
        return ci_number;
    }

    public void setCi_number(Byte ci_number) {
        this.ci_number = ci_number;
    }

    public Date getCi_created_time() {
        return ci_created_time;
    }

    public void setCi_created_time(Date ci_created_time) {
        this.ci_created_time = ci_created_time;
    }

    public String getCi_notice() {
        return ci_notice;
    }

    public void setCi_notice(String ci_notice) {
        this.ci_notice = ci_notice == null ? null : ci_notice.trim();
    }
}
