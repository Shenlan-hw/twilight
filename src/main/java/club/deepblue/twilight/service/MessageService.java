package club.deepblue.twilight.service;

import club.deepblue.twilight.pojo.Message;
import com.github.pagehelper.PageInfo;

public interface MessageService {
  public PageInfo<Message> getMessageListByID(Integer pageIndex, Integer pageSize, Integer u_id, Integer re_u_id);
}
