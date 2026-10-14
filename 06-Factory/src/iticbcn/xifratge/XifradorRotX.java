package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {

    private static final String lletres = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private static final String majuscules = lletres;
    private static final String minuscules = lletres.toLowerCase();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = convertirClau(clau);
        return new TextXifrat(desplaça(msg, desplaçament).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = convertirClau(clau);
        return desplaça(xifrat.toString(), -desplaçament);
    }

    private int convertirClau(String clau) throws ClauNoSuportada {
        try {
            return Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter");
        }
    }

    private String desplaça(String cadena, int desplaçament) {
        if (cadena == null) return null;
        int n = majuscules.length();
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int pos = minuscules.indexOf(c);
            if (pos != -1) {
                resultat.append(minuscules.charAt(Math.floorMod(pos + desplaçament, n)));
                continue;
            }
            pos = majuscules.indexOf(c);
            if (pos != -1) {
                resultat.append(majuscules.charAt(Math.floorMod(pos + desplaçament, n)));
            } else {
                resultat.append(c);
            }
        }
        return resultat.toString();
    }
}
