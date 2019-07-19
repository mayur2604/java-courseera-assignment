package Caesar;

public class Main {
    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 23);
        CaesarBreaker cb = new CaesarBreaker();
        String decrytped = cb.decrypt(encrypted);
        System.out.println("encrypted string " + encrypted);
        System.out.println("decrypted string " + decrytped);
        String enc = cc.encryptWithTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 23, 2);
        String dec = cb.decryptTwoKeys(enc);
        System.out.println("enc with 2 keys " + enc);
        System.out.println("dec with 2 keys " + dec);

    }
}
