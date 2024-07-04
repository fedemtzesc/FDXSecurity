/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fdxsoft.AES;



/**
 *
 * @author federico
 */
public class TestingApp {
    public static void main(String args[]){
        AESCrypter c = new AESCrypter();
        String cadenaAEncriptar = "Federico Martinez E.";
        String llaveEncripcion = "fdx";
        
        String cadenaEncriptada = c.encryptString(cadenaAEncriptar, llaveEncripcion);
        System.out.println("Cadena a encriptar: " + cadenaAEncriptar);
        System.out.println("Cadena encriptada: " + cadenaEncriptada);
        
        String cadenaDesencriptada = c.decryptString(cadenaEncriptada, llaveEncripcion);
        System.out.println("Cadena desencriptada: " + cadenaDesencriptada);
    }
}
