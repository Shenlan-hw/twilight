package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.MessageMapper;
import club.deepblue.twilight.pojo.Message;
import club.deepblue.twilight.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "REDIS")
@Service
public class MessageServiceImpl implements MessageService {
  @Resource
  MessageMapper messageMapper;

  @Cacheable(key="#u_id+'-'+re_u_id")
  public PageInfo<Message> getMessageListByID(Integer pageIndex, Integer pageSize,Integer u_id,Integer re_u_id){
    PageHelper.startPage(pageIndex,pageSize);
    List<Message> messages=messageMapper.selectByUIDAndReID(u_id,re_u_id);
    PageInfo<Message> pageInfo=new PageInfo<Message>(messages);
    return pageInfo;
  }


}
