package solution;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SubstitutionTableTest {
    SubstitutionTable substitutionTable;

    @Before
    public void setUp() throws Exception {
        //生成代换表
        HashMap<Character, Character> map = new HashMap<>();
        map.clear();
        map.put('a', 'b');
        //初始化要测试的类
        substitutionTable = new SubstitutionTable(map);
    }

    @After
    public void tearDown() throws Exception {
    }

    //"a"->"b"
    @Test
    public void encryptOneChar() {
        //明文
        String plainStr = "a";
        //密文
        String cipherStr;
        //加密
        cipherStr = substitutionTable.encrypt(plainStr);
        //验证
        Assert.assertEquals("b", cipherStr);
    }

    //"aaa"->"bbb"
    @Test
    public void encryptString() {
        //明文
        String plainStr = "aaa";
        //密文
        String cipherStr;
        //加密
        cipherStr = substitutionTable.encrypt(plainStr);
        //验证
        Assert.assertEquals("bbb", cipherStr);
    }

    //"abcdefg hijklmn, opq. Rst? uvwxyz! 2333"->"bbcdefg hijklmn, opq. Rst? uvwxyz! 2333"
    @Test
    public void encryptStringWithOtherWords() {
        //明文
        String plainStr = "abcdefg hijklmn, opq. Rst? uvwxyz! 2333";
        //密文
        String cipherStr;
        //加密
        cipherStr = substitutionTable.encrypt(plainStr);
        //验证
        Assert.assertEquals("bbcdefg hijklmn, opq. Rst? uvwxyz! 2333", cipherStr);
    }

    //"abcdefg hijklmn, opq. Rst? uvwxyz! 2333"<-"bbcdefg hijklmn, opq. Rst? uvwxyz! 2333"
    @Test
    public void decrypt() {
        //密文
        String cipherStr = "bbcdefg hijklmn, opq. Rst? uvwxyz! 2333";
        //明文
        String plainStr;
        //解密
        cipherStr = substitutionTable.decrypt(cipherStr);
        //验证
        Assert.assertEquals("aacdefg hijklmn, opq. Rst? uvwxyz! 2333", cipherStr);
    }
}