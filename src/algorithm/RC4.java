package algorithm;

import java.util.ArrayList;

public class RC4 {
    String key;
    ArrayList<Character> SBox = new ArrayList<>();

    public RC4(String key) {
        this.key = key;
    }

    /**
     * 加密
     *
     * @param plainText 明文
     * @return 密文
     */
    public String encrypt(String plainText) {
        String cipherText = "";
        return cipherText;
    }

    /**
     * 解密
     * 和加密一模一样
     *
     * @param cipherText 密文
     * @return 明文
     */
    public String decrypt(String cipherText) {
        return this.encrypt(cipherText);
    }
}
