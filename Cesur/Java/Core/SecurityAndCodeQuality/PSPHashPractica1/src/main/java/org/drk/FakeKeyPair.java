package org.drk;

import java.util.UUID;

public class FakeKeyPair {
    private final String publicKey;
    private final String privateKey;

    public FakeKeyPair() {
        String uuid = UUID.randomUUID().toString();
        this.privateKey = "PRIV-" + uuid;
        this.publicKey = "PUB-" + uuid;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
