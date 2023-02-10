package tools;

import it.unisa.dia.gas.jpbc.Element;

import java.math.BigInteger;

public class MyKeyPair {
    public Element privateKey;
    public Element publicKey;
    public MyKeyPair(Element privateKey,Element publicKey){
        this.privateKey = privateKey;
        this.publicKey  = publicKey;
    }


}
