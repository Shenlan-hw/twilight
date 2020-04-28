package club.deepblue.twilight.controller;

import club.deepblue.twilight.pojo.Friend;
import club.deepblue.twilight.service.FriendService;
import club.deepblue.twilight.utils.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {

  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private FriendService friendService;
  private static final Integer PAGE_SIZE = 20;

  @RequestMapping("/getFriends")
  public JSONObject getFriends(HttpServletRequest httpServletRequest,Integer idx){
    String session = RequestUtil.getSession(httpServletRequest);
    ValueOperations valueOperations = redisTemplate.opsForValue();
    JSONObject userJSONObject= (JSONObject) valueOperations.get(session);
    JSONObject userInfoJSONObject=userJSONObject.getJSONObject("userInfo");
    Integer u_id=userInfoJSONObject.getInteger("u_id");
    PageInfo<Friend> friendPageInfo=friendService.getFriendsByUID(idx,PAGE_SIZE,u_id);

    JSONObject jsonObject=new JSONObject();
    if(null != friendPageInfo ){
      jsonObject.put("friendPageInfo",friendPageInfo);
      jsonObject.put("errcode",0);
      return jsonObject;
    }else {
      jsonObject.put("errcode",71);
      return jsonObject;
    }
  }
}
