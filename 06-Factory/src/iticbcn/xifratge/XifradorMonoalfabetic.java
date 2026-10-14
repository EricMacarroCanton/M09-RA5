package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class XifradorMonoalfabetic implements Xifrador {

    private static final String lletres = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final char[] majuscules = lletres.toCharArray();
    private final char[] permutacio;

    public XifradorMonoalfabetic() {
        permutacio = permutaAlfabet(majuscules);
    }

    private char[] permutaAlfabet(char[] alfabet) {
        char[] permutat = alfabet.clone();
        Random random = new Random();
        for (int i = permutat.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char c = permutat[i];
            permutat[i] = permutat[j];
            permutat[j] = c;
        }
        return permutat;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        String xifrat = substitueix(msg, majuscules, permutacio);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        return substitueix(xifrat.toString(), permutacio, majuscules);
    }

    private void comprovaClau(String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }
    }

    // Substitueix cada lletra de 'origen' per la de 'desti' a la mateixa posició
    private String substitueix(String cadena, char[] origen, char[] desti) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            char cMajuscula = Character.toUpperCase(c);
            int pos = -1;
            for (int j = 0; j < origen.length; j++) {
                if (origen[j] == cMajuscula) {
                    pos = j;
                    break;
                }
            }
            if (pos == -1) {
                resultat.append(c);
            } else if (c == cMajuscula) {
                resultat.append(desti[pos]);
            } else {
                resultat.append(Character.toLowerCase(desti[pos]));
            }
        }
        return resultat.toString();
    }
}
