package club.deepblue.twilight.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.io.File;
import java.io.InputStream;

public class TwFileUtil extends FileUtil {
  private final static String RELATIVELY_PATH = System.getProperty("user.dir") +  File.separatorChar+"src"+ File.separatorChar+"main"+ File.separatorChar+"resources"+ File.separatorChar+"static"+ File.separatorChar+"data"+ File.separatorChar;


  public static String saveFileMD5(InputStream inputStream, String savePath, String extName){
    String md5File = DigestUtil.md5Hex(inputStream);
    if(fileSave(savePath,md5File,inputStream)) return md5File+extName;
    return null;
  }

  public static boolean fileSave(String savePath, String name, InputStream inputStream){
    File localFile = new File(RELATIVELY_PATH + savePath, name);
    if (!exist(localFile)) {
      //创建一个新文件
      File attachFile = touch(RELATIVELY_PATH + savePath, name);
      //将文件流写入文件中
     writeFromStream(inputStream, attachFile);
    }
    return true;
  }
}
