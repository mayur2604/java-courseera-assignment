package Caesar;

import edu.duke.*;

public class CaesarCipher {
    public static String encrypt(String input, int key) {
        String cipher = "";
        for (int i = 0; i < input.length(); i++) {
            int ch = input.charAt(i);
            int res;

            if (ch >= 65 && ch <= 90) {
                ch = ch - 'A';
                ch += key;
                if (ch >= 26)
                    ch -= 26;
                cipher += (char) (ch + 'A');
            } else if (ch >= 97 && ch <= 122) {
                ch -= 'a';
                ch += key;
                if (ch >= 26)
                    ch -= 26;
                cipher += (char) (ch + 'a');
            } else
                cipher += (char) ch;
        }
        return cipher;
    }

    public static String encryptWithTwoKeys(String input, int key1, int key2) {
        String cipher = "";
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                int ch = input.charAt(i);
                if (ch >= 65 && ch <= 90) {
                    ch = ch - 'A';
                    ch += key1;
                    if (ch >= 26)
                        ch -= 26;
                    cipher += (char) (ch + 'A');
                } else if (ch >= 97 && ch <= 122) {
                    ch -= 'a';
                    ch += key1;
                    if (ch >= 26)
                        ch -= 26;
                    cipher += (char) (ch + 'a');
                } else
                    cipher += (char) ch;
            } else {
                int ch = input.charAt(i);
                if (ch >= 65 && ch <= 90) {
                    ch = ch - 'A';
                    ch += key2;
                    if (ch >= 26)
                        ch -= 26;
                    cipher += (char) (ch + 'A');
                } else if (ch >= 97 && ch <= 122) {
                    ch -= 'a';
                    ch += key2;
                    if (ch >= 26)
                        ch -= 26;
                    cipher += (char) (ch + 'a');
                } else
                    cipher += (char) ch;
            }
        }
        return cipher;
    }

    public static void testCaeser() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 3;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }


}
