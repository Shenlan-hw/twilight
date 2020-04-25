package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Friend;

import java.util.List;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);

    List<Friend> selectByUID(Integer u_id);
}
