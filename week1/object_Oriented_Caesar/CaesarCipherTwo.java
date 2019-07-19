package object_Oriented_Caesar;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1, shiftedAlphabet2;
    private int key1, key2;

    CaesarCipherTwo(int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        alphabet=alphabet+alphabet.toUpperCase();
        shiftedAlphabet2+=shiftedAlphabet2.toUpperCase();
        shiftedAlphabet1+=shiftedAlphabet1.toUpperCase();
        this.key1 = key1;
        this.key2 = key2;
    }

    String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                char ch = input.charAt(i);
                int ind = alphabet.indexOf(ch);
                if (ind != -1)
                    encrypted.setCharAt(i, shiftedAlphabet1.charAt(ind));
            } else {
                char ch = input.charAt(i);
                int ind = alphabet.indexOf(ch);
                if (ind != -1)
                    encrypted.setCharAt(i, shiftedAlphabet2.charAt(ind));
            }

        }
        return encrypted.toString();
    }

    String decrypt(String encrypted) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cc.encrypt(encrypted);
    }
}
