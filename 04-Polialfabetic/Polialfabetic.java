import java.util.Random;

public class Polialfabetic {

    private static final String lletres = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static char[] majuscules = lletres.toCharArray();
    public static Random random;
    private static final long clauSecreta = 1234;

    private static void initRandom(long clauSecreta) {
        random = new Random(clauSecreta);
    }

    public static void permutaAlfabet(char[] alfabet) {
        for (int i = alfabet.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char c = alfabet[i];
            alfabet[i] = alfabet[j];
            alfabet[j] = c;
        }
    }

    public static String xifraPoliAlfa(String msg) {
        String resultat = "";
        char[] alfabetActual = new char[majuscules.length];
        for (int i = 0; i < majuscules.length; i++) {
            alfabetActual[i] = majuscules[i];
        }
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            char cMajuscula = Character.toUpperCase(c);
            int pos = -1;
            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == cMajuscula) {
                    pos = j;
                    break;
                }
            }
            if (pos != -1) {
                permutaAlfabet(alfabetActual);
                if (c == cMajuscula) {
                    resultat = resultat + alfabetActual[pos];
                } else {
                    resultat = resultat + Character.toLowerCase(alfabetActual[pos]);
                }
            } else {
                resultat = resultat + c;
            }
        }
        return resultat;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        String resultat = "";
        char[] alfabetActual = new char[majuscules.length];
        for (int i = 0; i < majuscules.length; i++) {
            alfabetActual[i] = majuscules[i];
        }
        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            char cMajuscula = Character.toUpperCase(c);
            permutaAlfabet(alfabetActual);
            int pos = -1;
            for (int j = 0; j < alfabetActual.length; j++) {
                if (alfabetActual[j] == cMajuscula) {
                    pos = j;
                    break;
                }
            }
            if (pos != -1) {
                permutaAlfabet(alfabetActual); 
                if (c == cMajuscula) {
                    resultat = resultat + majuscules[pos];
                } else {
                    resultat = resultat + Character.toLowerCase(majuscules[pos]);
                }
            } else {
                resultat = resultat + c;
            }
        }
        return resultat;
    }

    public static void main(String[] args) {
    String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                     "Test 02 Taüll, DÍA, año",
                     "Test 03 Peça, Òrrius, Bòvila"};
    String msgsXifrats[] = new String[msgs.length];

    System.out.println("Xifratge:\n--------");
    for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
    }

    System.out.println("Desxifratge:\n--------");
    for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        String msg = desxifraPoliAlfa(msgsXifrats[i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
    }
}
    
}
