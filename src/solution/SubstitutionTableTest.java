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
        //生成代换表
        HashMap<Character, Character> map = new HashMap<>();
        map.put('a', 'b');
        map.put('b', 'a');
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
        String plainStr = "aaa";
        //验证
        Assert.assertEquals("bbb", substitutionTable.encrypt(plainStr));
    }

    //"abcdefg hijklmn, opq. Rst? uvwxyz! 2333"->"bacdefg hijklmn, opq. Rst? uvwxyz! 2333"
    @Test
    public void encryptStringWithOtherWords() {
        //明文
        String plainStr = "abcdefg hijklmn, opq. Rst? uvwxyz! 2333";
        //验证
        Assert.assertEquals("bacdefg hijklmn, opq. Rst? uvwxyz! 2333", substitutionTable.encrypt(plainStr));
    }

    //"abcdefg hijklmn, opq. Rst? uvwxyz! 2333"<-"bacdefg hijklmn, opq. Rst? uvwxyz! 2333"
    @Test
    public void decrypt() {
        //密文
        String cipherStr = "bacdefg hijklmn, opq. Rst? uvwxyz! 2333";
        //验证
        Assert.assertEquals("abcdefg hijklmn, opq. Rst? uvwxyz! 2333", substitutionTable.decrypt(cipherStr));
    }
}