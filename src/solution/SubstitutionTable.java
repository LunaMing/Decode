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
        //要生成的密文
        String ciphertext = "";
        //明文的某一位
        Character tempPlainChar;
        //密文的某一位
        Character tempCipherChar;
        for (int i = 0; i < plaintext.length(); i++) {
            tempPlainChar = plaintext.charAt(i);
            if (keyTable.get(tempPlainChar) != null) {
                //代换表里面有的字符，就替换掉
                tempCipherChar = keyTable.get(tempPlainChar);
                ciphertext += tempCipherChar;
            } else {
                //没有的字符就保持原状
                ciphertext += tempPlainChar;
            }
        }
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
