package solution;

public class Caesar {
    public int offset;

    public Caesar(int offset) {
        this.offset = offset;
    }

    public String encrypt(String plaintext) {
        String ciphertext = "";
        if (plaintext == "") {
            return "";
        } else {
            for (int i = 0; i < plaintext.length(); i++) {
                if (plaintext.charAt(i) <= 'Z') ciphertext += (char) ((plaintext.charAt(i) - 'A' + offset) % 26 + 'A');
                else ciphertext += (char) ((plaintext.charAt(i) - 'a' + offset) % 26 + 'a');
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
                if (ciphertext.charAt(i) <= 'Z')
                    plaintext += (char) ((ciphertext.charAt(i) - 'A' - offset + 26) % 26 + 'A');
                else plaintext += (char) ((ciphertext.charAt(i) - 'a' - offset + 26) % 26 + 'a');
            }
        }
        return plaintext;
    }
}
