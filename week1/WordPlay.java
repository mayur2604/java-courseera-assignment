
public class WordPlay {

    public static boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
            return true;
        return false;
    }

    public static String replaceVowels(String phrase, char ch) {

        char[] res = phrase.toCharArray();
        String result = "";
        for (int i = 0; i < res.length; i++) {
            if (isVowel(res[i]))
                res[i] = ch;
            result += res[i];
        }
        return result;
    }

    public static String emphasize(String phrase, char ch) {
        char res[] = phrase.toCharArray();
        String result = "";
        for (int i = 0; i < res.length; i++) {
            if (res[i] == ch) {
                if ((i + 1) % 2 == 0)
                    res[i] = '+';
                else res[i] = '*';

            }
            //System.out.println(res[i]);
            result += res[i];
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
