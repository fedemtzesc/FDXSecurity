package mx.com.fdx.security;

abstract class EncryptCryptography extends BaseCryptography {

    /**
     * @hide
     *
     * @param input
     * @param key
     * @return
     * @throws IllegalArgumentException
     * @throws Exception
     */
    protected static String FDXEncrypt(String input, String key)
            throws IllegalArgumentException, Exception {

        // 1. Validamos que la cadena a encriptar no sea una cadena vacia
        if (input == null) {
            throw new IllegalArgumentException("Cadena vacia");
        }

        if (input.trim().equals("")) {
            throw new IllegalArgumentException("Cadena vacia");
        }

        // 2. Validamos la llave de encriptacion, si esta viene vacia, le
        // ponemos el valor por default
        key = key == null ? defaultKey : key;

        // 6. Llenamos el arreglo ASCII de la Llave
        int[] arrKeyAscii = stringToAscii(key);
        int sumKeyAscii = 0;
        for (int i = 0; i < arrKeyAscii.length; i++) {
            sumKeyAscii += (arrKeyAscii[i] * (i + 1)) % 9;
        }

        String reverseInput = new StringBuffer(input).reverse().toString();
        // 8. Llenamos el arreglo con los codigos ASCII de cada caracter de la
        // cadena a encriptar
        int[] arrReverseAscii = stringToAscii(reverseInput);
        int sumReverseAscii = 0;
        for (int i = 0; i < arrReverseAscii.length; i++) {
            sumReverseAscii += (arrReverseAscii[i] * (i + 1)) % 9;
        }

        // Hexadecimal
        int iHexTra = (sumKeyAscii + sumReverseAscii) % 143;
        String sHexTra = Integer.toHexString(iHexTra);
        if (sHexTra.length() == 1) {
            sHexTra = "0" + sHexTra;
        }

        // Mayusculas
        sHexTra = sHexTra.toUpperCase();

        // 9. Hago la suma de los codigos ASCII del primer y ultimo caracter de
        // la llave,
        // y de la longitud de la llave, a esta suma le aplico la longitud de la
        // llave, y
        // finalmente le saco el residuo de dividirlo entre nueve
        int sumKey = (key.charAt(0) + key.charAt(key.length() - 1) + key.length()) % 9;
        if (sumKey == 0) {
            sumKey = 20;
        }

        // 10. Comenzamos a transformar cada uno de los codigos de ASCII para
        // encriptar
        // Iniciamos el indice en la posicion resultante
        int k = (sumKeyAscii + iHexTra) % key.length();
        for (int i = 0; i < arrReverseAscii.length; i++) {
            int temp = 0;
            temp = arrReverseAscii[i] + sumKey + arrKeyAscii[k];
            if (temp > 254) {
                arrReverseAscii[i] = (temp - 254);
            } else {
                arrReverseAscii[i] = temp;
            }

            if (k == key.length() - 1) {
                k = 0;
            } else {
                k += 1;
            }
        }

        // 11. TRansformamos nuevamente cada uno de los codigos de ASCII para
        // encriptar
        for (int i = 0; i < arrReverseAscii.length; i++) {
            int temp = 0;
            temp = arrReverseAscii[i] + iHexTra;
            if (temp > 254) {
                arrReverseAscii[i] = temp - 254;
            } else {
                arrReverseAscii[i] = temp;
            }
        }

        // 12. Generamos la cadena encriptada final
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrReverseAscii.length; i++) {
            String tmp;
            tmp = Integer.toHexString(arrReverseAscii[i]);
            if (tmp.length() == 1) {
                tmp = "0" + tmp;
            }
            result.append(tmp.toUpperCase());
        }

        return new StringBuffer(sHexTra + result.toString()).reverse().toString();
    }
}
