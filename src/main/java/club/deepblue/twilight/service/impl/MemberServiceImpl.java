package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.MemberMapper;
import club.deepblue.twilight.pojo.Member;
import club.deepblue.twilight.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
  @Resource
  private MemberMapper memberMapper;

  @Override
  public int putMemberByObject(Member member) {
    return memberMapper.insertSelective(member);
  }

  @Override
  public PageInfo<Member> getMemberByCiID(Integer pageIndex, Integer pageSize,Integer ci_id) {
    PageHelper.startPage(pageIndex,pageSize);
    List<Member> members=memberMapper.selectByCiID(ci_id);
    PageInfo<Member> pageInfo=new PageInfo<Member>(members);
    return pageInfo;
  }

  @Override
  public int outMemberByObject(Member member) {
    return memberMapper.deleteByObject(member);
  }
}
