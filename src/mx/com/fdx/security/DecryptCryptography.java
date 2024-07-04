package mx.com.fdx.security;

abstract class DecryptCryptography extends EncryptCryptography {

    /**
     * @hide
     *
     * @param input
     * @param key
     * @return
     * @throws IllegalArgumentException
     * @throws Exception
     */
    protected static String FDXDecrypt(String input, String key)
            throws IllegalArgumentException, Exception {
        // 1. Validamos que la cadena a encriptar no sea una cadena vacia
        if (input == null) {
            throw new IllegalArgumentException("Cadena vacia");
        }

        if (input.trim().equals("")) {
            throw new IllegalArgumentException("Cadena vacia");
        }

        // 2. Valido que la longitud de la cadena a desencriptar sea par
        if (input.trim().length() % 2 != 0) {
            throw new IllegalArgumentException("Cadena invalida");
        }

        // 3. Validamos la llave de encriptacion, si esta viene vacia, le
        // ponemos el valor por default
        key = key == null ? defaultKey : key;

        // 6. Llenamos el arreglo ASCII de la Llave
        int[] arrKeyAscii = stringToAscii(key);
        int sumKeyAscii = 0;
        for (int i = 0; i < arrKeyAscii.length; i++) {
            sumKeyAscii += (arrKeyAscii[i] * (i + 1)) % 9;
        }

        // 9. Hago la suma de los codigos ASCII del primer y ultimo caracter de
        // la llave,
        // y de la longitud de la llave, a esta suma le aplico la longitud de la
        // llave, y
        // finalmente le saco el residuo de dividirlo entre nueve
        int sumKey = (key.charAt(0) + key.charAt(key.length() - 1) + key.length()) % 9;
        if (sumKey == 0) {
            sumKey = 20;
        }

        String reverseInput = new StringBuffer(input).reverse().toString();

        // 9. Obtenemos el factor de variacion de la cadena encriptada
        int iHexTra = Integer.parseInt(reverseInput.substring(0, 2), 16);

        // 10. Separamos la cadena a encriptar del factor de variacion
        reverseInput = reverseInput.substring(2);

        // 9. Llenamos el arreglo con los codigos ASCII tranformando de HEXAL a
        // ENTERO
        int[] arrInputAscii = new int[(input.length() / 2) - 1];
        int k = 0;
        for (int i = 0; i < reverseInput.length(); i += 2) {
            int iAscii2 = 0;
            String sAscii;
            sAscii = reverseInput.substring(i, i + 2);
            iAscii2 = Integer.parseInt(sAscii, 16);
            arrInputAscii[k] = iAscii2;
            k++;
        }

        // 10. Transformamos la parte media del arreglo de codigos ASCII
        // aplicando el incremento
        k = (sumKeyAscii + iHexTra) % key.length();
        for (int i = 0; i < arrInputAscii.length; i++) {
            int tmp = 0;
            tmp = arrInputAscii[i] - sumKey - arrKeyAscii[k];
            if (tmp < 1) {
                arrInputAscii[i] = 254 + tmp;
            } else {
                arrInputAscii[i] = tmp;
            }

            if (k == key.length() - 1) {
                k = 0;
            } else {
                k++;
            }
        }

        // 11. Transformamos nuevamente la parte media del arreglo de codigos
        // ASCII aplicando el incremento
        for (int i = 0; i < arrInputAscii.length; i++) {
            int tmp = 0;
            tmp = arrInputAscii[i] - iHexTra;
            if (tmp < 1) {
                arrInputAscii[i] = 254 + tmp;
            } else {
                arrInputAscii[i] = tmp;
            }

        }

        // 12. Llenamos el arreglo con los codigos ASCII tranformando de HEXAL a
        // ENTERO
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrInputAscii.length; i++) {
            result.append(arrInputAscii[i]);
        }

        return result.reverse().toString();
    }
}
