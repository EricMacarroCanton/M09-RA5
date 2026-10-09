import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "iticbcn";

    
     //Vector d'Inicialització (IV) aleatori utilitzant SecureRandom.
     
    private static byte[] generaIv() {
        byte[] ivBytes = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        return ivBytes;
    }

    
      //SecretKeySpec a partir del password utilitzant SHA-256.
     
    private static SecretKeySpec generaHash(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = password.getBytes(StandardCharsets.UTF_8);
        keyBytes = sha.digest(keyBytes);
        return new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);
    }

    
      //Extreu l'IV del array de bytes xifrat (primers 16 bytes).
    
    private static byte[] extreureIv(byte[] bMsgXifrat) {
        return Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
    }

    
     //Extreu la part del missatge xifrat (després del IV).
     
    private static byte[] getBytesXifrats(byte[] bMsgXifrat) {
        return Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);
    }

    
      //Mètode pXifraAES.
     
    public static byte[] xifraAES(String msg, String password) throws Exception {
        byte[] inputBytes = msg.getBytes(StandardCharsets.UTF_8);
        byte[] ivBytes = generaIv();
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec keySpec = generaHash(password);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(inputBytes);

        byte[] combined = new byte[ivBytes.length + encryptedBytes.length];
        System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
        System.arraycopy(encryptedBytes, 0, combined, ivBytes.length, encryptedBytes.length);

        return combined;
    }

public static String desxifraAES(byte[] bIvMsgXifrat, String clau)
throws Exception {

 byte[] ivBytes = extreureIv(bIvMsgXifrat);
    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
    byte[] encryptedBytes = getBytesXifrats(bIvMsgXifrat);
    SecretKeySpec keySpec = generaHash(clau);

    Cipher cipher = Cipher.getInstance(FORMAT_AES);
    cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

    return new String(decryptedBytes, StandardCharsets.UTF_8);
}

    // Mètodes addicionals opcionals: xifra / desxifra
    public static byte[] xifra(String msg) throws Exception {
        return xifraAES(msg, CLAU);
    }

    public static byte[] xifra(String msg, String password) throws Exception {
        return xifraAES(msg, password);
    }

    public static String desxifra(byte[] bMsgXifrat) throws Exception {
        return desxifraAES(bMsgXifrat, CLAU);
    }

    public static String desxifra(byte[] bMsgXifrat, String password) throws Exception {
        return desxifraAES(bMsgXifrat, password);
    }
    public static void main(String[] args) {
    String msgs[] = {"Lorem ipsum dicet",
        "Hola Andrés cómo está tu cuñado",
        "Àgora ïlla Ôtto"};

    for (int i = 0; i < msgs.length; i++) {
        String msg = msgs[i];

        byte[] bXifrats = null;
        String desxifrat = "";
        try {
            bXifrats = xifraAES(msg, CLAU);
            desxifrat = desxifraAES(bXifrats, CLAU);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " 
                + e.getLocalizedMessage());
        }
        System.out.println("--------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
    }
}
}
