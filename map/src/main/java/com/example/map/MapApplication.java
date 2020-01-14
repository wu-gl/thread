package com.example.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.*;

public class MapApplication {

    public static void main(String[] args) {


        SpringApplication.run(MapApplication.class, args);

        //独立的
        Map map;


        Set set;

        List list;

        Queue queue;

        TreeMap treeMap;

        Hashtable hashtable;

        List list1;

        SortedSet sortedSet;

        Random r = new Random(1);
        TreeSet<User> hashSet = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            int ran1 = r.nextInt(100);
            User user = new User(ran1);
            hashSet.add(user);
        }
        hashSet.comparator();
        Iterator<User> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getAge());
        }
        hashSet.clear();

        TreeSet treeSet;

        Set set1;

        ArrayList arrayList;

        LinkedList linkedList;
        Vector vector;
        AbstractList abstractList;
        AbstractSequentialList abstractSequentialList;
        AbstractSet abstractSet;

        Queue queue1;
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
        //linkedHashSet.

        //LinkedHashSet

        HashMap hashMap=new HashMap();
        hashMap.put();


        final HashSet<String> strings = new HashSet<>();

        strings.add();



        HashMap hashMap=new HashMap();
        hashMap.put("经纬度拼接",orderId);

        1000
                20

        CountDownLatch countDownLatch=new CountDownLatch(50);


        countDownLatch.await();
        BlockingQueue


        HashSet<String> orders = new HashSet<>();


        HashMap hashMap=new HashMap();



    }

}
