package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.Cinema;
import com.github.pagehelper.PageInfo;

public interface CinemaService {
  public Integer setCinemaByObject(Cinema cinema);
  public Cinema getCinemaByCiID(Integer ci_id);
  public int destroyCinemaByCiID(Integer ci_id);
  public PageInfo<Cinema> searchCinema(Integer pageIndex, Integer pageSize,String content);
  public PageInfo<Cinema> getAllCinema(Integer pageIndex, Integer pageSize);
}
