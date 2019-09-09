package FX.FXEncryptor;

import java.util.Calendar;

public class Cipher {
    public enum Type {
        ENCRYPT,DECRYPT
    }
    private static int SALT = (Calendar.getInstance().get(Calendar.YEAR) + 7) * (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + 3);
    private String key;
    public Cipher (int length) {
        key = genKey(length);
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public static String processMessage (Type type, String message, String key) {
        String newStr = "";
        if (type == Type.ENCRYPT) {
            for (int i = 0; i < message.length(); i++)
                newStr += (char)(message.charAt(i) + SALT + key.charAt(i%key.length()));
            return newStr;
        } else {
            for (int i = 0; i < message.length(); i++)
                newStr += (char)(message.charAt(i) - SALT - key.charAt(i%key.length()));
            return newStr;
        }
    }
    public static String genKey(int len) {
        String s = "";
        for (int i = 0; i < len; i++)
            s += (char)((int)(Math.random()*1000));
        return s;
    }
    public static String processMessage (Type type, String message) {
        String newStr = "";
        if (type == Type.ENCRYPT) {
            for (int i = 0; i < message.length(); i++)
                newStr += (char)(Integer.parseInt(change(Integer.toBinaryString(message.charAt(i))),2));
            return newStr;

        } else if (type == Type.DECRYPT) {
            for (int i = 0; i < message.length(); i++)
                newStr += (char)(Integer.parseInt(change(Integer.toBinaryString(message.charAt(i))),2));
            return newStr;
        }
        return newStr;
    }
    public static String change (String binaryString) {
        String newStr = "";
        int charCount = 3;
        for (char c: binaryString.toCharArray()) {
            if (charCount++ == 5) {
                newStr += c == '1' ? '0' : '1';
                charCount = 0;
            }
            else
                newStr += c;
        }
        return newStr;
    }
}