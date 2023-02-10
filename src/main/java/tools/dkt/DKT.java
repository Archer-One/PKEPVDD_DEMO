package tools.dkt;

import it.unisa.dia.gas.jpbc.Element;
import tools.MyKeyPair;
import tools.pairing.MyPairing;

public class DKT {


    public static MyKeyPair KeyGen(){
        Element sk = MyPairing.getRandomFromZp();
        Element pk = MyPairing.g.mulZn(sk).getImmutable();
        MyKeyPair pair = new MyKeyPair(sk,pk);
        return pair;
    }

    public static Element[] Enc(Element pk, Element m){
        Element r = MyPairing.getRandomFromZp();
        Element c1 = MyPairing.G1MulR(r);

        Element hc1 = MyPairing.hashFromBytesToG1(c1.toBytes());
        Element e_y_hc1= MyPairing.pair(pk,hc1);
        Element c2 = e_y_hc1.mulZn(r).add(m);
        Element[] c = new Element[2];
        c[0] = c1;
        c[1] = c2;
        return c;
    }

    public static Element RGen(Element sk_r,Element[] c){
        Element c1 = c[0];
        Element R = MyPairing.hashFromBytesToG1(c1.toBytes()).mulZn(sk_r).getImmutable();
        return R;
    }

    public static boolean RVrf(Element y_r, Element[] c, Element R){
        Element c1 = c[0];
        Element h_c1 = MyPairing.hashFromBytesToG1(c1.toBytes());
        Element e_l = MyPairing.pair(y_r,h_c1);
        Element e_r = MyPairing.pair(MyPairing.g,R);
        boolean result = e_l.equals(e_r);

        return result;
    }


    public static Element AGen(Element x_s, Element[] c, Element R){
        Element A = R.mulZn(x_s).getImmutable();
        return A;
    }

    public static boolean AVrf(Element y_s, Element R, Element A){
        Element e_l = MyPairing.pair(y_s,R);
        Element e_r = MyPairing.pair(MyPairing.g,A);
        boolean result = e_l.equals(e_r);
        assert (result);
        return result;
    }

    public static Element DecO(Element x_s, Element[] c, Element m){
        Element c1 = c[0];
        Element c2 = c[1];
        Element h_c1 = MyPairing.hashFromBytesToG1(c1.toBytes());
        Element h_x = h_c1.mulZn(x_s);

        Element e_b = MyPairing.pair(c1,h_x);
        Element m_ = c2.div(e_b);
        assert(m.equals(m_));
        return null;

    }

    public static Element DecR(Element x_r, Element[] c, Element A, Element m){
        Element c1 = c[0];
        Element c2 = c[1];
        Element A_ = A.mulZn(MyPairing.getOneFromZp().div(x_r));
        Element e_b = MyPairing.pair(c1,A_);
        Element m_ = c2.div(e_b);
        assert(m.equals(m_));
        return null;
    }

    public static Element Sign(Element x_o, Element m){
        Element h_m = MyPairing.hashFromBytesToG1(m.toBytes());

        Element sig = h_m.mulZn(x_o).getImmutable();
        return sig;
    }

    public static Element Vrfy(Element y_o, Element m, Element sig){

        Element h_m = MyPairing.hashFromBytesToG1(m.toBytes());
        Element e_l = MyPairing.pair(y_o,h_m);
        Element e_r = MyPairing.pair(MyPairing.g,sig);
        boolean result = e_l.equals(e_r);
        assert(result);
        return null;
    }




    public static void main(String[] args) {

    }
}
