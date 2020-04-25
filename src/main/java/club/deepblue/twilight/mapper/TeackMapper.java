package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Teack;

public interface TeackMapper {
    int deleteByPrimaryKey(Integer t_id);

    int insert(Teack record);

    int insertSelective(Teack record);

    Teack selectByPrimaryKey(Integer t_id);

    int updateByPrimaryKeySelective(Teack record);

    int updateByPrimaryKeyWithBLOBs(Teack record);

    int updateByPrimaryKey(Teack record);
}