/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdxsoft.AES;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Lic. Federico Martinez Escamilla
 */
public class AESCrypter {

    public String encryptString(String stringToEncrypt, String secretKey) {
        String derivedSecretKey = "";

        try {
            // Validamos que la llave secreta sea exactamente de 16 caracteres
            if (secretKey.length() > 16) {
                derivedSecretKey = secretKey.substring(0, 16);
            } else if (secretKey.length() < 16) {
                derivedSecretKey = String.format("%-16s", secretKey);
            } else {
                derivedSecretKey = secretKey;
            }

            // Create key and cipher
            Key aesKey = new SecretKeySpec(derivedSecretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(stringToEncrypt.getBytes("UTF-8"));
            String sEncrypted = DatatypeConverter.printHexBinary(encrypted);
            return sEncrypted;
        } catch (Exception e) {
            return "";
        }
    }

    public String encryptString(String stringToEncrypt, String secretKey, String IV) throws Exception {
        int len = stringToEncrypt.length();
        String newString;

        for (int i = 16;; i += 16) {
            if (len < i) {
                newString = String.format("%-" + i + "s", stringToEncrypt);
                break;
            }
        }
        if (secretKey.length() != 16) {
            throw new Exception("La llave tiene que ser de 16 caracteres");
        }
        if (IV.length() != 16) {
            throw new Exception("El Vector tiene que ser de 16 caracteres");
        }
        byte[] cipher = AESencrypt(newString, secretKey, IV);
        String sEncrypted = DatatypeConverter.printHexBinary(cipher);
        return sEncrypted;
    }

    public String decryptString(String stringToDecrypt, String secretKey) {
        String derivedSecretKey = "";

        try {
            // Validamos que la llave secreta sea exactamente de 16 caracteres
            if (secretKey.length() > 16) {
                derivedSecretKey = secretKey.substring(0, 16);
            } else if (secretKey.length() < 16) {
                derivedSecretKey = String.format("%-16s", secretKey);
            } else {
                derivedSecretKey = secretKey;
            }

            // Create key and cipher
            Key aesKey = new SecretKeySpec(derivedSecretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // encrypt the text
            // decrypt the text
            byte[] toDecrypt = DatatypeConverter.parseHexBinary(stringToDecrypt);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(toDecrypt));
            return decrypted;
        } catch (Exception e) {
            return "";
        }
    }

    public String decryptString(String stringToDecrypt, String secretKey, String IV) throws Exception {
        if (secretKey.length() != 16) {
            throw new Exception("La llave tiene que ser de 16 caracteres");
        }
        if (IV.length() != 16) {
            throw new Exception("El Vector tiene que ser de 16 caracteres");
        }
        byte[] cipher = DatatypeConverter.parseHexBinary(stringToDecrypt);
        String sDecrypted = AESdecrypt(cipher, secretKey, IV);
        return sDecrypted.trim();
    }

    private static byte[] AESencrypt(String stringToEncrypt, String secretKey, String IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(stringToEncrypt.getBytes("UTF-8"));
    }
    
    private static String AESdecrypt(byte[] cipherText, String secretKey, String IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }

    public String getVector(int vectorCode) {
        String result = "";
        try {
            switch (vectorCode) {
                case 2012:
                    result = encryptString("ValeriaAndrea456", "");
                    break;
                case 1978:
                    result = encryptString("Elizabeth0123456", "");
                    break;
                case 1974:
                    result = encryptString("Alicia6789012346", "");
                    break;
                case 1973:
                    result = encryptString("fdx.soft.mx.2506", "");
                    break;
                case 1945:
                    result = encryptString("Faroul7890123456", "");
                    break;
                case 1942:
                    result = encryptString("MaElena890123456", "");
                    break;
                default:
                    result = encryptString("1234567890123456", "");
                    break;
            }
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    public String machineIDGetter(int vectorCode) {
        String result = "";
        String IV = getVector(vectorCode);
        //Desencriptamos el vector
        IV = decryptString(IV, "");

        try {
            switch (vectorCode) {
                case 2012:
                    result = encryptString("select uuid() as uid;", IV, IV);
                    break;
                case 1978:
                    result = encryptString("select uuid() as uid;", IV, IV);
                    break;
                case 1974:
                    result = encryptString("select uuid() as uid;", IV, IV);
                    break;
                case 1973:
                    result = encryptString("select uuid() as uid;", IV, IV);
                    break;
                case 1945:
                    result = encryptString("select uuid() as uid;", IV, IV);
                    break;
                case 1942:
                    result = encryptString("select uuid() as uid;", IV, IV);
                    break;
                default:
                    result = encryptString("select uuid() as uid;", IV, IV);
                    break;
            }
            return result;
        } catch (Exception e) {
            return result;
        }
    }
}
