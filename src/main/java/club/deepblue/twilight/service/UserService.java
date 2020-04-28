package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.User;

import java.util.List;

public interface UserService {
  public Integer setUserByObject(User user);

  public User getUserByOpenID(String openid);

  public int editUserByObject(User user);

  public List<User> getUsersByLikeContent(String content);

  User getUserByUID(Integer uid);
}

