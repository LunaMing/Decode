package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Frequency {
    /**
     * 统计次数
     *
     * @param inputStr 输入的字符串
     * @return 每个字母的出现次数，是一个Character和Integer的映射表
     */
    public HashMap<Character, Integer> countNum(String inputStr) {
        HashMap<Character, Integer> letterNum = new HashMap<>();

        //遍历字符串，统计字符出现的次数
        for (int i = 0; i < inputStr.length(); i++) {
            char ch = inputStr.charAt(i);
            if (!letterNum.containsKey(ch)) {
                //如果没有这个字母就加入
                letterNum.put(ch, 1);
            } else {
                //如果已经有就加1
                int tempNum = letterNum.get(ch);
                tempNum++;
                letterNum.replace(ch, tempNum);
            }
        }

        return letterNum;
    }

    /**
     * 统计频率
     *
     * @param inputStr 输入字符串
     * @return 每个字母的出现频率，是一个Character和Double的映射表
     */
    public HashMap<Character, Double> countFrequency(String inputStr) {
        HashMap<Character, Double> doubleHashMap = new HashMap<>();
        //统计次数
        HashMap<Character, Integer> letterNum = countNum(inputStr);
        //用次数来计算频率
        int len = inputStr.length();
        for (Character key : letterNum.keySet()) {
            int num_key = letterNum.get(key);
            double d = (double) num_key / (double) len;
            Double fre_key = d;
            doubleHashMap.put(key, fre_key);
        }
        return doubleHashMap;
    }

    /**
     * 按照频率排序
     *
     * @param inputStr 输入的字符串
     * @return 已经排好序的一个字母列表
     */
    public List<Character> sort(String inputStr) {
        List<Character> characterList = new ArrayList<>();
        return characterList;
    }
}
