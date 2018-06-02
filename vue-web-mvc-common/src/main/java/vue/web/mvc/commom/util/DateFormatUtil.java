package vue.web.mvc.commom.util;

import java.util.Date; /**
 * Author     : zh_zhou
 * Create at  : 2017/12/15 16:54
 * Description:
 */
public class DateFormatUtil {
    public static String format(Date time, String format) {
        if(time==null){
            return null;
        }
        return org.apache.commons.lang3.time.DateFormatUtils.format(time,format);
    }
}
