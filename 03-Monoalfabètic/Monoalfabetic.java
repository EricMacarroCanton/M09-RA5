import java.util.Random;

public class Monoalfabetic {

    private static final String lletres = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static char[] majuscules = lletres.toCharArray();
    public static char[] permutacio = permutaAlfabet(majuscules);

    public static char[] permutaAlfabet(char[] alfabet) {
        char[] permutat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            permutat[i] = alfabet[i];
        }

        Random random = new Random();
        for (int i = permutat.length - 1; i > 0; i--) {
            int j = random.nextInt(i+1); //// i + 1 perquè si no no es podria permutar per si mateixa
            char c = permutat[i];
            permutat[i] = permutat[j];
            permutat[j] = c;
        }

        return permutat;
    }

   public static String xifraMonoAlfa(String cadena) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            char cMajuscula = Character.toUpperCase(c);
            int pos = -1;
            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == cMajuscula) {
                    pos = j;
                    break;
                }
            }
            if (pos != -1) {
                if (c == cMajuscula) {
                resultat = resultat + permutacio[pos];
            } else {
                resultat = resultat + Character.toLowerCase(permutacio[pos]);
            } 
        }
        else {
             resultat = resultat + c;
             }
        }
        return resultat;
    }
   public static String desxifraMonoAlfa(String cadena) {
    String resultat = "";
    for (int i = 0; i < cadena.length(); i++) {
        char c = cadena.charAt(i);
        char cMajuscula = Character.toUpperCase(c);
        int pos = -1;
        for (int j = 0; j < permutacio.length; j++) {
            if (permutacio[j] == cMajuscula) {
                pos = j;
                break;
            }
        }
        if (pos != -1) {
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
    String alfabet = "";
    String alfabetPermutat = "";
    for (int i = 0; i < majuscules.length; i++) {
        alfabet = alfabet + majuscules[i] + " ";
        alfabetPermutat = alfabetPermutat + Character.toUpperCase(permutacio[i]) + " ";
    }
    System.out.println(alfabet);
    System.out.println(alfabetPermutat);

    String[] proves = {
        "Test 01 àrbitre, coixí, Perímetre",
        "Test 02 Taüll, DÍA, año",
        "Test 03 Peça, Òrrius, Bòvila"
    };

    System.out.println("Xifratge:");
    for (int i = 0; i < proves.length; i++) {
        String xifrat = xifraMonoAlfa(proves[i]);
        System.out.println(proves[i] + " -> " + xifrat);
    }

    System.out.println("Desxifratge:");
    for (int i = 0; i < proves.length; i++) {
        String xifrat = xifraMonoAlfa(proves[i]);
        String desxifrat = desxifraMonoAlfa(xifrat);
        System.out.println(xifrat + " -> " + desxifrat);
    }
}
}