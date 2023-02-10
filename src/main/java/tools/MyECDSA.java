package tools;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.security.*;

/**
 * @Author archer_oneee
 * @Description //TODO None
 * @Date @date 2022-12-24 22:15
 **/
public class MyECDSA {

    static ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
    static KeyPairGenerator  g;
    static Signature  ecdsaSign;
    static Signature  ecdsaVerify;

    static {
        try {
            g = KeyPairGenerator.getInstance("ECDSA", "BC");
            g.initialize(ecSpec, new SecureRandom());
            ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
            ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static KeyPair keyGen() {
        try {

            return g.generateKeyPair();
        }catch (Exception e){
            return null;
        }

    }

    public void keyGenTest(){
        int rounds = 10000;
        String text = new String("a");
        long start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.keyGen();
        }
        long end =System.currentTimeMillis();
        float cost = end-start;
        float ave = cost/rounds;
        System.out.println("ave = " + ave);

        start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.keyGen();
        }
        end =System.currentTimeMillis();
        cost = end-start;
        ave = cost/rounds;
        System.out.println("ave = " + ave);

        start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.keyGen();
        }
        end =System.currentTimeMillis();
        cost = end-start;
        ave = cost/rounds;
        System.out.println("ave = " + ave);
    }


    public static byte[]  sign(String message, PrivateKey sk){
        try {
            ecdsaSign.initSign(sk);
            ecdsaSign.update(message.getBytes("UTF-8"));
            byte[] signature = ecdsaSign.sign();
            return signature;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public KeyPair signTest(){
        KeyPair keyPair = MyECDSA.keyGen();
        PrivateKey sk = keyPair.getPrivate();
        int rounds = 10000;
        String text = new String("aaaaaaaaaaaaaaaaaaaaaaaaa");
        long start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.sign(text, sk);
        }
        long end =System.currentTimeMillis();
        float cost = end-start;
        float ave = cost/rounds;
        System.out.println("ave = " + ave);

        start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.sign(text, sk);
        }
        end =System.currentTimeMillis();
        cost = end-start;
        ave = cost/rounds;
        System.out.println("ave = " + ave);

        start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.sign(text, sk);
        }
        end =System.currentTimeMillis();
        cost = end-start;
        ave = cost/rounds;
        System.out.println("ave = " + ave);

        return keyPair;
    }


    public static boolean verify(PublicKey pk, String m, byte[] signature){
        boolean result = false;
        try {
            ecdsaVerify.initVerify(pk);
            ecdsaVerify.update(m.getBytes("UTF-8"));
            result = ecdsaVerify.verify(signature);
            // System.out.println("result = " + result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void verifyTest(){
        KeyPair keyPair = MyECDSA.keyGen();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey pk = keyPair.getPublic();
        int rounds = 10000;
        String text = new String("aaaaaaaaaaaaaaaaaaaaaaaaa");
        byte[] sig = MyECDSA.sign(text, sk);

        long start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.verify(pk,text,sig);
        }
        long end =System.currentTimeMillis();
        float cost = end-start;
        float ave = cost/rounds;
        System.out.println("ave = " + ave);

         start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.verify(pk,text,sig);
        }
         end =System.currentTimeMillis();
         cost = end-start;
         ave = cost/rounds;
        System.out.println("ave = " + ave);

        start =System.currentTimeMillis();
        for (int i = 0; i < rounds; i++) {
            MyECDSA.verify(pk,text,sig);
        }
        end =System.currentTimeMillis();
        cost = end-start;
        ave = cost/rounds;
        System.out.println("ave = " + ave);
    }

    public static void main(String[] args) {
        MyECDSA myECDSA = new MyECDSA();
        System.out.println("keyGenTest");
        myECDSA.keyGenTest();

        System.out.println("signTest");

        myECDSA.signTest();

        System.out.println("verifyTest");

        myECDSA.verifyTest();

    }


}
