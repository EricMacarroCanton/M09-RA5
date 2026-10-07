import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class AES {

    public static final String ALORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    public static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau)
throws Exception {
     
    
    byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);    
    // Genera IvParameterSpec
    
    // Genera hash
    
    // Encrypt.
    
    // Combinar IV i part xifrada.
    
    // return iv+msgxifrat
}

public static String desxifraAES(byte[] bIvMsgXifrat, String clau)
throws Exception {

    // Extreure l'IV.
    
    // Extreure la part xifrada.
    
    // Fer hash de la clau
    
    // Desxifrar.
    
    // return String desxifrat
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
