package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.PlayListMapper;
import club.deepblue.twilight.pojo.PlayList;
import club.deepblue.twilight.service.PlayListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService {
  @Resource
  private PlayListMapper playListMapper;
  @Override
  public int setPlayListByObject(PlayList playList) {
    return playListMapper.insertSelective(playList);
  }

  @Override
  public PageInfo<PlayList> getPlayListByCiID(Integer pageIndex, Integer pageSize, Integer ci_id) {
    PageHelper.startPage(pageIndex,pageSize);
    List<PlayList> playLists=playListMapper.selectByCiID(ci_id);
    PageInfo<PlayList> pageInfo=new PageInfo<PlayList>(playLists);
    return pageInfo;
  }

  @Override
  public int deletePlayListByObject(PlayList playList) {
    return playListMapper.deleteByObject(playList);
  }

  @Override
  public int deletePlayListByCiID(Integer ci_id) {
    return playListMapper.deleteByCiId(ci_id);
  }
}
