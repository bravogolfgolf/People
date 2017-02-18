package domain;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

class Encryption {

    private static final char[] PASSWORD = "Unauthorized-Personal-Are-Not-Unauthorized".toCharArray();
    private static final int ITERATION_COUNT = 1000;
    private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBEWithMD5AndDES";


    static String decrypt(String encrypted) throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        String[] strings = encrypted.split(":");
        byte[] salt = Base64.getDecoder().decode(strings[1]);
        int iterations = Integer.parseInt(strings[0]);
        String password = strings[2];

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(salt, iterations));
        return new String(pbeCipher.doFinal(Base64.getDecoder().decode(password)));

    }

    static String encrypt(char[] property) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        byte[] bytes = new byte[property.length];

        for (int i = 0; i < property.length; i++)
            bytes[i] = (byte) property[i];

        String encrypted = encrypt(bytes);

        Arrays.fill(property, '0');
        System.gc();

        return encrypted;
    }

    private static String encrypt(byte[] bytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));

        byte[] salt = new byte[8];
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.nextBytes(salt);

        Cipher pbeCipher = Cipher.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(salt, ITERATION_COUNT));

        String password = Base64.getEncoder().encodeToString(pbeCipher.doFinal(bytes));

        return String.format("%d:%s:%s", ITERATION_COUNT, Base64.getEncoder().encodeToString(salt), password);
    }
}
