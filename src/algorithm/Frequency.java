package algorithm;

import java.util.*;

public class Frequency {
    /**
     * 统计次数
     *
     * @param inputStr 输入的字符串
     * @return 每个字母的出现次数，是一个Character和Integer的映射表
     */
    public static TreeMap<Character, Integer> countNum(String inputStr) {
        TreeMap<Character, Integer> letterNumMap = new TreeMap<>();

        //遍历字符串，统计字符出现的次数
        for (int i = 0; i < inputStr.length(); i++) {
            char ch = inputStr.charAt(i);
            if (!letterNumMap.containsKey(ch)) {
                //如果没有这个字母就加入
                letterNumMap.put(ch, 1);
            } else {
                //如果已经有就加1
                int tempNum = letterNumMap.get(ch);
                tempNum++;
                letterNumMap.replace(ch, tempNum);
            }
        }

        return letterNumMap;
    }

    /**
     * 统计频率
     *
     * @param inputStr 输入字符串
     * @return 每个字母的出现频率，是一个Character和Double的映射表
     */
    public static TreeMap<Character, Double> countFrequency(String inputStr) {
        TreeMap<Character, Double> freqMap = new TreeMap<>();
        //统计次数
        TreeMap<Character, Integer> letterNum = countNum(inputStr);
        //用次数来计算频率
        int len = inputStr.length();
        for (Character key : letterNum.keySet()) {
            int num_key = letterNum.get(key);
            Double fre_key = (double) num_key / (double) len;
            freqMap.put(key, fre_key);
        }
        return freqMap;
    }

    /**
     * 按照频率排序
     *
     * @param inputStr 输入的字符串
     * @return 已经排好序的一个字母列表
     */
    public static List<Character> sort(String inputStr) {
        List<Character> characterList = new ArrayList<>();
        TreeMap<Character, Integer> treeMap = countNum(inputStr);
        // 升序比较器
        Comparator<Map.Entry<Character, Integer>> valueComparator = (o1, o2) -> (o2.getValue() - o1.getValue());
        // map转换成list进行排序
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(treeMap.entrySet());
        // 排序
        list.sort(valueComparator);
        for (Map.Entry<Character, Integer> entry : list) {
            characterList.add(entry.getKey());
        }
        return characterList;
    }
}
