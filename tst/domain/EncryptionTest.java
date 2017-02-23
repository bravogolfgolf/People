package domain;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertEquals;

public class EncryptionTest {

    @Test
    public void encryptAndDecryptCharArray() throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String password = "Password";
        char[] chars = password.toCharArray();

        String encrypted = Encryption.encrypt(chars);
        String expected = Encryption.decrypt(encrypted);

        assertEquals(expected, password);
    }
}
