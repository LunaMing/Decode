package solution;

public class Caesar {
    public int offset;

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
        String ciphertext = "";
        if (plaintext == "") {
            return "";
        } else {
            for (int i = 0; i < plaintext.length(); i++) {
                if (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z') {
                    ciphertext += (char) ((plaintext.charAt(i) - 'A' + offset) % 26 + 'A');
                } else if (plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z') {
                    ciphertext += (char) ((plaintext.charAt(i) - 'a' + offset) % 26 + 'a');
                } else {
                    System.out.println("加密错误：不是英文字母");
                }
            }
        }
        return ciphertext;
    }

    public String decrypt(String ciphertext) {
        String plaintext = "";
        if (ciphertext == "") {
            return "";
        } else {
            for (int i = 0; i < ciphertext.length(); i++) {
                if (ciphertext.charAt(i) <= 'Z') {
                    plaintext += (char) ((ciphertext.charAt(i) - 'A' - offset + 26) % 26 + 'A');
                } else {
                    plaintext += (char) ((ciphertext.charAt(i) - 'a' - offset + 26) % 26 + 'a');
                }
            }
        }
        return plaintext;
    }
}
