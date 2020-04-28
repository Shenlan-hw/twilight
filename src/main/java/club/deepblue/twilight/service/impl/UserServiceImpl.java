package club.deepblue.twilight.service.impl;


import club.deepblue.twilight.mapper.UserMapper;
import club.deepblue.twilight.pojo.User;
import club.deepblue.twilight.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "REDIS")
@Service
public class UserServiceImpl implements UserService {
  @Resource
  private UserMapper userMapper;

  @Override
  @CacheEvict(key = "'uid-'+#user.u_id")
  public Integer setUserByObject(User user) {
    return userMapper.insert(user);
  }

  @Override
  public User getUserByOpenID(String openid) {
    return userMapper.selectByOpenId(openid);
  }

  @Override
  @CacheEvict(key = "'uid-'+#user.u_id")
  public int editUserByObject(User user) {
    return userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public List<User> getUsersByLikeContent(String content) {
    return userMapper.likeByContent(content);
  }

  @Override
  @Cacheable(key = "'uid-'+#uid", unless = "#result == null")
  public User getUserByUID(Integer uid) {
    return userMapper.selectOtherByPrimaryKey(uid);
  }

}
