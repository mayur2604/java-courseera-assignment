import java.io.File;
import java.util.*;

import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String res = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            res += message.charAt(i);
        }
        return res;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i = 0; i < klength; i++) {
            String res = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(res);
            // System.out.println(key[i]);
        }

        return key;
    }


    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<>();
        for (String s : fr.words())
            dict.add(s.toLowerCase());
        return dict;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] valid = message.split("\\W+");
        int cn = 0;
        for (String t : valid) {
            if (dictionary.contains(t.toLowerCase()))
                cn++;
        }

        return cn;

    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        int[] count = new int[26];

        for (String s : dictionary) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= 97 && s.charAt(i) <= 122)
                    count[s.charAt(i) - 'a']++;
            }
        }
        int max = 0, in = 0;
        for (int i = 0; i < 26; i++) {
            if (max < count[i]) {
                max = count[i];
                in = i;
            }
        }

        return (char) (in + 'a');
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String language = "", decrypted = "";
        for (Map.Entry<String, HashSet<String>> m : languages.entrySet()) {
            String lang = m.getKey();
            HashSet<String> dict = m.getValue();
            String dec = breakForLanguage(encrypted, dict);
            int valid = countWords(dec, dict);
            if (max < valid) {
                max = valid;
                language = lang;
                decrypted = dec;
            }

        }
        System.out.println(language + " " + decrypted);
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        VigenereCipher vc;
        int max = 0;
        String decrypted = "";
        char ch = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++) {
            int key[] = tryKeyLength(encrypted, i, ch);
            vc = new VigenereCipher(key);
            String dec = vc.decrypt(encrypted);
            int validWords = countWords(dec, dictionary);
            if (max < validWords) {
                max = validWords;
                decrypted = dec;
            }
        }
        return decrypted;
    }

    public void breakVigenere() {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String msg = fr.asString();
        HashMap<String, HashSet<String>> h = new HashMap<>();

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource temp = new FileResource(f);
            HashSet<String> dict = readDictionary(temp);
            h.put(f.getName(), dict);
        }
        breakForAllLangs(msg, h);
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }

}
