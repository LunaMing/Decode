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
        int offset = 1;//a->b
        caesar = new Caesar(offset);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encrypt() {
        //明文
        String plainStr = "a";
        //密文
        String cipherStr = "b";
        //加密
        cipherStr = caesar.encrypt(plainStr);
        //验证加密成果
        Assert.assertEquals(cipherStr, "b");
    }

    @Test
    public void decrypt() {
    }
}