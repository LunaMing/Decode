package solution;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class FrequencyTest {
    Frequency frequency;

    @Before
    public void setUp() {
        frequency = new Frequency();
    }

    @After
    public void tearDown() {
    }

    //功能测：直接测试频率
    @Test
    public void funcTest() {
        String s = "aabc";
        HashMap<Character, Double> hashMap = frequency.countFrequency(s);
        Character key = 'a';
        Double frequnce = 0.5;
        Assert.assertEquals(frequnce, hashMap.get(key));
    }

    //单元测：测试统计字母个数
    @Test
    public void unitTest() {
        String s = "aabc";
        HashMap<Character, Integer> hashMap = frequency.countNum(s);
        Character key = 'a';
        Integer num = 2;
        Assert.assertEquals(num, hashMap.get(key));
    }
}