package club.deepblue.twilight.pojo;

public class Member {
    private Integer ml_id;

    private Integer ci_id;

    private Integer u_id;

    private String u_nickname;

    private String u_avatar_url;

    public Integer getMl_id() {
        return ml_id;
    }

    public void setMl_id(Integer ml_id) {
        this.ml_id = ml_id;
    }

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
}