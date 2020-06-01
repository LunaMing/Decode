package algorithm;

public class Caesar {
    int offset;

    public Caesar(int offset) {
        this.offset = offset;
    }

    /**
     * 加密函数。
     * 按照密钥，来进行同偏移量的加密，也就是每个字母向后移动【密钥】次数；
     * 区分大小写，大写一套A-Z循环，小写一套a-z循环。
     *
     * @param plaintext 明文
     * @return 密文
     */
    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            if (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z') {
                ciphertext.append((char) ((plaintext.charAt(i) - 'A' + offset) % 26 + 'A'));
            } else if (plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z') {
                ciphertext.append((char) ((plaintext.charAt(i) - 'a' + offset) % 26 + 'a'));
            } else {
                ciphertext.append(plaintext.charAt(i));//不是英文字母的就保持原状
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            if (ciphertext.charAt(i) <= 'Z') {
                plaintext.append((char) ((ciphertext.charAt(i) - 'A' - offset + 26) % 26 + 'A'));
            } else {
                plaintext.append((char) ((ciphertext.charAt(i) - 'a' - offset + 26) % 26 + 'a'));
            }
        }
        return plaintext.toString();
    }
}
