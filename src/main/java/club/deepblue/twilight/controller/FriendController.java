package club.deepblue.twilight.controller;

import club.deepblue.twilight.pojo.Friend;
import club.deepblue.twilight.service.FriendService;
import club.deepblue.twilight.utils.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private FriendService friendService;


  @RequestMapping("/getFriends")
  public JSONObject getFriends(HttpServletRequest httpServletRequest){
    String session = RequestUtil.getSession(httpServletRequest);
    ValueOperations valueOperations = redisTemplate.opsForValue();
    JSONObject userJSONObject= (JSONObject) valueOperations.get(session);
    JSONObject userInfoJSONObject=userJSONObject.getJSONObject("userInfo");
    Integer u_id=userInfoJSONObject.getInteger("u_id");
    List<Friend> friends=friendService.getFriendsByUID(u_id);

    JSONObject jsonObject=new JSONObject();
    if(null != friends && !friends.isEmpty()){
      jsonObject.put("friends",friends);
      jsonObject.put("errcode",0);
      return jsonObject;
    }else {
      jsonObject.put("errcode",71);
      return jsonObject;
    }
  }
}
