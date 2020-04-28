package club.deepblue.twilight.controller;


import club.deepblue.twilight.pojo.User;
import club.deepblue.twilight.service.UserService;
import club.deepblue.twilight.utils.RequestUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
  private final static String APPID = "wx10c7f025afe5f14a";
  private final static String SECRET = "70923c2b4e7a5d1563704fe0e0e9b064";
  private final static String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={JSCODE}&grant_type=authorization_code";
  private final static long TIMEOUT = 30;

  @Value("${tw.file-path-prefix}")
  public String uploadedFilePathPrefix;

  private RestTemplate restTemplate;


  @Autowired
  private UserService userService;

  @Autowired
  private RedisTemplate redisTemplate;

  @RequestMapping("/login")
  public JSONObject login(String code) {
    return getSession(code);
  }

  @RequestMapping("/getUserInfo")
  public JSONObject getUserInfo(HttpServletRequest httpServletRequest) {
    JSONObject jsonObject = new JSONObject();
    ValueOperations valueOperations = redisTemplate.opsForValue();
    String session = RequestUtil.getSession(httpServletRequest);
    jsonObject = (JSONObject) valueOperations.get(session);
    if (!jsonObject.containsKey("userInfo")) {
      jsonObject.clear();
      jsonObject.put("errcode", 41);
      return jsonObject;
    } else {
      JSONObject userJSONObject = jsonObject.getJSONObject("userInfo");
      userJSONObject.remove("u_openid");
      jsonObject.clear();
      jsonObject.put("errcode", 0);
      jsonObject.put("userInfo", userJSONObject);
      return jsonObject;
    }
  }

  @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
  public JSONObject editUserInfo(HttpServletRequest httpServletRequest, User userInfo) {
    JSONObject jsonObject = null;
    String session = RequestUtil.getSession(httpServletRequest);
    ValueOperations valueOperations = redisTemplate.opsForValue();
    JSONObject userJSONObject = (JSONObject) valueOperations.get(session);
    JSONObject userInfoJSONObject = userJSONObject.getJSONObject("userInfo");
    User user = userInfoJSONObject.toJavaObject(User.class);
    User user_new = userInfo;
    user_new.setU_id(user.getU_id());
    int editSum = userService.editUserByObject(user_new);
    if (editSum > 0) {
      jsonObject = new JSONObject();
      jsonObject.put("errcode", 0);
    } else {
      jsonObject = new JSONObject();
      jsonObject.put("errcode", 61);
    }
    return jsonObject;
  }

  @RequestMapping("/editHeadpin")
  public JSONObject editHeadpin(HttpServletRequest httpServletRequest, String url) {
    JSONObject jsonObject = null;
    String session = RequestUtil.getSession(httpServletRequest);
    ValueOperations valueOperations = redisTemplate.opsForValue();
    User user = (User) valueOperations.get("user");
    user.setU_avatar_url(url);
    int editSum = userService.editUserByObject(user);
    if (editSum > 0) {
      jsonObject = new JSONObject();
      jsonObject.put("errcode", 0);
    } else {
      jsonObject = new JSONObject();
      jsonObject.put("errcode", 641);
    }
    return jsonObject;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public JSONObject register(String code, User userInfo) {
    JSONObject jsonObject = getRawData(code);
    int errcode;
    if (!jsonObject.containsKey("errcode") || (errcode = jsonObject.getInteger("errcode")) == 0) {
      String openID = jsonObject.getString("openid");
      User user_new = userService.getUserByOpenID(openID);
      if (!(null == user_new)) {
        jsonObject.clear();
        jsonObject.put("errcode", 21);
        return jsonObject;
      }
      Date date = new Date();
      user_new = userInfo;
      user_new.setU_openid(openID);
      String avatar_url = getFile(userInfo.getU_avatar_url());
      if (avatar_url != null) {
        user_new.setU_avatar_url(avatar_url);
      } else {
        user_new.setU_avatar_url("default");
      }
      user_new.setU_created_time(date);
      user_new.setU_power((byte) 7);
      int insertSum = userService.setUserByObject(user_new);

      if (insertSum > 0) {
        jsonObject = getSession(jsonObject);
        return jsonObject;
      } else {
        jsonObject.clear();
        jsonObject.put("errcode", 22);
        return jsonObject;
      }
    } else {
      String errmsg = jsonObject.getString("errmsg");
      jsonObject.clear();
      jsonObject.put("errcode", errcode);
      jsonObject.put("errmsg", errmsg);
      return jsonObject;
    }
  }

  @RequestMapping("/searchUsers")
  public JSONObject searchUsers(String content) {
    JSONObject jsonObject = new JSONObject();
    if (!StringUtils.isEmpty(content)) {
      List<User> users = userService.getUsersByLikeContent(content);
      if (!(null == users) && !users.isEmpty()) {
        jsonObject.put("users", users);
        jsonObject.put("errcode", 0);
      } else {
        jsonObject.put("errcode", 81);
      }
    } else {
      jsonObject.put("errcode", 82);
    }
    return jsonObject;
  }

  @RequestMapping("/checkSignature")
  public JSONObject checkSignature(HttpServletRequest httpServletRequest, String rawData, String signature) {
    JSONObject jsonObject;
    if (StringUtils.isEmpty(rawData)) {
      jsonObject = new JSONObject();
      jsonObject.put("code", 31);
      return jsonObject;
    } else {
      jsonObject = new JSONObject();
      String session = RequestUtil.getSession(httpServletRequest);
      //String signature2= DigestUtils.sha1Hex(rawData+session_key);

      return jsonObject;
    }
  }

  @RequestMapping("/requestSession")
  public JSONObject requestSession(String JSCODE) {
    return getSession(JSCODE);
  }

  @RequestMapping("/getUserInfoByUID")
  public JSONObject getUserInfoByUID(Integer uid) {
    JSONObject jsonObject = new JSONObject();
    if (!(null ==uid)) {
      User user=userService.getUserByUID(uid);
      if (!(null ==user)){
        jsonObject.put("userInfo",user);
        jsonObject.put("errcode",0);
      }else {
        jsonObject.put("errcode",43);
      }
    } else {
      jsonObject.put("errcode",42);
    }
    return jsonObject;
  }

  public JSONObject getSession(String JSCODE) {
    JSONObject jsonObject = getRawData(JSCODE);
    return getSession(jsonObject);
  }

  public JSONObject getSession(JSONObject jsonObject) {
    int errcode;
    if (!jsonObject.containsKey("errcode") || (errcode = jsonObject.getInteger("errcode")) == 0) {
      String openID = jsonObject.getString("openid");
      User user = userService.getUserByOpenID(openID);
      if (null == user) {
        jsonObject.clear();
        jsonObject.put("errcode", 11);
        return jsonObject;
      } else {
        String session = createSession(jsonObject);
        JSONObject userJSONObject = (JSONObject) JSONObject.toJSON(user);
        jsonObject.put("userInfo", userJSONObject);

        ValueOperations<String, JSONObject> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(session, jsonObject);
        redisTemplate.expire(session, TIMEOUT, TimeUnit.MINUTES);
        jsonObject.clear();
        jsonObject.put("errcode", 0);
        jsonObject.put("session", session);
        return jsonObject;
      }
    } else {
      String errmsg = jsonObject.getString("errmsg");
      jsonObject.clear();
      jsonObject.put("errcode", errcode);
      jsonObject.put("errmsg", errmsg);
      return jsonObject;
    }
  }

  private String createSession(JSONObject jsonObject) {
    SecureRandom secureRandom = new SecureRandom();
    String random = String.valueOf(secureRandom.nextInt() * secureRandom.nextInt() * secureRandom.nextInt());
    String session = DigestUtils.sha1Hex(jsonObject.toJSONString() + random);
    return session;
  }

  private JSONObject getRawData(String JSCODE) {
    restTemplate = new RestTemplate();
    Map<String, String> data = new HashMap<String, String>();
    data.put("APPID", APPID);
    data.put("SECRET", SECRET);
    data.put("JSCODE", JSCODE);
    String responseData = restTemplate.getForObject(requestUrl, String.class, data);
    JSONObject jsonObject = JSONObject.parseObject(responseData);
    return jsonObject;
  }


  public String getFile(String url) {

    restTemplate = new RestTemplate();
    ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
    MediaType mediaType = rsp.getHeaders().getContentType();
    String md5File = DigestUtil.md5Hex(rsp.getBody());
    String fileName = md5File + "." + mediaType.getSubtype();
    try {
      Files.write(Paths.get(uploadedFilePathPrefix + "images/" + fileName), rsp.getBody());
      return fileName;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
