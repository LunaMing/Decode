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

    @Test
    public void encrypt() {
        //明文
        String plainStr = "a";
        //密文
        String cipherStr = "b";
        //加密
        cipherStr = substitutionTable.encrypt(plainStr);
        //验证
        Assert.assertEquals(cipherStr, "b");
    }

    @Test
    public void decrypt() {
    }
}