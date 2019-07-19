package object_Oriented_Caesar;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    CaesarCipher(int key) {
        mainKey = key;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        alphabet+=alphabet.toUpperCase();
        shiftedAlphabet+=shiftedAlphabet.toUpperCase();
    }

    String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ind = alphabet.indexOf(ch);
            if(ind!=-1)
            encrypted.setCharAt(i, shiftedAlphabet.charAt(ind));
        }
        return encrypted.toString();
    }

    String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(encrypted);
    }
}
