package com.alevel.map;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class LinkedHashMapTest {
    private List<Long> timeAdd = new ArrayList<>();
    private java.util.List<Long> timeGet = new ArrayList<>();
    private List<Long> timeRemove = new ArrayList<>();
    private LinkedHashMap<Integer, Integer> array = new LinkedHashMap<>();

    public void testArrayList(){
        long start;
        long end;
        long result;
        for(int i =0; i<10; i++){
            start = System.nanoTime();
            testAdd();
            end = System.nanoTime();
            result = end - start;
            timeAdd.add(result);
        }
        for(int i =0; i<10; i++){
            start = System.nanoTime();
            testGet();
            end = System.nanoTime();
            result = end - start;
            timeGet.add(result);
        }
        for(int i =0; i<10; i++){
            start = System.nanoTime();
            testRemove();
            end = System.nanoTime();
            result = end - start;
            timeRemove.add(result);
        }
        writeToFile();
        testRemove();
    }

    private void testAdd(){
        for(int i = 0; i < 1000; i++){
            array.put(i, i);
        }
    }

    private void testGet(){
        for(int i = 0; i <1000; i++){
            array.get(i);
        }
    }

    private void testRemove(){
        for(int i = 0; i < 1000; i++){
            array.remove(i);
        }
    }

    private void writeToFile(){
        timeAdd.stream().sorted();
        timeGet.stream().sorted();
        timeRemove.stream().sorted();
        try{
            PrintWriter writer = new PrintWriter("linked_hash_map.txt", "UTF-8");
            writer.println("Add");
            writer.println(timeAdd);
            writer.println("Get");
            writer.println(timeGet);
            writer.println("Remove");
            writer.println(timeRemove);
            writer.close();
        }catch (Exception e){
            System.out.println("IOException");
        }
    }
}
