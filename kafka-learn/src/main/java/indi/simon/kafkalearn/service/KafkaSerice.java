package indi.simon.kafkalearn.service;

import org.apache.commons.collections4.trie.PatriciaTrie;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Service
public class KafkaSerice {

    PatriciaTrie patriciaTrie = new PatriciaTrie();

    public static void main(String[] args) {

        Map<String, String> hashTable = new Hashtable<>();
        Map<String, String> hashMap = new HashMap<>(7);
        Map<String, String> treeMap = new TreeMap<>();
        Map<String, String> linkedMap = new LinkedHashMap<>(100, 0.75f, true);
        Map<String, String> concurrMap = new ConcurrentHashMap<>();
        Set<String> hashSet = new HashSet<>();
        Set<String> treeSet = new TreeSet<>();
        Set<String> linkedSet = new LinkedHashSet<>();
        List<String> arrList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        PriorityQueue<Character> priorityQueue = new PriorityQueue<>();
        ArrayDeque arrayDeque = new ArrayDeque();

        //DelayQueue<> delayQueue = new DelayQueue<>();
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);


    }

    public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> comparingByKey() {
        return new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> c1, Map.Entry<K, V> c2) {
                return c1.getKey().compareTo(c2.getKey());
            }
        };
    }
}
