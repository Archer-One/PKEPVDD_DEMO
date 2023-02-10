package tools;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2022-12-24 22:05
 **/
public class MySha256 {
    public static String Sha256(String message) {
        Digest bcSha256 = new SHA256Digest();
        byte[] bmessage = message.getBytes();
        // System.out.println(Arrays.toString(bmessage));
        bcSha256.update(bmessage, 0, bmessage.length);
        byte[] value = new byte[bcSha256.getDigestSize()];
        bcSha256.doFinal(value, 0);
        return bytesToHexFun2(value);
    }

   


    public static String bytesToHexFun2(byte[] bytes) {
        char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    public static void main(String[] args) {
        int rounds = 10000;
        String text = new String("a");
        long start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MySha256.Sha256(text);
        }
        long end =System.currentTimeMillis();
        float cost = end-start;
        float ave = cost/rounds;
        System.out.println("ave = " + ave);

         start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MySha256.Sha256(text);
        }
         end =System.currentTimeMillis();
         cost = end-start;
         ave = cost/rounds;
        System.out.println("ave = " + ave);

        start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MySha256.Sha256(text);
        }
        end =System.currentTimeMillis();
        cost = end-start;
        ave = cost/rounds;
        System.out.println("ave = " + ave);
    }
}
