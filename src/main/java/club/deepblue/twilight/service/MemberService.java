package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.Member;
import com.github.pagehelper.PageInfo;

public interface MemberService {
  public int putMemberByObject(Member member);
  public PageInfo<Member> getMemberByCiID(Integer pageIndex, Integer pageSize,Integer ci_id);
  public int outMemberByObject(Member member);

}
