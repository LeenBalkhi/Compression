package TextFilesCompression;

import java.io.*;
import java.util.*;

public class shannonFano {

    public void compression() throws Exception {

        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        List<Character> fileArr = new ArrayList<Character>();
        TreeMap<Character, Float> probabilityMap = new TreeMap<Character, Float>();

        fileArr = readFile("C:\\Users\\حسين\\Desktop\\multi\\Compression\\src\\TextFilesCompression\\text1.txt");
        map = buildMap(fileArr);

//================get number of characters==================
        int charNum = map.values().stream().mapToInt(Integer::new).sum();

//===========start get probability========================
        for (int i = 0; i < fileArr.size(); i++) {
            probabilityMap.put(fileArr.get(i), (map.get(fileArr.get(i)).floatValue()) / charNum);
        }
//==============end get probability======================

//===============sort map===========================
        Map sortdeMap = sortByValues(probabilityMap);
//============add map to TableList
        Set set = sortdeMap.entrySet();
        Iterator itr = set.iterator();
        int counter = 0;
        TableList list = new TableList();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            list.keys.add((char) entry.getKey());
            list.values.add((Float) entry.getValue());
            list.sympoles.add(Integer.toBinaryString(counter));
            counter++;
        }
        writeFile("C:\\Users\\حسين\\Desktop\\multi\\Compression\\src\\TextFilesCompression\\text2.txt", list.sympoles, list.keys, list.values);

    }
//=======================================================================

    //======start read text file=========
    public List<Character> readFile(String fileName) {
        FileReader file = null;
        List<Character> fileArr = new ArrayList<Character>();

        try {
            file = new FileReader(fileName);

            int i;
            int counter = -1;

            while ((i = file.read()) != -1) {
                fileArr.add((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileArr;
    }
//======end read text file===========

    //======start build map==============
    public TreeMap<Character, Integer> buildMap(List<Character> fileArr) {
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        for (int i = 0; i < fileArr.size(); i++) {

            if (map.get(fileArr.get(i)) == null) {
                map.put(fileArr.get(i), 1);
            } else {
                int value = map.get(fileArr.get(i)).intValue();
                value++;
                map.put(fileArr.get(i), value);
            }
        }
        System.out.println("map:" + map);
        return map;
    }
//======end build map================

    //======start sort by value==========
    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
//======end sort by value============

    //======start write text file========
    public void writeFile(String fileName, List<String> list, List<Character> keys, List<Float> values) throws IOException {
        File file = new File(fileName);
        FileWriter fileW = new FileWriter(file, false);
        BufferedWriter file2 = new BufferedWriter(fileW);
        PrintWriter printWriter = new PrintWriter(file2);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
            }
            for (Character i : keys) {
                printWriter.print(i);
                printWriter.print('.');
            }
            printWriter.println();
            for (Float i : values) {
                printWriter.print(i);
                printWriter.print('.');
            }
            printWriter.println();
            for (String i : list) {
                printWriter.print(i);
                printWriter.print('.');
            }
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//======end write text file==========


}