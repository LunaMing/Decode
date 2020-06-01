package algorithmTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import algorithm.Caesar;

public class CaesarTest {

    Caesar caesar;

    @Before
    public void setUp() throws Exception {
        int key = 1;//a->b
        caesar = new Caesar(key);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encrypt() {
        //明文
        String plainStr = "abc";
        //验证加密成果
        Assert.assertEquals("bcd", caesar.encrypt(plainStr));
    }

    @Test
    public void decrypt() {
        //密文
        String cipherStr = "bcd";
        //验证解密成果
        Assert.assertEquals("abc", caesar.decrypt(cipherStr));
    }
}