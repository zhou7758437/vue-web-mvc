package vue.web.mvc.commom.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Author     : zh_zhou
 * Create at  : 2018/1/15 11:41
 * Description:
 */
public class AppStringUtil {
    static String charSet = null;

    static {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(i + "");
        }
        for (int i = 'a'; i <= 'z'; i++) {
            sb.append((char) i);
        }
        charSet = sb.toString();
    }

    public static String randomString(int size,int num,String separate) {
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < num; i++) {
            if(i>0){
                sb.append(separate);
            }
            String random=randomString(size);
            sb.append(random);
        }
        return sb.toString();
    }

    public static String randomString(int length) {
        return RandomStringUtils.random(length, charSet);
    }
}
