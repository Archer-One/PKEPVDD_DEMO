import entities.Court;
import entities.User;
import it.unisa.dia.gas.jpbc.Element;
import tools.dkt.DKT;
import tools.pairing.MyPairing;

public class TestMain1 {

    int []num;
    float[] keyGen;
    float[] Enc;
    float[] RGen;
    float[] RVrf;
    float[] AGen;
    float[] AVrf;
    float[] DecO;
    float[] DecR;

    public TestMain1(int[] num) {
        this.num = num;
        keyGen = new float[num.length];
        Enc = new float[num.length];
        RGen = new float[num.length];
        RVrf = new float[num.length];
        AGen = new float[num.length];
        AVrf = new float[num.length];
        DecO = new float[num.length];
        DecR = new float[num.length];

    }

    public void startTest(){
        for (int i = 0; i < num.length; i++) {
            int nNum = num[i];
            this.test(nNum,i);
        }
    }

    public void test(int nNum, int index) {
        Court[] court = new Court[nNum];
        User[] user = new User[nNum];
        Element[] m = new Element[nNum];
        long start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            m[i] = MyPairing.getRandomFromGT();
        }
        long end = System.currentTimeMillis();
        float cost = new Float(end - start) / nNum;

        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            court[i] = new Court(DKT.KeyGen());
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        keyGen[index] = cost;
        //System.out.println("court keyGen cost = " + cost);

        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            user[i] = new User(DKT.KeyGen());

        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);

        System.out.println("user keyGen cost = " + cost);


        Element[][] c = new Element[nNum][2];
        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            c[i] = DKT.Enc(court[i].keyPair.publicKey, m[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        Enc[index] = cost;
        //System.out.println("Enc cost = " + cost);

        Element[] R = new Element[nNum];

        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            R[i] = DKT.RGen(user[i].keyPair.privateKey, c[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        RGen[index] = cost;
        //System.out.println("RGen cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            DKT.RVrf(user[i].keyPair.publicKey, c[i], R[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        RVrf[index] = cost;
        //System.out.println("RVrf cost = " + cost);


        Element[] A = new Element[nNum];
        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            A[i] = DKT.AGen(court[i].keyPair.privateKey, c[i], R[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        AGen[index] = cost;
        //System.out.println("AGen cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            DKT.AVrf(court[i].keyPair.publicKey, R[i], A[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        AVrf[index] = cost;
        //System.out.println("AVrf cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            DKT.DecO(court[i].keyPair.privateKey, c[i], m[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        DecO[index] = cost;
        //System.out.println("DecO cost = " + cost);


        start = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++) {
            DKT.DecR(user[i].keyPair.privateKey, c[i], A[i], m[i]);
        }
        end = System.currentTimeMillis();
        cost = new Float(end - start) / new Float(nNum);
        DecR[index] = cost;
        //System.out.println("DecR cost = " + cost);
    }

    public static void main(String[] args) {
        int[] num = new int[]{100,500,1000};
        TestMain1 testMain = new TestMain1(num);
        testMain.startTest();
        int a = 1;

    }
}
