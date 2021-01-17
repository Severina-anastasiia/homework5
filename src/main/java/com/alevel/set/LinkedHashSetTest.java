package com.alevel.set;

import java.io.PrintWriter;
import java.util.*;

public class LinkedHashSetTest {
    private List<Long> timeAdd = new ArrayList<>();
    private java.util.List<Long> timeGet = new ArrayList<>();
    private List<Long> timeRemove = new ArrayList<>();
    private LinkedHashSet<Integer> array = new LinkedHashSet<>();

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
            array.add(i);
        }
    }


    private void testGet(){
        Iterator<Integer> i=array.iterator();
        while(i.hasNext())
        {
            i.next();
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
            PrintWriter writer = new PrintWriter("linked_hash_set.txt", "UTF-8");
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
