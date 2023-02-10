import entities.User;
import entities.Court;
import it.unisa.dia.gas.jpbc.Element;
import tools.dkt.DKT;
import tools.pairing.MyPairing;

public class DKTMain {
    public static void main(String[] args) {


        Court court = new Court(DKT.KeyGen());

        User user = new User(DKT.KeyGen());

        Element m = MyPairing.getRandomFromGT();
        Element[] c = DKT.Enc(court.keyPair.publicKey,m);

        Element R = DKT.RGen(user.keyPair.privateKey,c);

        assert (DKT.RVrf(user.keyPair.publicKey, c,R));

        Element A = DKT.AGen(court.keyPair.privateKey,c,R);
        assert (DKT.AVrf(court.keyPair.publicKey,R,A));

        Element result = DKT.DecO(court.keyPair.privateKey,c,m);
        result = DKT.DecR(user.keyPair.privateKey,c,A,m);

    }
}
