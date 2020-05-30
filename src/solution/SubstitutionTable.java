package solution;

import java.util.HashMap;

public class SubstitutionTable {
    HashMap<Character, Character> keyTable;

    SubstitutionTable(HashMap<Character, Character> keyTable) {
        this.keyTable = keyTable;
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @return 密文
     */
    public String encrypt(String plaintext) {
        String ciphertext = "";
        Character tempPlainChar = plaintext.charAt(0);
        Character tempCipherChar = keyTable.get(tempPlainChar);
        ciphertext += tempCipherChar;
        return ciphertext;
    }

    /**
     * 解密
     *
     * @param ciphertext 密文
     * @return 明文
     */
    public String decrypt(String ciphertext) {
        return "";
    }
}
