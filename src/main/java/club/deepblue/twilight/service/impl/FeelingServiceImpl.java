package club.deepblue.twilight.service.impl;

import club.deepblue.twilight.mapper.FeelingMapper;
import club.deepblue.twilight.pojo.Feeling;
import club.deepblue.twilight.service.FeelingService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@CacheConfig(cacheNames = "REDIS")
@Service
public class FeelingServiceImpl implements FeelingService {
  @Resource
  FeelingMapper feelingMapper;
  @Override
  public int setFeelingByObject(Feeling feeling) {
    return feelingMapper.insert(feeling);
  }

  @Override
  public int deleteFeelingByObject(Feeling feeling) {
    return feelingMapper.deleteByObject(feeling);
  }

  @Override
  public Feeling getFeelingByObject(Feeling feeling) {
    return feelingMapper.selectByObject(feeling);
  }
}
