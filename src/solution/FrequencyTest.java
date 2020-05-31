package solution;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FrequencyTest {
    Frequency frequency;

    @Before
    public void setUp() {
        frequency = new Frequency();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void calculate() {
        String s = "aabc";
        HashMap<Character, Double> hashMap = frequency.calculate(s);
        Character key = 'a';
        Double frequnce = 0.5;
        Assert.assertEquals(frequnce, hashMap.get(key));
    }
}