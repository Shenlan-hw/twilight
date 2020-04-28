package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.Video;
import com.github.pagehelper.PageInfo;

public interface VideoService {
  public PageInfo<Video> getAllVideo(Integer pageIndex, Integer pageSize);

  public PageInfo<Video> searchVideo(Integer pageIndex, Integer pageSize,String content);
}
