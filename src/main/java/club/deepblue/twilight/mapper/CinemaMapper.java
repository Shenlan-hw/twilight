package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Cinema;

import java.util.List;

public interface CinemaMapper {
    int deleteByPrimaryKey(Integer ci_id);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer ci_id);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKeyWithBLOBs(Cinema record);

    int updateByPrimaryKey(Cinema record);

    List<Cinema> selectByContent(String content);

  List<Cinema> selectAll();
}
