package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.FriendMapper;
import club.deepblue.twilight.pojo.Friend;
import club.deepblue.twilight.service.FriendService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "REDIS")
@Service
public class FriendServiceImpl implements FriendService {
  @Resource
  FriendMapper friendMapper;
  @Override
  @Cacheable(key = "#u_id")
  public List<Friend> getFriendsByUID(Integer u_id) {
    return friendMapper.selectByUID(u_id);
  }
}
