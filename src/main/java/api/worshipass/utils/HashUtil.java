/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author David
 */
public class HashUtil {
    public static String sha256(String text) {
        if (text == null) return null;
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                
                if (hex.length() == 1) hexString.append('0');
                
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash SHA-256", e);
        }
    }
}
