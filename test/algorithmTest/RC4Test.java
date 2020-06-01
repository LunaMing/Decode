package algorithmTest;

import algorithm.RC4;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RC4Test {

    RC4 rc4;

    @Before
    public void setUp() {
        rc4 = new RC4("abc");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void encryptAndDecrypt() {
        String expectedStr = "ab";
        String plainS = expectedStr;
        String cipherS;
        cipherS = rc4.encrypt(plainS);
        System.out.println("密文：【" + cipherS + "】。\n");
        plainS = rc4.decrypt(cipherS);
        Assert.assertEquals(expectedStr, plainS);
    }

}