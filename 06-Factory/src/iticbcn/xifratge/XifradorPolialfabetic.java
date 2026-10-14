package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private static final String lletres = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final char[] majuscules = lletres.toCharArray();
    private Random random;

    private void initRandom(String clau) throws ClauNoSuportada {
        try {
            random = new Random(Long.parseLong(clau));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de Polialfabètic ha de ser un String convertible a long");
        }
    }

    private void permutaAlfabet(char[] alfabet) {
        for (int i = alfabet.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char c = alfabet[i];
            alfabet[i] = alfabet[j];
            alfabet[j] = c;
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        initRandom(clau);
        return new TextXifrat(xifraPoliAlfa(msg).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        initRandom(clau);
        return desxifraPoliAlfa(xifrat.toString());
    }

    private String xifraPoliAlfa(String msg) {
        StringBuilder resultat = new StringBuilder();
        char[] alfabetActual = majuscules.clone();
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            char cMajuscula = Character.toUpperCase(c);
            int pos = lletres.indexOf(cMajuscula);
            if (pos != -1) {
                permutaAlfabet(alfabetActual);
                if (c == cMajuscula) {
                    resultat.append(alfabetActual[pos]);
                } else {
                    resultat.append(Character.toLowerCase(alfabetActual[pos]));
                }
            } else {
                resultat.append(c);
            }
        }
        return resultat.toString();
    }

    private String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder resultat = new StringBuilder();
        char[] alfabetActual = majuscules.clone();
        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            char cMajuscula = Character.toUpperCase(c);
            if (lletres.indexOf(cMajuscula) != -1) {
                permutaAlfabet(alfabetActual);
                int pos = -1;
                for (int j = 0; j < alfabetActual.length; j++) {
                    if (alfabetActual[j] == cMajuscula) {
                        pos = j;
                        break;
                    }
                }
                if (c == cMajuscula) {
                    resultat.append(majuscules[pos]);
                } else {
                    resultat.append(Character.toLowerCase(majuscules[pos]));
                }
            } else {
                resultat.append(c);
            }
        }
        return resultat.toString();
    }
}
