import edu.duke.FileResource;


public class Wordlengths {

    static void countWordLengths(FileResource resource, int[] counts) {
        for (String s : resource.words()) {
            int len = 0;
            for (int i = 0; i < s.length(); i++) {
                if (i == 0 || i == s.length() - 1) {
                    char ch = s.charAt(i);
                    if (!Character.isLetter(ch)) {
                    } else len++;
                } else len++;
            }
            if (len >= counts.length)
                len = counts.length - 1;
            counts[len]++;
        }

    }

    static int indexOfMax(int[] counts) {
        int max = 0, in = 0;
        for (int i = 0; i < counts.length; i++) {
            if (max < counts[i]) {
                in = i;
                max = counts[i];
            }
        }
        return in;

    }

    static void testCountWordsLength() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] == 0)
                continue;
            else System.out.println(counts[i] + " Words of length " + i);
        }
        System.out.println("Most Common Word Length " + indexOfMax(counts));
    }

    public static void main(String[] args) {
        testCountWordsLength();
    }
}
