package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] ivBytes = generaIv();
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, generaHash(clau), new IvParameterSpec(ivBytes));
            byte[] xifrat = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));

            byte[] combinat = new byte[MIDA_IV + xifrat.length];
            System.arraycopy(ivBytes, 0, combinat, 0, MIDA_IV);
            System.arraycopy(xifrat, 0, combinat, MIDA_IV, xifrat.length);
            return new TextXifrat(combinat);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] bytes = xifrat.getBytes();
            byte[] ivBytes = Arrays.copyOfRange(bytes, 0, MIDA_IV);
            byte[] dades = Arrays.copyOfRange(bytes, MIDA_IV, bytes.length);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, generaHash(clau), new IvParameterSpec(ivBytes));
            return new String(cipher.doFinal(dades), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    private byte[] generaIv() {
        byte[] ivBytes = new byte[MIDA_IV];
        new SecureRandom().nextBytes(ivBytes);
        return ivBytes;
    }

    private SecretKeySpec generaHash(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = sha.digest(password.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);
    }
}
