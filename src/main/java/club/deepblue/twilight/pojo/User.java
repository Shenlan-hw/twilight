package club.deepblue.twilight.pojo;

import com.fasterxml.jackson.databind.deser.Deserializers;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer u_id;

    private String u_openid;

    private String u_nickname;

    private String u_avatar_url;

    private Byte u_gender;

    private String u_mobile;

    private String u_region;

    private String u_introduction;

    private Byte u_power;

    private Date u_created_time;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_openid() {
        return u_openid;
    }

    public void setU_openid(String u_openid) {
        this.u_openid = u_openid == null ? null : u_openid.trim();
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname == null ? null : u_nickname.trim();
    }

    public String getU_avatar_url() {
        return u_avatar_url;
    }

    public void setU_avatar_url(String u_avatar_url) {
        this.u_avatar_url = u_avatar_url == null ? null : u_avatar_url.trim();
    }

    public Byte getU_gender() {
        return u_gender;
    }

    public void setU_gender(Byte u_gender) {
        this.u_gender = u_gender;
    }

    public String getU_mobile() {
        return u_mobile;
    }

    public void setU_mobile(String u_mobile) {
        this.u_mobile = u_mobile == null ? null : u_mobile.trim();
    }

    public String getU_region() {
        return u_region;
    }

    public void setU_region(String u_region) {
        this.u_region = u_region == null ? null : u_region.trim();
    }

    public String getU_introduction() {
        return u_introduction;
    }

    public void setU_introduction(String u_introduction) {
        this.u_introduction = u_introduction == null ? null : u_introduction.trim();
    }

    public Byte getU_power() {
        return u_power;
    }

    public void setU_power(Byte u_power) {
        this.u_power = u_power;
    }

    public Date getU_created_time() {
        return u_created_time;
    }

    public void setU_created_time(Date u_created_time) {
        this.u_created_time = u_created_time;
    }
}
