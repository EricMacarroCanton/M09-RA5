public class RotX {

    private static final String lletres = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static char[] minuscules = lletres.toLowerCase().toCharArray();
    public static char[] majuscules = lletres.toCharArray();
    public static String xifraRotX (String cadena, int desplaçament) {

     if (cadena == null) return null;

            String resultat = "";

            for (int i = 0; i < cadena.length(); i++) {
                char lletra = cadena.charAt(i);
                boolean trobat = false;
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == lletra) {
                        int novaPos = (j + desplaçament) % minuscules.length;
                        resultat += minuscules[novaPos];
                        trobat = true;
                        break;
                    }
                }
                if (!trobat) {
                    for (int j = 0; j < majuscules.length; j++) {
                        if (majuscules[j] == lletra) {
                            int novaPos = (j + desplaçament) % majuscules.length;
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



    public static String desxifraRotX (String Cadena, int desplaçament) {
        String resultat = "";

        return "s";
    }

    public static String forcaBrutaRotX () {

        return "s";
    }


        public static void main(String[] args) {
            String original1[] = {"ABC" , "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
            String msgsxifrats[] = new String[original1.length];

            System.out.println("\nXifrat---");
            for (int i = 0; i < original1.length; i++) {
                int desplaçament = i * 2; 
                msgsxifrats[i] = xifraRotX(original1[i], desplaçament);
                System.out.printf("(%d) -%-31s=> %s%n", desplaçament, original1[i], msgsxifrats[i]);            }
        }
    } 
