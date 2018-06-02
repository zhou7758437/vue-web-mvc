package vue.web.mvc.service.util;

import com.alibaba.fastjson.JSON;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 14:36
 * Description:
 */
public class EhCacheFastJsonSerializer<T> implements Serializer<T> {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    Class<T> targetClass;
    boolean isList;

    public EhCacheFastJsonSerializer(Class<T> tClass, boolean isList) {
        targetClass = tClass;
        this.isList = isList;
    }


    @Override
    public ByteBuffer serialize(T object) throws SerializerException {
        byte[] bytes = null;
        if (isList) {
            String json = JSON.toJSONString(object);
            bytes = json.getBytes(UTF_8);
        } else {
            bytes = JSON.toJSONBytes(object);
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes).flip();
        return byteBuffer;
    }

    @Override
    public T read(ByteBuffer binary) throws ClassNotFoundException, SerializerException {
        byte[] bytes = new byte[binary.remaining()];
        binary.get(bytes);
        T object = null;
        if (isList) {
            String json = new String(bytes, UTF_8);
            object = (T) JSON.parseArray(json, targetClass);
        } else {
            object = JSON.parseObject(bytes, targetClass);
        }

        return object;
    }

    @Override
    public boolean equals(T object, ByteBuffer binary) throws ClassNotFoundException, SerializerException {
        return object.equals(read(binary));
    }
}

