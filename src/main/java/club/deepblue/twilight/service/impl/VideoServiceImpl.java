package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.VideoMapper;
import club.deepblue.twilight.pojo.Video;
import club.deepblue.twilight.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "REDIS")
@Service
public class VideoServiceImpl implements VideoService {
  @Resource
  private VideoMapper videoMapper;

  public PageInfo<Video> getAllVideo(Integer pageIndex, Integer pageSize){
    PageHelper.startPage(pageIndex,pageSize);
    List<Video> videos=videoMapper.selectAll();
    PageInfo<Video> pageInfo=new PageInfo<Video>(videos);
    return pageInfo;
  }

  @Override
  public PageInfo<Video> searchVideo(Integer pageIndex, Integer pageSize,String content) {
    PageHelper.startPage(pageIndex,pageSize);
    List<Video> videos=videoMapper.selectByContent(content);
    PageInfo<Video> pageInfo=new PageInfo<Video>(videos);
    return pageInfo;
  }
}
