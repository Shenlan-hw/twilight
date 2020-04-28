package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.FeelingMapper;
import club.deepblue.twilight.pojo.Feeling;
import club.deepblue.twilight.service.FeelingService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@CacheConfig(cacheNames = "REDIS")
@Service
public class FeelingServiceImpl implements FeelingService {
  @Resource
  private FeelingMapper feelingMapper;

  @Override
  @CacheEvict(key = "'feeling-'+#feeling.u_id+'-'+#feeling.is_f_id")
  public int setFeelingByObject(Feeling feeling) {
    return feelingMapper.insert(feeling);
  }

  @Override
  @CacheEvict(key = "'feeling-'+#feeling.u_id+'-'+#feeling.is_f_id")
  public int deleteFeelingByObject(Feeling feeling) {
    return feelingMapper.deleteByObject(feeling);
  }

  @Override
  @Cacheable(key = "'feeling-'+#feeling.u_id+'-'+#feeling.is_f_id")
  public Feeling getFeelingByObject(Feeling feeling) {
    return feelingMapper.selectByObject(feeling);
  }
}
