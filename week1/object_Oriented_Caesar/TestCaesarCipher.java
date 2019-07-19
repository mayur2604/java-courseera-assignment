package object_Oriented_Caesar;

import edu.duke.FileResource;

public class TestCaesarCipher {
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
    int getKey(String encrypted) {
        int count[] = countLetters(encrypted);
        int key = maxIndex(count);
        int dkey = key - 4;
        if (dkey < 0)
            dkey += 26;
        return dkey;
    }

    void simpleTests() {
        FileResource fr = new FileResource();
        String msg = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String enc = cc.encrypt(msg);
        String dec = cc.decrypt(enc);
        System.out.println("encrypted string: " + enc + "decrypted string: " + dec);
        breakCaesarCipher(enc);
    }

    void breakCaesarCipher(String input) {
        int dkey = getKey(input);
        CaesarCipher cc = new CaesarCipher(26 - dkey);
        System.out.println("decrypted msg by guessing key: " + cc.encrypt(input));
    }

    public static void main(String[] args) {
        TestCaesarCipher tcc = new TestCaesarCipher();
        tcc.simpleTests();

    }
}
