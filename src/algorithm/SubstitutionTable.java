package algorithm;

import java.util.HashMap;

public class SubstitutionTable {
    HashMap<Character, Character> keyTable;

    public SubstitutionTable(HashMap<Character, Character> keyTable) {
        this.keyTable = keyTable;
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @return 密文
     */
    public String encrypt(String plaintext) {
        //要生成的密文
        StringBuilder ciphertext = new StringBuilder();
        //明文的某一位
        Character tempPlainChar;
        //密文的某一位
        Character tempCipherChar;
        //根据代换表逐位替换
        for (int i = 0; i < plaintext.length(); i++) {
            tempPlainChar = plaintext.charAt(i);
            tempCipherChar = keyTable.getOrDefault(tempPlainChar, tempPlainChar);
            ciphertext.append(tempCipherChar);
        }
        return ciphertext.toString();
    }

    /**
     * 解密
     *
     * @param ciphertext 密文
     * @return 明文
     */
    public String decrypt(String ciphertext) {
        //要生成的明文
        StringBuilder plaintext = new StringBuilder();
        //明文的某一位
        Character tempPlainChar;
        //密文的某一位
        Character tempCipherChar;
        //反转代换表
        HashMap<Character, Character> reverseMap = new HashMap<>();
        for (Character key : keyTable.keySet()) {
            reverseMap.put(keyTable.get(key), key);
        }
        //根据代换表逐位替换
        for (int i = 0; i < ciphertext.length(); i++) {
            tempCipherChar = ciphertext.charAt(i);
            tempPlainChar = reverseMap.getOrDefault(tempCipherChar, tempCipherChar);
            plaintext.append(tempPlainChar);
        }
        return plaintext.toString();
    }
}
