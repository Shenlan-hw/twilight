package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer ml_id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer ml_id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}