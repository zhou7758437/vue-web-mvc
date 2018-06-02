package vue.web.mvc.commom.util;

/**
 * Author     : zh_zhou
 * Create at  : 2018/1/5 16:15
 * Description:
 */
public class ApiUtil {

    public static final String SEPARATOR = "/";

    public static String build(String host, String path) {
        StringBuffer sb=new StringBuffer();
        sb.append(host);
        if(!host.endsWith(SEPARATOR)){
            sb.append(SEPARATOR);
        }
        if(path.startsWith(SEPARATOR)){
            path=path.substring(1);
        }
        sb.append(path);
        return sb.toString();
    }
}
