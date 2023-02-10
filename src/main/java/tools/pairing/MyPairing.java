package tools.pairing;
import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MyPairing {

    public static Pairing pairing = PairingFactory.getPairing("src/main/java/tools/pairing/a.properties");
    /* Return Zr */
    public static Field  Zr = pairing.getZr();

    /* Return G1 */
    public static Field  G1 = pairing.getG1();

    /* Return the generator of G1 */
    public static Element  g = pairing.getG1().newRandomElement();

    /* Return G2 */
    public static Field  G2 = pairing.getG2();

    /* Return GT */
    public static Field  GT = pairing.getGT();

    //G1中获取随机元素，获取1，获取0
    public static Element getRandomFromG1() {
        return pairing.getG1().newRandomElement().getImmutable();
    }
    public static Element getOneFromG1() {
        return pairing.getG1().newOneElement().getImmutable();
    }
    public static Element getZeroFromG1() {
        return pairing.getG1().newZeroElement().getImmutable();
    }

    //GT中获取随机元素，获取1，获取0
    public static Element getRandomFromGT() {
        return pairing.getGT().newRandomElement().getImmutable();
    }

    //Zr中获取随机元素，获取1，获取0
    public static Element getRandomFromZp() {
        return pairing.getZr().newRandomElement().getImmutable();
    }


    public static Element getOneFromZp() {
        return pairing.getZr().newOneElement().getImmutable();
    }
    public static Element getZeroFromZp() {
        return pairing.getZr().newZeroElement().getImmutable();
    }




    public static Element pair(Element in1, Element in2){
        Element e_in1_in2 =  MyPairing.pairing.pairing(in1,in2).getImmutable();
        return e_in1_in2;
    }

    public static Element hashFromStringToZp(String str) {
        return pairing.getZr().newElement().setFromHash(str.getBytes(), 0, str.length()).getImmutable();
    }
    public static Element hashFromBytesToZp( byte[] bytes) {
        return pairing.getZr().newElement().setFromHash(bytes, 0, bytes.length).getImmutable();
    }


    public static Element hashFromStringToG1(String str) {
        return pairing.getG1().newElement().setFromHash(str.getBytes(), 0, str.length()).getImmutable();
    }

    public static Element hashFromBytesToG1(byte[] bytes) {
        return pairing.getG1().newElement().setFromHash(bytes, 0, bytes.length).getImmutable();
    }


    public static Element hashFromG1ToZp( Element g1_element) {
        // h(y) : G1 -> Zp
        byte[] g1_bytes = g1_element.getImmutable().toCanonicalRepresentation();
        byte[] zp_bytes = g1_bytes;
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA-512");
            zp_bytes = hasher.digest(g1_bytes);   //先把G1元素hash成512bits
        } catch (Exception e) {
            e.printStackTrace();
        }
        //再把hash后的bits映射到Zp
        Element hash_result = pairing.getZr().newElementFromHash(zp_bytes, 0, zp_bytes.length).getImmutable();
        return hash_result;
    }

    public static Element transformFromGtToZp(Element pairing_result){
        BigInteger pairing_params = pairing_result.toBigInteger();
        return pairing.getZr().newElement().set(pairing_params);
    }

    public static Element G1MulR(Element r){
        Element g_r = g.mulZn(r).getImmutable();
        return g_r;
    }



}
