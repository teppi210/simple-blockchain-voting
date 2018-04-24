package com.chernikovmaksym.voting.util;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public final class BlockchainUtils {

    private BlockchainUtils() {
    }

    public static Credentials buildCredentials(String privateKey) {
        return Credentials.create(Numeric.toHexStringNoPrefix(new BigInteger(privateKey)));
    }

    public static Credentials generateCredentials() {
        try {
            return Credentials.create(Keys.createEcKeyPair());
        } catch (Exception e) {
            throw new RuntimeException("Error generating private key: ", e);
        }
    }
}
