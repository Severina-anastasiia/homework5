package com.alevel.list;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {
    private List<Long> timeAdd = new ArrayList<>();
    private List<Long> timeGet = new ArrayList<>();
    private List<Long> timeRemove = new ArrayList<>();
    private LinkedList<Integer> array = new LinkedList<>();

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
    }

    private void testAdd(){
        for(int i = 0; i < 1000; i++){
            array.add(i);
        }
    }

    private void testGet(){
        for(int i = 0; i < 1000; i++){
            array.get(i);
        }
    }

    private void testRemove(){
        for(int i = 1000-1; i > 0; i--){
            array.remove(i);
        }
    }

    private void writeToFile(){
        timeAdd.stream().sorted();
        timeGet.stream().sorted();
        timeRemove.stream().sorted();
        try{
            PrintWriter writer = new PrintWriter("linked_list.txt", "UTF-8");
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
