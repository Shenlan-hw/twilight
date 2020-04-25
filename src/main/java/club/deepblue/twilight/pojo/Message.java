package club.deepblue.twilight.pojo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Integer me_id;

    private Integer u_id;

    private Integer re_u_id;

    private Date me_time;

    private Byte is_delivered;

    private String me_content;

    public Integer getMe_id() {
        return me_id;
    }

    public void setMe_id(Integer me_id) {
        this.me_id = me_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getRe_u_id() {
        return re_u_id;
    }

    public void setRe_u_id(Integer re_u_id) {
        this.re_u_id = re_u_id;
    }

    public Date getMe_time() {
        return me_time;
    }

    public void setMe_time(Date me_time) {
        this.me_time = me_time;
    }

    public Byte getIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(Byte is_delivered) {
        this.is_delivered = is_delivered;
    }

    public String getMe_content() {
        return me_content;
    }

    public void setMe_content(String me_content) {
        this.me_content = me_content == null ? null : me_content.trim();
    }
}
