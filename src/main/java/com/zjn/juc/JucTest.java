package com.zjn.juc;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JucTest
 *
 * @author zjn
 * @date 2021/3/12
 **/
public class JucTest {

    public static void main(String[] args) throws Exception{
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        }).start();

        Executors.newCachedThreadPool();
        ThreadPoolExecutor e = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new BlockingDeque<Runnable>() {
            @Override
            public void addFirst(Runnable runnable) {

            }

            @Override
            public void addLast(Runnable runnable) {

            }

            @Override
            public boolean offerFirst(Runnable runnable) {
                return false;
            }

            @Override
            public boolean offerLast(Runnable runnable) {
                return false;
            }

            @Override
            public void putFirst(Runnable runnable) throws InterruptedException {

            }

            @Override
            public void putLast(Runnable runnable) throws InterruptedException {

            }

            @Override
            public boolean offerFirst(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public boolean offerLast(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public Runnable takeFirst() throws InterruptedException {
                return null;
            }

            @Override
            public Runnable takeLast() throws InterruptedException {
                return null;
            }

            @Override
            public Runnable pollFirst(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public Runnable pollLast(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public boolean removeFirstOccurrence(Object o) {
                return false;
            }

            @Override
            public boolean removeLastOccurrence(Object o) {
                return false;
            }

            @Override
            public boolean add(Runnable runnable) {
                return false;
            }

            @Override
            public boolean offer(Runnable runnable) {
                return false;
            }

            @Override
            public void put(Runnable runnable) throws InterruptedException {

            }

            @Override
            public boolean offer(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public Runnable remove() {
                return null;
            }

            @Override
            public Runnable poll() {
                return null;
            }

            @Override
            public Runnable take() throws InterruptedException {
                return null;
            }

            @Override
            public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public Runnable element() {
                return null;
            }

            @Override
            public Runnable peek() {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Iterator<Runnable> iterator() {
                return null;
            }

            @Override
            public void push(Runnable runnable) {

            }

            @Override
            public Runnable removeFirst() {
                return null;
            }

            @Override
            public Runnable removeLast() {
                return null;
            }

            @Override
            public Runnable pollFirst() {
                return null;
            }

            @Override
            public Runnable pollLast() {
                return null;
            }

            @Override
            public Runnable getFirst() {
                return null;
            }

            @Override
            public Runnable getLast() {
                return null;
            }

            @Override
            public Runnable peekFirst() {
                return null;
            }

            @Override
            public Runnable peekLast() {
                return null;
            }

            @Override
            public Runnable pop() {
                return null;
            }

            @Override
            public Iterator<Runnable> descendingIterator() {
                return null;
            }

            @Override
            public int remainingCapacity() {
                return 0;
            }

            @Override
            public int drainTo(Collection<? super Runnable> c) {
                return 0;
            }

            @Override
            public int drainTo(Collection<? super Runnable> c, int maxElements) {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Runnable> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        }, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });

        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock1 = new ReentrantLock(true);
        lock.lock();
        lock.unlock();

        CountDownLatch f = new CountDownLatch(5);
        f.countDown();

        f.await();

        CyclicBarrier g = new CyclicBarrier(3);
        g.await();

        Semaphore h = new Semaphore(4);
        h.acquire();
        h.release();

        Exchanger<String> i = new Exchanger<String>();
        i.exchange("");
    }
}
