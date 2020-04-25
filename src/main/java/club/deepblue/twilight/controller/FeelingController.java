package club.deepblue.twilight.controller;

import club.deepblue.twilight.pojo.Feeling;
import club.deepblue.twilight.service.FeelingService;
import club.deepblue.twilight.utils.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/feeling")
public class FeelingController {
  @Autowired
  FeelingService feelingService;
  @Autowired
  private RedisTemplate redisTemplate;

  @RequestMapping("/isfeeling")
  public JSONObject isFeeling(HttpServletRequest httpServletRequest, Integer fid) {
    JSONObject jsonObject = new JSONObject();
    if (!(null == fid)) {
      String session = RequestUtil.getSession(httpServletRequest);
      ValueOperations valueOperations = redisTemplate.opsForValue();
      JSONObject userJSONObject = (JSONObject) valueOperations.get(session);
      JSONObject userInfoJSONObject = userJSONObject.getJSONObject("userInfo");
      Integer uid = userInfoJSONObject.getInteger("u_id");
      Feeling feeling = new Feeling(uid, fid);
      Feeling isfeeling = feelingService.getFeelingByObject(feeling);
      if (!(null == isfeeling)) {
        jsonObject.put("errcode", 0);
        jsonObject.put("isFeeling", true);
      } else {
        jsonObject.put("errcode", 0);
        jsonObject.put("isFeeling", false);
      }
    } else {
      jsonObject.put("errcode", 10001);
    }
    return jsonObject;
  }

  @RequestMapping("/feeling")
  public JSONObject feeling(HttpServletRequest httpServletRequest, Integer fid) {
    JSONObject jsonObject = new JSONObject();
    if (!(null == fid)) {
      ValueOperations valueOperations = redisTemplate.opsForValue();
      String session = RequestUtil.getSession(httpServletRequest);
      JSONObject userJSONObject = (JSONObject) valueOperations.get(session);
      JSONObject userInfoJSONObject = userJSONObject.getJSONObject("userInfo");
      Integer uid = userInfoJSONObject.getInteger("u_id");
      Date date = new Date();
      Feeling feeling = new Feeling(uid, fid, date);

      int fs = feelingService.setFeelingByObject(feeling);
      if (fs > 0) {
        jsonObject.put("errcode", 0);
      } else {
        jsonObject.put("errcode", 91);
      }
    } else {
      jsonObject.put("errcode", 10001);
    }
    return jsonObject;
  }

  @RequestMapping("/unfeeling")
  public JSONObject unfeeling(HttpServletRequest httpServletRequest, Integer fid) {
    JSONObject jsonObject = new JSONObject();
    if (!(null == fid)) {
      ValueOperations valueOperations = redisTemplate.opsForValue();
      String session = RequestUtil.getSession(httpServletRequest);
      JSONObject userJSONObject = (JSONObject) valueOperations.get(session);
      JSONObject userInfoJSONObject = userJSONObject.getJSONObject("userInfo");
      Integer uid = userInfoJSONObject.getInteger("u_id");

      Feeling feeling = new Feeling(uid, fid);

      int fs = feelingService.deleteFeelingByObject(feeling);
      if (fs > 0) {
        jsonObject.put("errcode", 0);
      } else {
        jsonObject.put("errcode", 91);
      }
    } else {
      jsonObject.put("errcode", 10001);
    }
    return jsonObject;
  }
}
