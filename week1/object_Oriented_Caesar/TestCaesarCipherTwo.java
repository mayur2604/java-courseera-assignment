package object_Oriented_Caesar;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    void simpleTests() {
        FileResource fr = new FileResource();
        String msg = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String enc = cc.encrypt(msg);
        String dec = cc.decrypt(enc);
        System.out.println("encrypted string: " + enc + "decrypted string: " + dec);
        System.out.println("decrypted text using key guessing " + breakCaesarCipher(enc));
    }

    int[] countLetters(String encrypted) {

        int count[] = new int[26];
        encrypted = encrypted.toLowerCase();
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            if (Character.isLetter(ch))
                count[ch - 'a']++;
        }
        return count;
    }

    int maxIndex(int[] count) {
        int max = 0;
        int in = 0;
        for (int i = 0; i < 26; i++) {

            if (max < count[i]) {
                max = count[i];
                in = i;
            }
        }
        return in;
    }

    String halfOfString(String message, int start) {
        String res = "";
        for (int i = start; i < message.length(); i += 2) {
            res += message.charAt(i);
        }
        return res;
    }

    int getKey(String encrypted) {
        int count[] = countLetters(encrypted);
        int key = maxIndex(count);
        int dkey = key - 4;
        if (dkey < 0)
            dkey += 26;
        return dkey;
    }

    String breakCaesarCipher(String encrypted) {
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);
        int key1 = getKey(half1);
        int key2 = getKey(half2);
        System.out.println("the two keys are " + (26 - key1) + " " + (26 - key2));
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        String msg = cc.encrypt(encrypted);
        return msg;
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tcc = new TestCaesarCipherTwo();
        tcc.simpleTests();

    }
}
