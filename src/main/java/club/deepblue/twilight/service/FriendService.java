package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.Friend;

import java.util.List;

public interface FriendService {
  public List<Friend> getFriendsByUID(Integer u_id);
}
