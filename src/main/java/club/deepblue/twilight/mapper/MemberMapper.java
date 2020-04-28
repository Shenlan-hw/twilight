package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Member;

import java.util.List;

public interface MemberMapper {
    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByCiID(Integer ci_id);

  int deleteByObject(Member member);
}
