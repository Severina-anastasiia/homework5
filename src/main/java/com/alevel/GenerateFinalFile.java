package com.alevel;

import com.alevel.list.ArrayListTest;
import com.alevel.list.LinkedListTest;
import com.alevel.map.HashMapTest;
import com.alevel.map.LinkedHashMapTest;
import com.alevel.map.TreeMapTest;
import com.alevel.set.HashSetTest;
import com.alevel.set.LinkedHashSetTest;
import com.alevel.set.TreeSetTest;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class GenerateFinalFile {
    private ArrayListTest testArrayList = new ArrayListTest();
    private LinkedListTest testLinkedList = new LinkedListTest();
    private HashSetTest testHashSet = new HashSetTest();
    private LinkedHashSetTest testLinkedHashSet = new LinkedHashSetTest();
    private TreeSetTest testTreeSet = new TreeSetTest();
    private HashMapTest testHashMap = new HashMapTest();
    private LinkedHashMapTest testLinkedHashMap = new LinkedHashMapTest();
    private TreeMapTest testTreeMap = new TreeMapTest();
    private HashMap<String, Integer> addList = new HashMap<>();
    private HashMap<String, Integer> getList = new HashMap<>();
    private HashMap<String, Integer> removeList = new HashMap<>();
    private List<String> nameKey = Arrays.asList("ArrayList",
            "Linkedlist",
            "HashSet",
            "LinkedHashSet",
            "TreeSet",
            "HashMap",
            "LinkedHashMap",
            "TreeMap");

    public void run(){
        testArrayList.testArrayList();
        testLinkedList.testArrayList();
        testHashSet.testArrayList();
        testLinkedHashSet.testArrayList();
        testTreeSet.testArrayList();
        testHashMap.testArrayList();
        testLinkedHashMap.testArrayList();
        testTreeMap.testArrayList();
        createLists();
        createFile();
    }

    private String[] readFromFiles(String s){
        String data = null;
        try {
            File myObj = new File(s + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data.split("]");
    }

    private void createLists(){
        String[] listArray = readFromFiles("array_list");
        String[] listLinked = readFromFiles("linked_list");
        String[] setHash = readFromFiles("hash_set");
        String[] setLinked = readFromFiles("linked_hash_set");
        String[] setTree = readFromFiles("tree_set");
        String[] mapHash = readFromFiles("hash_map");
        String[] mapLinked = readFromFiles("linked_hash_map");
        String[] mapTree = readFromFiles("tree_map");

        List<String> firstPart = new ArrayList<>();
        firstPart.add(listArray[0]);
        firstPart.add(listLinked[0]);
        firstPart.add(setHash[0]);
        firstPart.add(setLinked[0]);
        firstPart.add(setTree[0]);
        firstPart.add(mapHash[0]);
        firstPart.add(mapLinked[0]);
        firstPart.add(mapTree[0]);

        List<String> secondPart = new ArrayList<>();
        secondPart.add(listArray[1]);
        secondPart.add(listLinked[1]);
        secondPart.add(setHash[1]);
        secondPart.add(setLinked[1]);
        secondPart.add(setTree[1]);
        secondPart.add(mapHash[1]);
        secondPart.add(mapLinked[1]);
        secondPart.add(mapTree[1]);

        List<String> thirdPart = new ArrayList<>();
        thirdPart.add(listArray[2]);
        thirdPart.add(listLinked[2]);
        thirdPart.add(setHash[2]);
        thirdPart.add(setLinked[2]);
        thirdPart.add(setTree[2]);
        thirdPart.add(mapHash[2]);
        thirdPart.add(mapLinked[2]);
        thirdPart.add(mapTree[2]);
        for(int i =0; i<nameKey.size(); i++){
            addList.put(nameKey.get(i), Integer.parseInt(
                    firstPart.get(i).substring(firstPart.get(i).indexOf('[')+1, firstPart.get(i).indexOf(','))));
        }
        for(int i =0; i<nameKey.size(); i++){
            getList.put(nameKey.get(i), Integer.parseInt(
                    secondPart.get(i).substring(secondPart.get(i).indexOf('[')+1, secondPart.get(i).indexOf(','))));
        }
        for(int i =0; i<nameKey.size(); i++){
            removeList.put(nameKey.get(i), Integer.parseInt(
                    thirdPart.get(i).substring(thirdPart.get(i).indexOf('[')+1, thirdPart.get(i).indexOf(','))));
        }
    }

    private void createFile(){
        Map<String, Integer> result1 = addList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1,e2) ->e1, LinkedHashMap::new));
        Map<String, Integer> result2 = getList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1,e2) ->e1, LinkedHashMap::new));
        Map<String, Integer> result3 = removeList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1,e2) ->e1, LinkedHashMap::new));
        try{
            PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
            BufferedWriter bf = new BufferedWriter(writer);
            bf.write("Add");
            bf.newLine();
            for(Map.Entry<String,Integer> entry : addList.entrySet()){
                bf.write(entry.getKey() + " = " + entry.getValue());
                bf.newLine();
            }
            bf.write("Get");
            bf.newLine();
            for(Map.Entry<String,Integer> entry : getList.entrySet()){
                bf.write(entry.getKey() + " = " + entry.getValue());
                bf.newLine();
            }
            bf.write("Remove");
            bf.newLine();
            for(Map.Entry<String,Integer> entry : removeList.entrySet()){
                bf.write(entry.getKey() + " = " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
            bf.close();
        }catch (Exception e){
            System.out.println("IOException");
        }
    }
}
