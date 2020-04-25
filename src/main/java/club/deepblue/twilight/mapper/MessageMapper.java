package club.deepblue.twilight.mapper;

import club.deepblue.twilight.pojo.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer me_id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer me_id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

  List<Message> selectByUIDAndReID(Integer u_id, Integer re_u_id);
}
