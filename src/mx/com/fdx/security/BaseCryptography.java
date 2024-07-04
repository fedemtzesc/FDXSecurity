package mx.com.fdx.security;

abstract class BaseCryptography {
    //Llave por default para encriptacion cuando se pone en blanco
    protected static String defaultKey = "AhMesAmies2506";

    protected BaseCryptography() {
    }
    //Metodo que convierte una cadena de texto a su correspondiente arreglo de
    //codigos ASCII enteros
    public static int[] stringToAscii(String value) {
        int[] result = new int[value.length()];
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            result[i] = (int) c;
        }
        return result;
    }
}
