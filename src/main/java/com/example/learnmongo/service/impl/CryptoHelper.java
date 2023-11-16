package com.example.learnmongo.service.impl;

import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

public class CryptoHelper {

    public static String getUncompressedRawPubKey(ECPublicKey ecPublicKey) {
        byte[] encodedKeyX = ecPublicKey.getQ().getAffineXCoord().getEncoded();
        byte[] encodedKeyY = ecPublicKey.getQ().getAffineYCoord().getEncoded();
        return "04".concat(Hex.toHexString(encodedKeyX)).concat(Hex.toHexString(encodedKeyY));
    }

    public ECPublicKey getECKeyFromUncompressedRawKey(String uncompressedRawKey) throws NoSuchAlgorithmException, NoSuchProviderException {
        ECNamedCurveParameterSpec params = ECNamedCurveTable.getParameterSpec("secp256r1");
        ECCurve ecCurve = params.getCurve();
        EllipticCurve ellipticCurve = EC5Util.convertCurve(ecCurve, params.getSeed());
        ECPoint ecPoint = ECPointUtil.decodePoint(ellipticCurve, Hex.decode(uncompressedRawKey));
        KeyFactory fact = KeyFactory.getInstance("EC", "BC");
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {

        Security.addProvider(new BouncyCastleProvider());

        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        KeyPairGenerator keyPairGenerator = (KeyPairGenerator) KeyPairGenerator.getInstance("ECIES", BouncyCastleProvider.PROVIDER_NAME);
        keyPairGenerator.initialize(ecSpec);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();
        ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();

        String uncompressedPubKey = getUncompressedRawPubKey(ecPublicKey);


    }

}
