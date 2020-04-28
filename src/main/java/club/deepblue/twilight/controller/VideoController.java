package club.deepblue.twilight.controller;

import club.deepblue.twilight.pojo.Video;
import club.deepblue.twilight.service.VideoService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class VideoController {
  @Autowired
  VideoService videoService;
  private static final Integer PAGE_SIZE = 20;

  @RequestMapping("/getAllVideo")
  public JSONObject getAllVideo(Integer idx) {
    JSONObject jsonObject = new JSONObject();
    if (!(null ==idx)){
      PageInfo<Video> videoPageInfo = videoService.getAllVideo(idx, PAGE_SIZE);
      if (!(null==videoPageInfo)){
        jsonObject.put("errcode",0);
        jsonObject.put("videoPageInfo",videoPageInfo);
      }else
      {
        jsonObject.put("errcode",101);
      }
    }else {
      jsonObject.put("errcode",10001);
    }
    return jsonObject;
  }

  @RequestMapping("/searchVideo")
  public JSONObject searchVideo(Integer idx,String content){
    JSONObject jsonObject=new JSONObject();
    if (!(null ==idx)&&!(null ==content)&& !StringUtils.isEmpty(content)){
      PageInfo<Video> videoPageInfo =  videoService.searchVideo(idx, PAGE_SIZE,content);
      if (!(null==videoPageInfo)){
        jsonObject.put("errcode",0);
        jsonObject.put("videoPageInfo",videoPageInfo);
      }else
      {
        jsonObject.put("errcode",102);
      }
    }else {
      jsonObject.put("errcode",10001);
    }
    return jsonObject;
  }
}
