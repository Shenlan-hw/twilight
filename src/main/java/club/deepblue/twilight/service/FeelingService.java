package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.Feeling;

public interface FeelingService {
  int setFeelingByObject(Feeling feeling);
  int deleteFeelingByObject(Feeling feeling);

  Feeling getFeelingByObject(Feeling feeling);
}
