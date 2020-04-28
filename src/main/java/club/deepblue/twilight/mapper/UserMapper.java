package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer u_id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer u_id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByOpenId(String openid);

  List<User> likeByContent(String content);

  User selectOtherByPrimaryKey(Integer uid);
}
