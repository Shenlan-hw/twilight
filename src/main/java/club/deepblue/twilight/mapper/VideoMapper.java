package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Video;

public interface VideoMapper {
    int deleteByPrimaryKey(Integer v_id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer v_id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKeyWithBLOBs(Video record);

    int updateByPrimaryKey(Video record);
}