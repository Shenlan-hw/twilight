package club.deepblue.twilight.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
  public static String getSession(HttpServletRequest request){
    return request.getHeader("session");
  }
}
