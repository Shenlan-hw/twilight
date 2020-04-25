package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Feeling;

public interface FeelingMapper {
    int deleteByPrimaryKey(Integer f_id);

    int insert(Feeling record);

    int insertSelective(Feeling record);

    Feeling selectByPrimaryKey(Integer f_id);

    int updateByPrimaryKeySelective(Feeling record);

    int updateByPrimaryKey(Feeling record);

  int deleteByObject(Feeling feeling);

  Feeling selectByObject(Feeling feeling);
}
