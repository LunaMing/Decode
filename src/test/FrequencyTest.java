package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solution.Frequency;

import java.util.HashMap;
import java.util.TreeMap;

public class FrequencyTest {
    Frequency frequency;

    @Before
    public void setUp() {
        frequency = new Frequency();
    }

    @After
    public void tearDown() {
    }

    //测试统计频率
    @Test
    public void frequencyTest() {
        String s = "aabc";
        TreeMap<Character, Double> hashMap = frequency.countFrequency(s);
        Character key = 'a';
        Double frequnce = 0.5;
        Assert.assertEquals(frequnce, hashMap.get(key));
    }

    //测试统计字母个数
    @Test
    public void countLetterTest() {
        String s = "aabc";
        TreeMap<Character, Integer> hashMap = frequency.countNum(s);
        Character key = 'a';
        Integer num = 2;
        Assert.assertEquals(num, hashMap.get(key));
    }

    //测试频率从大到小排序
    @Test
    public void downSortTest() {
        String s = "aabc";
        Character mostNumLetter = frequency.sort(s).get(0);
        Character mostCh = 'a';
        Assert.assertEquals(mostCh, mostNumLetter);
    }
}