package com.zjn.juc;

import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap a = new ConcurrentHashMap(16);
        a.put("", "");
        a.get("");

        Vector b = new Vector();
        b.add("");
        b.get(1);

        Hashtable c = new Hashtable();
        c.put("", "");
        c.get("");

        CopyOnWriteArrayList d = new CopyOnWriteArrayList();
        d.add("");
        d.get(1);

    }
}
