package club.deepblue.twilight.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
public class FFMPEGUtils {
  protected static String getComm4Map(Map<String, Object> paramMap) {
    // -i：输入流地址或者文件绝对地址
    StringBuilder comm = new StringBuilder("ffmpeg -re -i ");
    // 是否有必输项：输入地址，输出地址，应用名
    if (paramMap.containsKey("input") && paramMap.containsKey("output")
      && paramMap.containsKey("name")) {
      comm.append(paramMap.get("input")).append(" ");
      // -vcodec 强制使用codec编解码方式。 如果用copy表示原始编解码数据必须被拷贝。
      comm.append("-vcodec ").append(paramMap.containsKey("vcodec")?paramMap.get("vcodec"):"copy").append(" ");
      //-acodec codec 使用codec编解码
      comm.append("-acodec ").append(paramMap.containsKey("acodec")?paramMap.get("acodec"):"copy").append(" ");
      // -f ：转换格式，默认flv
      comm.append(" -f ").append(paramMap.containsKey("fmt") ? paramMap.get("fmt") : "flv").append("  ");
      // -segment_time ：指定切片长度
      comm.append(" -segment_time ").append(paramMap.containsKey("segment_time") ? paramMap.get("segment_time") : "5").append("  ");
      // -s 分辨率 默认是原分辨率
      comm.append("-s ").append(paramMap.containsKey("rs") ? paramMap.get("rs") : "").append(" ");
      // -an 禁用音频
      comm.append("-an ").append(paramMap.containsKey("disableAudio") && ((Boolean) paramMap.get("disableAudio")) ? "-an" : "").append(" ");
      // 输出地址
      comm.append(paramMap.get("output"));
      //发布的应用名
      comm.append(paramMap.get("name"));
      //一个视频源，可以有多个输出，第二个输出为拷贝源视频输出，不改变视频的各项参数
     // comm.append(" ").append(" -vcodec copy -f flv -an rtmp://192.168.30.21/live/test2");
      System.out.println(comm.toString());
      return comm.toString();
    } else {
      throw new RuntimeException("输入流地址不能为空！");
    }

  }

  public static ConcurrentMap<String, Object> push(Map<String, Object> paramMap)
    throws IOException {
    // 从map里面取数据，组装成命令
    String comm = getComm4Map(paramMap);
    ConcurrentMap<String, Object> resultMap = null;
    // 执行命令行
    final Process proc = Runtime.getRuntime().exec(comm);
    System.out.println("执行命令----start commond");
    OutHandler errorGobbler = new OutHandler(proc.getErrorStream(), "Error");
    OutHandler outputGobbler = new OutHandler(proc.getInputStream(), "Info");

    errorGobbler.start();
    outputGobbler.start();
    // 返回参数
    resultMap = new ConcurrentHashMap<String, Object>();
    resultMap.put("info", outputGobbler);
    resultMap.put("error", errorGobbler);
    resultMap.put("process", proc);
    return resultMap;
  }

  public static void removePush(ConcurrentMap concurrentMap)
  {
    if (concurrentMap!=null&&!concurrentMap.isEmpty())
    {
      //关闭两个线程
      ((OutHandler)concurrentMap.get("error")).destroy();
      ((OutHandler)concurrentMap.get("info")).destroy();

      System.out.println("停止命令-----end commond");
      //关闭命令主进程
      ((Process)concurrentMap.get("process")).destroy();
    }
  }
}
