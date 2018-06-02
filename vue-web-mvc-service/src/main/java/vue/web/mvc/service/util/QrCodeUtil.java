package vue.web.mvc.service.util;

import net.glxn.qrgen.core.scheme.VCard;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/14 14:04
 * Description:
 */
public class QrCodeUtil {
    public static String getImgBase64() throws IOException {
        VCard johnDoe = new VCard ("土豆")
                .setEmail("zh79325@163.com")
                .setAddress("John Doe Street 1, 5678 Doestown")
                .setTitle("随便起的")
                .setCompany("Ctrip Inc.")
                .setPhoneNumber("暂时保密")
                .setWebsite("wydy.shop");
        ByteArrayOutputStream os= QRCode.from(johnDoe)
        .withCharset("utf-8")
                .withSize(300,150)
                .withColor(0xFFFF0000, 0xFFFFFFFF)
        .to(net.glxn.qrgen.core.image.ImageType.PNG).stream();
        byte[] bytes= os.toByteArray();
        return Base64Utils.encodeToString(bytes);
    }


}
