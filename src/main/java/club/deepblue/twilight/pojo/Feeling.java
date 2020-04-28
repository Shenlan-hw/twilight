package club.deepblue.twilight.pojo;

import java.io.Serializable;
import java.util.Date;

public class Feeling implements Serializable {
    private Integer f_id;

    private Integer u_id;

    private Integer is_f_id;

    private Date f_time;

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getIs_f_id() {
        return is_f_id;
    }

    public void setIs_f_id(Integer is_f_id) {
        this.is_f_id = is_f_id;
    }

    public Date getF_time() {
        return f_time;
    }

    public void setF_time(Date f_time) {
        this.f_time = f_time;
    }
}
