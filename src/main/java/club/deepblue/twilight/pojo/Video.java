package club.deepblue.twilight.pojo;

public class Video {
    private Integer v_id;

    private String v_name;

    private String v_cover;

    private String v_url;

    private String v_about;

    public Integer getV_id() {
        return v_id;
    }

    public void setV_id(Integer v_id) {
        this.v_id = v_id;
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

    public String getV_url() {
        return v_url;
    }

    public void setV_url(String v_url) {
        this.v_url = v_url == null ? null : v_url.trim();
    }

    public String getV_about() {
        return v_about;
    }

    public void setV_about(String v_about) {
        this.v_about = v_about == null ? null : v_about.trim();
    }
}