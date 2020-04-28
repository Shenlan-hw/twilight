package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.Friend;
import com.github.pagehelper.PageInfo;

public interface FriendService {
  public PageInfo<Friend> getFriendsByUID(Integer pageIndex, Integer pageSize, Integer u_id);
}
