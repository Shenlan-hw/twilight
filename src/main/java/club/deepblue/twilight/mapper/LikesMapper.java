package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Likes;
import club.deepblue.twilight.pojo.LikesKey;

public interface LikesMapper {
    int deleteByPrimaryKey(LikesKey key);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(LikesKey key);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
}