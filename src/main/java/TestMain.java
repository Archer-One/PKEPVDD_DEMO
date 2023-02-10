import entities.Court;
import entities.User;
import it.unisa.dia.gas.jpbc.Element;
import tools.dkt.DKT;
import tools.pairing.MyPairing;

public class TestMain {




    public static void main(String[] args) {

        int num = 100;
        Court []court = new Court[num];
        User []user = new User[num];
        Element[] m = new Element[num];
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            m[i] = MyPairing.getRandomFromGT();
        }
        long end = System.currentTimeMillis();
        float cost = new Float(end - start) / num;
        System.out.println("get m cost = " + cost);

        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            court[i] = new Court(DKT.KeyGen());

        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("court keyGen cost = " + cost);

        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            user[i] = new User(DKT.KeyGen());
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("user keyGen cost = " + cost);


        Element[][] c = new Element[num][2];
        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            c[i] = DKT.Enc(court[i].keyPair.publicKey, m[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("Enc cost = " + cost);

        Element[] R = new Element[num];

        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            R[i] = DKT.RGen(user[i].keyPair.privateKey, c[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("RGen cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            boolean result =DKT.RVrf(user[i].keyPair.publicKey, c[i], R[i]);
            assert (result);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("RVrf cost = " + cost);


        Element[] A = new Element[num];
        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            A[i] = DKT.AGen(court[i].keyPair.privateKey, c[i], R[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("AGen cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
             boolean result = DKT.AVrf(court[i].keyPair.publicKey, R[i], A[i]);
            assert (result);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("AVrf cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            DKT.DecO(court[i].keyPair.privateKey, c[i], m[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("DecO cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            DKT.DecR(user[i].keyPair.privateKey, c[i], A[i], m[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("DecR cost = " + cost);


        for (int i = 0; i < num; i++) {
            m[i] = MyPairing.getRandomFromG1();
        }

        Element[] sig = new Element[num];
        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            sig[i] = DKT.Sign(user[i].keyPair.privateKey,m[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("Sign cost = " + cost);

        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            DKT.Vrfy(user[i].keyPair.publicKey,m[i],sig[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / num;
        System.out.println("Vrfy cost = " + cost);
    }
}
