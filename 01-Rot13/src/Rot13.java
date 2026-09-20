public class Rot13 {
    
    private static final String lletres = "AÀÁBCÇDEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÜÙVWXYZ";
    public static char[] minuscules = lletres.toLowerCase().toCharArray();
    public static char[] majuscules = lletres.toCharArray();

    public static String xifraRot13(String cadena) {
    if (cadena == null) return null;
        
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean trobat = false;
          for (int j = 0; j < minuscules.length; j++) {
                if (minuscules[j] == lletra) {
                    int novaPos = (j + 13) % minuscules.length;
                    resultat += minuscules[novaPos];
                    trobat = true;
                    break;
                }
            }
            if (!trobat) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == lletra) {
                        int novaPos = (j + 13) % majuscules.length;
                        resultat += majuscules[novaPos];
                        trobat = true;
                        break;
                    }
                }
            }

            if (!trobat) {
                resultat += lletra;
            }
        }

        return resultat;
    }
        
public static String desxifraRot13(String cadena) {
    if (cadena == null) return null;
  String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean trobat = false;

            for (int j = 0; j < minuscules.length; j++) {
                if (minuscules[j] == lletra) {
                    int novaPos = (j - 13) % minuscules.length;
                    if (novaPos < 0) novaPos += minuscules.length;
                    resultat += minuscules[novaPos];
                    trobat = true;
                    break;
                }
            }

            if (!trobat) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == lletra) {
                        int novaPos = (j - 13) % majuscules.length;
                        if (novaPos < 0) novaPos += majuscules.length;
                        resultat += majuscules[novaPos];
                        trobat = true;
                        break;
                    }
                }
            }

            if (!trobat) {
                resultat += lletra;
            }
        }

        return resultat;
    }

public static void main(String[] args) {
        String original1[] = {"ABC" , "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?",};
        String msgsxifrats[] = new String[original1.length];

        System.out.println("\nXifrat---");
        for (int i = 0; i < original1.length; i++) {
            msgsxifrats[i] = xifraRot13(original1[i]);
            System.out.printf("%-23sc=> %s%n" , original1[i], msgsxifrats[i]);
        }
        System.out.println("\nDesxifrat---");

            for(String msg : msgsxifrats) {
                System.out.printf("%-23sc=> %s%n" , msg, desxifraRot13(msg));
            }
   }

