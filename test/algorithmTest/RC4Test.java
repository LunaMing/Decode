package algorithmTest;

import algorithm.RC4;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RC4Test {

    RC4 rc4;

    @Before
    public void setUp() throws Exception {
        rc4 = new RC4("abc");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encryptAndDecrypt() {
        String plainS = "aabc";
        String cipherS = "";
        cipherS = rc4.encrypt(plainS);
        System.out.println("密文：【" + cipherS + "】。\n");
        plainS = rc4.decrypt(cipherS);
        Assert.assertEquals("aabc", plainS);
    }

}