package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.PlayList;

import java.util.List;

public interface PlayListMapper {
    int deleteByPrimaryKey(Integer pl_id);

    int insert(PlayList record);

    int insertSelective(PlayList record);

    PlayList selectByPrimaryKey(Integer pl_id);

    int updateByPrimaryKeySelective(PlayList record);

    int updateByPrimaryKey(PlayList record);

    List<PlayList> selectByCiID(Integer ci_id);

  int deleteByObject(PlayList playList);

  int deleteByCiId(Integer ci_id);
}
