package vue.web.mvc.service.util;

import org.bouncycastle.util.encoders.Base64;

import java.nio.charset.Charset;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/31 14:54
 * Description:
 */
public class EmojiUtil {

    private static final Charset UTF8 = Charset.forName("utf8");

    public static String convert(String content){
        byte[] dataByte = Base64.encode(content.getBytes(UTF8));
        return new String(dataByte,UTF8);
    }
    public static String parse(String encrypt){
        byte[] dataByte = Base64.decode(encrypt.getBytes(UTF8));
        return new String(dataByte,UTF8);
    }
}
