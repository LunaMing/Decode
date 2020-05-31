package solution;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class SubstitutionTableTest {
    SubstitutionTable substitutionTable;

    @Before
    public void setUp() {
        HashMap<Character, Character> map = new HashMap<>();
        //初始化代换表
        map.put('a', 'b');
        map.put('b', 'a');
        map.put('A', 'B');
        map.put('B', 'A');
        //小写->大写
        char ch;
        char bigCh;
        ch = 'c';
        bigCh = 'C';
        while (ch <= 'z') {
            map.put(ch, bigCh);
            ch++;
            bigCh++;
        }
        //大写->小写
        ch = 'C';
        bigCh = 'c';
        while (ch <= 'Z') {
            map.put(ch, bigCh);
            ch++;
            bigCh++;
        }
        //数字
        ch = '0';
        bigCh = '1';
        while (ch <= '8') {
            map.put(ch, bigCh);
            ch++;
            bigCh++;
        }
        //初始化要测试的类
        substitutionTable = new SubstitutionTable(map);
    }

    @After
    public void tearDown() {
    }

    //"a"->"b"
    @Test
    public void encryptOneChar() {
        //明文
        String plainStr = "a";
        //验证
        Assert.assertEquals("b", substitutionTable.encrypt(plainStr));
    }

    //"aaa"->"bbb"
    @Test
    public void encryptString() {
        //明文
        String plainStr = "aAa";
        //验证
        Assert.assertEquals("bBb", substitutionTable.encrypt(plainStr));
    }

    //"abcdefg hijklmn, opq. Rst? uvwxyz! 2333"->"baCDEFG HIJKLMN, OPQ. RST? UVWXYZ! 2333"
    @Test
    public void encryptAndDecryptWithOtherWords() {
        //明文
        String plainStr = "abcdefg hijklmn, opq. Rst? uvwxyz! 2333";
        //加密
        String cipherStr = substitutionTable.encrypt(plainStr);
        //验证
        Assert.assertEquals("baCDEFG HIJKLMN, OPQ. rST? UVWXYZ! 3444", cipherStr);
        //解密
        plainStr = substitutionTable.decrypt(cipherStr);
        //验证
        Assert.assertEquals("abcdefg hijklmn, opq. Rst? uvwxyz! 2333", plainStr);
    }

    //"ab"<-"ba"
    @Test
    public void decryptString() {
        //密文
        String cipherStr = "ba";
        //验证
        Assert.assertEquals("ab", substitutionTable.decrypt(cipherStr));
    }
}