package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.PlayList;

public interface PlayListMapper {
    int deleteByPrimaryKey(Integer pl_id);

    int insert(PlayList record);

    int insertSelective(PlayList record);

    PlayList selectByPrimaryKey(Integer pl_id);

    int updateByPrimaryKeySelective(PlayList record);

    int updateByPrimaryKey(PlayList record);
}