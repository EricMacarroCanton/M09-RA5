import java.util.Random;

public class Monoalfabetic {

    private static final String lletres = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static char[] minuscules = lletres.toLowerCase().toCharArray();
    public static char[] majuscules = lletres.toCharArray();
    public static char[] permutacio = permutaAlfabet(minuscules);

    public static char[] permutaAlfabet(char[] alfabet) {
        char[] permutat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            permutat[i] = alfabet[i];
        }

        Random random = new Random();
        for (int i = permutat.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = permutat[i];
            permutat[i] = permutat[j];
            permutat[j] = temp;
        }

        return permutat;
    }

   public static String xifraMonoAlfa(String cadena) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int pos = -1;
            for (int j = 0; j < minuscules.length; j++) {
                if (minuscules[j] == c) {
                    pos = j;
                    break;
                }
            }
            if (pos != -1) {
                resultat = resultat + permutacio[pos];
            } else {
                resultat = resultat + c;
            }
        }
        return resultat;
    }
    public static String desxifraMonoAlfa(String cadena) {
       
        return "S";
    }

    public static void main(String[] args) {

    }
}