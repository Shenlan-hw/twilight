package club.deepblue.twilight.controller;

import club.deepblue.twilight.pojo.Cinema;
import club.deepblue.twilight.pojo.Member;
import club.deepblue.twilight.pojo.PlayList;
import club.deepblue.twilight.service.CinemaService;
import club.deepblue.twilight.service.MemberService;
import club.deepblue.twilight.service.PlayListService;
import club.deepblue.twilight.utils.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private CinemaService cinemaService;
  @Autowired
  private PlayListService playListService;
  @Autowired
  private MemberService memberService;

  private static final Integer PAGE_SIZE = 20;


  @RequestMapping(value = "/createCinema", method = RequestMethod.POST)
  public JSONObject createCinema(HttpServletRequest httpServletRequest, @RequestBody String str) {
    JSONObject jsonObject = JSONObject.parseObject(str);
    JSONObject cinemaJSONObject = (JSONObject) jsonObject.get("cinema");
    List<Integer> vlist = (List<Integer>) jsonObject.get("vlist");

    if (!(null == cinemaJSONObject) && !(null == vlist || vlist.isEmpty())) {
      String session = RequestUtil.getSession(httpServletRequest);
      ValueOperations valueOperations = redisTemplate.opsForValue();
      jsonObject = (JSONObject) valueOperations.get(session);
      JSONObject userInfoJSONObject = (JSONObject) jsonObject.get("userInfo");
      Integer u_id = userInfoJSONObject.getInteger("u_id");
      Cinema cinema = new Cinema();
      cinema.setU_id(u_id);
      cinema.setCi_notice(cinemaJSONObject.getString("notice"));
      cinema.setCi_type(cinemaJSONObject.getByte("type"));
      cinema.setCi_number(cinemaJSONObject.getByte("number"));
      Date date = new Date();
      cinema.setCi_created_time(date);
      Integer num = cinemaService.setCinemaByObject(cinema);
      if (!(null == num) && (num > 0) && (!(null == cinema.getU_id()))) {
        int count = 0;
        for (Integer v : vlist) {
          PlayList playList = new PlayList();
          playList.setCi_id(cinema.getCi_id());
          playList.setV_id(v);
          int r = playListService.setPlayListByObject(playList);
          if (r > 0) {
            count++;
          }
        }
        if (count == 0) {
          jsonObject.clear();
          jsonObject.put("errcode", 112);
          cinemaService.destroyCinemaByCiID(cinema.getCi_id());
        } else {
          jsonObject.clear();
          jsonObject.put("errcode", 0);
          jsonObject.put("ci_id", cinema.getCi_id());
        }

      } else {
        jsonObject.clear();
        jsonObject.put("errcode", 111);
      }
    } else {
      jsonObject.clear();
      jsonObject.put("errcode", 10001);
    }
    return jsonObject;
  }

  @RequestMapping("/getCimenaMemberList")
  public JSONObject getCimenaMemberList(Integer idx, Integer ci_id) {

    JSONObject jsonObject = new JSONObject();
    if (!(null == ci_id || null == idx)) {
      PageInfo<Member> memberPageInfo = memberService.getMemberByCiID(idx, PAGE_SIZE, ci_id);
      if (!(null==memberPageInfo)){
        jsonObject.put("errcode",0);
        jsonObject.put("memberPageInfo",memberPageInfo);
      }else {
        jsonObject.put("errcode",121);
      }
    } else {
      jsonObject.put("errcode", 10001);
    }
    return jsonObject;
  }
}
