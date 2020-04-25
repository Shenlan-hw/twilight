package club.deepblue.twilight.pojo;

import java.util.Date;

public class Teack {
    private Integer t_id;

    private Integer u_id;

    private Integer v_id;

    private Date t_time;

    private String t_content;

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getV_id() {
        return v_id;
    }

    public void setV_id(Integer v_id) {
        this.v_id = v_id;
    }

    public Date getT_time() {
        return t_time;
    }

    public void setT_time(Date t_time) {
        this.t_time = t_time;
    }

    public String getT_content() {
        return t_content;
    }

    public void setT_content(String t_content) {
        this.t_content = t_content == null ? null : t_content.trim();
    }
}