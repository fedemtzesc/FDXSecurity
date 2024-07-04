package mx.com.fdx.security;

/**
 * Utileria para encriptacion/desencriptacion
 * 
 * @author Datalogic
 * @date 13/04/2010
 * @version 1.0
 * 
 */
public final class FDXCryptography extends DecryptCryptography {

    private FDXCryptography() {
    }

    /**
     * Encripta una cadena
     *
     * @param input
     *            Cadena a encriptar
     * @param key
     *            Llave
     * @return Cadena encriptada
     *
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String encryptString(String input, String key)
            throws IllegalArgumentException, Exception {

        if (key == null) {
            key = defaultKey;
        }

        if (key.trim().equals("")) {
            key = defaultKey;
        }

        return FDXEncrypt(input, key);
    }

    /**
     * Encripta una cadena
     *
     * @param input
     *            Cadena a encriptar
     * @return Cadena encriptada
     *
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String encryptString(String input)
            throws IllegalArgumentException, Exception {
        return FDXEncrypt(input, null);
    }

    /**
     * Desencripta una cadena
     *
     * @param input
     *            Cadena a desencriptar
     * @param key
     *            Llave
     *
     * @return Cadena desencriptada
     *
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String decryptString(String input, String key)
            throws IllegalArgumentException, Exception {

        if (key == null) {
            key = defaultKey;
        }

        if (key.trim().equals("")) {
            key = defaultKey;
        }

        return FDXDecrypt(input, key);
    }

    /**
     * Desencripta una cadena
     *
     * @param input
     *            Cadena a desencriptar
     *
     * @return Cadena desencriptada
     *
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String decryptString(String input)
            throws IllegalArgumentException, Exception {
        return FDXDecrypt(input, null);
    }
}
