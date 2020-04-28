package club.deepblue.twilight.pojo;

public class Friend {
    private Integer u_id;

    private Integer f_id;

    private String u_nickname;

    private String u_avatar_url;

    private String u_introduction;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
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

    public String getU_introduction() {
        return u_introduction;
    }

    public void setU_introduction(String u_introduction) {
        this.u_introduction = u_introduction == null ? null : u_introduction.trim();
    }
}