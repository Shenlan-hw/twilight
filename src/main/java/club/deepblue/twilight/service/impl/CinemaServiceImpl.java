package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.CinemaMapper;
import club.deepblue.twilight.pojo.Cinema;
import club.deepblue.twilight.service.CinemaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "REDIS")
@Service
public class CinemaServiceImpl implements CinemaService {
  @Resource
  private CinemaMapper cinemaMapper;
  @Override
  public Integer setCinemaByObject(Cinema cinema) {
    return cinemaMapper.insertSelective(cinema);
  }

  @Override
  @Cacheable(key="'ci-'+#ci_id")
  public Cinema getCinemaByCiID(Integer ci_id) {
    return cinemaMapper.selectByPrimaryKey(ci_id);
  }

  @Override
  @CacheEvict(key="'ci-'+#ci_id")
  public int destroyCinemaByCiID(Integer ci_id) {
    return cinemaMapper.deleteByPrimaryKey(ci_id);
  }

  @Override
  public PageInfo<Cinema> searchCinema(Integer pageIndex, Integer pageSize, String content) {
    PageHelper.startPage(pageIndex,pageSize);
    List<Cinema> cinemas=cinemaMapper.selectByContent(content);
    PageInfo<Cinema> pageInfo=new PageInfo<Cinema>(cinemas);
    return pageInfo;
  }

  @Override
  public PageInfo<Cinema> getAllCinema(Integer pageIndex, Integer pageSize) {
    PageHelper.startPage(pageIndex,pageSize);
    List<Cinema> cinemas=cinemaMapper.selectAll();
    PageInfo<Cinema> pageInfo=new PageInfo<Cinema>(cinemas);
    return pageInfo;
  }
}
