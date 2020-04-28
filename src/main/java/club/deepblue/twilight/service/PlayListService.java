package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.PlayList;
import com.github.pagehelper.PageInfo;

public interface PlayListService {
  public int setPlayListByObject(PlayList playList);
  public PageInfo<PlayList> getPlayListByCiID(Integer pageIndex, Integer pageSize,Integer ci_id);
  public int deletePlayListByObject(PlayList playList);
  public int deletePlayListByCiID(Integer ci_id);
}
