package club.deepblue.twilight.pojo;

import java.util.Date;

public class Teack {
    private Integer t_id;

    private Integer u_id;

    private Integer v_id;

    private Date t_time;

    private String u_nickname;

    private String u_avatar_url;

    private Byte u_gender;

    private String v_name;

    private String v_cover;

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

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name == null ? null : v_name.trim();
    }

    public String getV_cover() {
        return v_cover;
    }

    public void setV_cover(String v_cover) {
        this.v_cover = v_cover == null ? null : v_cover.trim();
    }

    public String getT_content() {
        return t_content;
    }

    public void setT_content(String t_content) {
        this.t_content = t_content == null ? null : t_content.trim();
    }
}