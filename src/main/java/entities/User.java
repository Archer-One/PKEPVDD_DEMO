package entities;

import tools.MyKeyPair;

public class User {
    public MyKeyPair keyPair;

    public User(MyKeyPair keyPair) {
        this.keyPair = keyPair;
    }
}
