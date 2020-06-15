package algorithm;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RC4 {
    String key;
    char[] S;

    public RC4(String key) {
        this.key = key;
    }

    /**
     * S的初始置换
     */
    private void initSBox() {
        //初始化S
        S = new char[256];
        for (int i = 0; i < 256; i++) {
            //每一个格子里面就先放自己的位置
            S[i] = (char) i;
        }

        //初始化T
        char[] T = new char[256];
        //把Key放到T里面，循环反复地放，直到T放满256格为止。
        for (int i = 0; i < 256; i++) {
            //用整除的方式找到放T的位置
            //比如key是abc，T[012]和T[345]就应该分别是abc；
            // T[345] = key[012]
            T[i] = key.charAt(i % key.length());
        }

        //打乱S
        int j = 0;
        for (int i = 0; i < 256; i++) {
            //这里是打乱算法，用S和T来打乱S里面的字符排序
            j = j + S[i] + T[i];
            j %= 256;//防止数组越界
            //swap S[i] S[j]
            char temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
    }

    /**
     * 加密
     *
     * @param plainText 明文
     * @return 密文
     */
    public String encrypt(String plainText) {
        //按照密钥初始化S盒
        initSBox();
        //开始加密
        StringBuilder cipherText = new StringBuilder();
        int i = 0, j = 0, t;
        for (char in : plainText.toCharArray()) {
            //先找到j
            j += S[i];
            j %= 256;//防止数组越界
            t = S[i] + S[j];
            t %= 256;//防止数组越界
            //对字符真正的加密
            char out = (char) (in ^ S[t]);
            cipherText.append(out);
            //swap S[i] S[j]
            char temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            //下一个i
            i++;
            i %= 256;//防止数组越界
        }
        String res = cipherText.toString();
        //经过base64编码，解决显示乱码问题
        return base64Encode(res);
    }

    /**
     * 解密
     * 和加密一模一样
     *
     * @param cipherText 密文
     * @return 明文
     */
    public String decrypt(String cipherText) {
        return this.encrypt(base64Decode(cipherText));
    }

    /**
     * base64编码
     * 解决乱码显示问题
     *
     * @param utf8Str UTF8的字符串
     * @return 用base64编码之后的字符串
     */
    public String base64Encode(String utf8Str) {
        return Base64.getEncoder().encodeToString(utf8Str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64解码
     * 解决乱码显示问题
     *
     * @param base64Str base64的字符串
     * @return 用base64解码码之后的字符串，是UTF8的格式
     */
    public String base64Decode(String base64Str) {
        // 解码
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64Str);
        return new String(base64decodedBytes, StandardCharsets.UTF_8);
    }
}
