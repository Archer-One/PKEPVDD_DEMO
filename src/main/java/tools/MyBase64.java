package tools;

import org.bouncycastle.util.encoders.Base64;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2022-12-25 08:48
 **/
public class MyBase64 {
    public static byte[] encode(String m){
        return Base64.encode(m.getBytes());
    }


    public static String decode(byte [] data){
        return new String(Base64.decode(data));
    }
}
