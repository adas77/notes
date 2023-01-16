package pl.backend.Service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class HashNotesService {
        private final String algorithm = "AES";
        private final String salt = "Pxt)DLaG";

        public String encrypt(String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
                        InvalidKeyException,
                        BadPaddingException, IllegalBlockSizeException {

                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] cipherText = cipher.doFinal(input.getBytes());
                return Base64.getEncoder()
                                .encodeToString(cipherText);
        }

        public String decrypt(String cipherText, SecretKey key)
                        throws NoSuchPaddingException, NoSuchAlgorithmException,
                        InvalidAlgorithmParameterException, InvalidKeyException,
                        BadPaddingException, IllegalBlockSizeException {

                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] plainText = cipher.doFinal(Base64.getDecoder()
                                .decode(cipherText));
                return new String(plainText);
        }

        public SecretKey getKeyFromPassword(String password)
                        throws NoSuchAlgorithmException, InvalidKeySpecException {

                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
                SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                                .getEncoded(), algorithm);
                return secret;
        }
}