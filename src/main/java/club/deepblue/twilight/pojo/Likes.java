package club.deepblue.twilight.pojo;

import java.util.Date;

public class Likes extends LikesKey {
    private Date likes_time;

    public Date getLikes_time() {
        return likes_time;
    }

    public void setLikes_time(Date likes_time) {
        this.likes_time = likes_time;
    }
}