package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Teack;

public interface TeackMapper {
    int insert(Teack record);

    int insertSelective(Teack record);
}