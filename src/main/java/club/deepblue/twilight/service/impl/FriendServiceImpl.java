package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.FriendMapper;
import club.deepblue.twilight.pojo.Friend;
import club.deepblue.twilight.service.FriendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "REDIS")
@Service
public class FriendServiceImpl implements FriendService {
  @Resource
  private FriendMapper friendMapper;
  @Override
  public PageInfo<Friend> getFriendsByUID(Integer pageIndex, Integer pageSize,Integer u_id) {
    PageHelper.startPage(pageIndex,pageSize);
    List<Friend> friends=friendMapper.selectByUID(u_id);
    PageInfo<Friend> pageInfo=new PageInfo<Friend>(friends);
    return pageInfo;
  }
}
